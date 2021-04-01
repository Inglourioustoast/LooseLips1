package com.example.supersecretproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UsersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UsersFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UsersFragment() {
        // Required empty public constructor
    }



    public static UsersFragment newInstance(String param1, String param2) {
        UsersFragment fragment = new UsersFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_users, container, false);

        ArrayList<ExampleItem> exampleList = new ArrayList<>();
        exampleList.add(new ExampleItem(R.drawable.person, "Line 1", "Line 2"));
        exampleList.add(new ExampleItem(R.drawable.person, "Line 3", "Line 4"));
        exampleList.add(new ExampleItem(R.drawable.person, "Line 5", "Line 6"));
        exampleList.add(new ExampleItem(R.drawable.person, "Line 1", "Line 2"));
        exampleList.add(new ExampleItem(R.drawable.person, "Line 3", "Line 4"));
        exampleList.add(new ExampleItem(R.drawable.person, "Line 5", "Line 6"));
        exampleList.add(new ExampleItem(R.drawable.person, "Line 1", "Line 2"));
        exampleList.add(new ExampleItem(R.drawable.person, "Line 3", "Line 4"));
        exampleList.add(new ExampleItem(R.drawable.person, "Line 5", "Line 6"));
        exampleList.add(new ExampleItem(R.drawable.person, "Line 1", "Line 2"));
        exampleList.add(new ExampleItem(R.drawable.person, "Line 3", "Line 4"));
        exampleList.add(new ExampleItem(R.drawable.person, "Line 5", "Line 6"));

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this.getActivity());
        mAdapter = new ExampleAdapter(exampleList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);






        return view;


    }
}