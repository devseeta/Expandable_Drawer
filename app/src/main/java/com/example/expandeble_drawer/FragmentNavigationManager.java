package com.example.expandeble_drawer;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class FragmentNavigationManager implements NavigationManager {
    private static FragmentNavigationManager fmanager;
    private FragmentManager fragmentManager;
    private MainActivity mainActivity;


    public static FragmentNavigationManager getfmanager(MainActivity mainActivity) {
        if (fmanager == null)
            fmanager = new FragmentNavigationManager();
        fmanager.configure(mainActivity);
        return fmanager;


    }

    private void configure(MainActivity mainActivity) {
        mainActivity = mainActivity;
        fragmentManager = mainActivity.getSupportFragmentManager();
    }

    @Override
    public void showFragment(String title) {

        showFragment(ContaintFragment.newInstance(title), false);

    }

    private void showFragment(Fragment containtFragment, boolean b) {
        FragmentManager fm = fragmentManager;
        FragmentTransaction ft = fm.beginTransaction().replace(R.id.container, containtFragment);

        ft.addToBackStack(null);
        if (b || !BuildConfig.DEBUG)
            ft.commitAllowingStateLoss();
        else
            ft.commit();
        fm.executePendingTransactions();
    }
}

//    public void showFragment(Fragment fragment,boolean allowStateLoss){
//           FragmentManager fm=fragmentManager;
//        FragmentTransaction ft = fm.beginTransaction().replace(R.id.container,fragmentcontent);
//
//        ft.addToBackStack(null);
//        if (b|| !BuildConfig.DEBUG)
//ft.commitAllowingStateLoss();
//        else
//            ft.commit();
//        ft.executePendingTransacion();
//    }

