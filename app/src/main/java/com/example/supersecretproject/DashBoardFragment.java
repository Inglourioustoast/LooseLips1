package com.example.supersecretproject;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DashBoardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DashBoardFragment extends Fragment  {




    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private EditText editTextMOTD;
    private DatabaseReference mDataBase;


    public EditText getEditTextMOTD() {
        return editTextMOTD;
    }

    public void setEditTextMOTD(EditText editTextMOTD) {
        this.editTextMOTD = editTextMOTD;
    }

    public DashBoardFragment() {
        // Required empty public constructor
    }

    public static DashBoardFragment newInstance(String param1, String param2) {
        DashBoardFragment fragment = new DashBoardFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dash_board, container, false);
        EditText editTextMOTD = (EditText) view.findViewById(R.id.editTextMOTD);
Button updateMOTDButton = (Button) view.findViewById(R.id.updateMOTDButton);


//grabs "Secret message from firebase
        mDataBase = FirebaseDatabase.getInstance().getReference();
        mDataBase.child("MOTD").addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        editTextMOTD.setText(Objects.requireNonNull(snapshot.getValue()).toString());
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        editTextMOTD.setText("could not get secret message, try again later");
                    }
                });

        updateMOTDButton.setOnClickListener(new View.OnClickListener() {

            //updates the MOTD
    @Override
    public void onClick(View v) {
      String MOTDupdate;
      Log.d("debug", "MOTD is " + editTextMOTD.getText());
     MOTDupdate = editTextMOTD.getText().toString();
        mDataBase.child("MOTD").setValue(MOTDupdate).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(getActivity(), "MOTD updated successfully", Toast.LENGTH_LONG).show();
                Log.d("info", "update MOTD button clicked");

            }}); }
    });
        return view;
    }}











