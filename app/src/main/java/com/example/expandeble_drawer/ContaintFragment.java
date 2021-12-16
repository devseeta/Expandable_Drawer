package com.example.expandeble_drawer;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class ContaintFragment extends Fragment {
private static final String key_title="Containt";

    public ContaintFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ContaintFragment newInstance(String param1) {
        ContaintFragment fragment = new ContaintFragment();
        Bundle args = new Bundle();
        args.putString(key_title,param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_containt, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String title= getArguments().getString(key_title);
        ((TextView)view.findViewById(R.id.title_name)).setText(title);


    }
}
