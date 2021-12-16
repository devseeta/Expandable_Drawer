package com.example.expandeble_drawer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;
    private String[] items;

    private ExpandableListView expandableListView;
    private ExpandableListAdapter expandableListAdapter;
    private List<String> LsTitle;
    private Map<String, List<String>> LstChild;
    private NavigationManager navigationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();
        expandableListView = findViewById(R.id.navList);
        navigationManager = FragmentNavigationManager.getfmanager(this);

        initItems();
        View ListHeaderView = getLayoutInflater().inflate(R.layout.nav_header, null, false);
        genData();

        addDrawerItem();
        setupDrawer();

        if (savedInstanceState == null)
//            SelectFirstItemasDefault();
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("elejkf");

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("Run Ho na yedya");
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu();

            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);

    }

    private void addDrawerItem() {
        expandableListAdapter = new CustomeExpandableListAdapter(this, LsTitle, LstChild);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
//                set title for toolbar when open menu
                getSupportActionBar().setTitle(LsTitle.get(groupPosition).toString());
            }
        });
        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                getSupportActionBar().setTitle("EDMTDev");
            }
        });
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
//       change fragment when click on item
                String selctedItem = ((List) (LstChild.get(LsTitle.get(groupPosition))))
                        .get(childPosition).toString();
                getSupportActionBar().setTitle(selctedItem);

                if (items[0].equals(LsTitle.get(groupPosition)))
                    navigationManager.showFragment(selctedItem);
                else
                    throw new IllegalArgumentException("not supported fragment");

                mDrawerLayout.closeDrawer(GravityCompat.START);

                return false;
            }
        });


    }

    private void genData() {
        List<String> title = Arrays.asList("Android programing", "Xamarin prog", "Ios programing");
        List<String> childitem = Arrays.asList("beginer", "Intermediate", "Professional");

        LstChild = new TreeMap<>();
        LstChild.put(title.get(0), childitem);
        LstChild.put(title.get(1), childitem);
        LstChild.put(title.get(2), childitem);

        LsTitle = new ArrayList<>(LstChild.keySet());


    }


    private void initItems() {
        items = new String[]{"Android programing", "Xamarin prog", "Ios programing"};
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (mDrawerToggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }
}