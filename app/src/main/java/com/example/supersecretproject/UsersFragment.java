package com.example.supersecretproject;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

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
    private UserArrayAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private DatabaseReference mDataBase;
    private ArrayList<User> userArrayList;


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

        //creates the usersArrayList and populates it with firebase snapshot.

        ArrayList<User> userArrayList = new ArrayList<>();
        mDataBase = FirebaseDatabase.getInstance().getReference();
        mDataBase.child("Users").addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        //iterates the real time database with all users
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            User user = ds.getValue(User.class);
                            userArrayList.add(user);
                        }

                        //just for debugging, delete before final.
                        for (int i = 0; i < userArrayList.size(); i++) {
                            Log.d("info", "list of people(hopefully)" + userArrayList.get(i).getFullName() + "   " + userArrayList.get(i).getEmail());
                        }
                        sortArrayListByValidation(userArrayList);

                        Log.d("Info", "adapter notified of change");
                        mAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }

                });

        Log.d("Info", "size of arrayList" + userArrayList.size());

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this.getActivity());
        mAdapter = new UserArrayAdapter(userArrayList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new UserArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Log.d("info", "card pressed");
            }

            //validates the user
            @Override
            public void onAddUserClick(int position) {
                final String[] userID = new String[1];
                Log.d("info", "the user clicked was " + userArrayList.get(position).getEmail());

                if (userArrayList.get(position).getUserStatus().equals("Validated")) {
                    Toast.makeText(getActivity(), "This user is already validated", Toast.LENGTH_SHORT).show();
                    return;
                } if (userArrayList.get(position).getUserStatus().equals("Administrator")) {
                    Toast.makeText(getActivity(), "This user is an Admin", Toast.LENGTH_SHORT).show();
                    return;
                } else {


//gets the userID from the box the button is pressed in and passes it to updateUserStatusToValidate for status change.
                    userArrayList.get(position).setUserStatus("Validated");
                mDataBase.child("Users")
                        .orderByChild("fullName")
                        .equalTo(userArrayList.get(position).getFullName())
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                                     userID[0] = childSnapshot.getKey();
                                }
                                updateUserStatusToValidated(userID[0]);
                                mAdapter.notifyItemChanged(position);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                }
            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

//sorts the list, admin first, then awaiting verification
    public ArrayList<User> sortArrayListByValidation(ArrayList<User> userArrayList) {
        Collections.sort(userArrayList, new Comparator<User>() {
            public int compare(User o1, User o2) {
                return o1.getUserStatus().compareTo(o2.getUserStatus());
            }
        });
        return userArrayList;
    }
//sets user status to validated
    public void updateUserStatusToValidated(String userID) {
        mDataBase.child("Users").child(userID).child("userStatus").setValue("Validated").addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(getActivity(), "user has been validated", Toast.LENGTH_SHORT).show();
                Log.d("info", userID + "has been set to validated");




            }
        });
    }




}