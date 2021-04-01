package com.example.supersecretproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile_activity extends AppCompatActivity implements View.OnClickListener {



    TextView textViewMOTD, textViewReveal;
    Button signOutButton;

    private FirebaseAuth mAuth;
    private DatabaseReference mDataBase;

    public TextView getTextViewMOTD() {
        return textViewMOTD;
    }

    public void setTextViewMOTD(TextView textViewMOTD) {
        this.textViewMOTD = textViewMOTD;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_activity);

        textViewMOTD = (TextView) findViewById(R.id.textViewMOTD);
        textViewReveal = (TextView) findViewById(R.id.textViewReveal);
        signOutButton = (Button) findViewById(R.id.signoutButton);
        mAuth = FirebaseAuth.getInstance();
        mDataBase = FirebaseDatabase.getInstance().getReference();

        signOutButton.setOnClickListener(this);
        textViewReveal.setOnClickListener(this);





    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signoutButton:
                startActivity(new Intent(this, LoginScreen.class));
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(Profile_activity.this, "Signed out", Toast.LENGTH_SHORT).show();
                break;
            case R.id.textViewReveal:
                revealMessage();
                break;
        }
    }


    public void revealMessage() {
        textViewReveal.animate().alpha(0).setDuration(1000);
        mDataBase.child("MOTD").addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        textViewMOTD.setText(snapshot.getValue().toString());

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                }
        );

    }

}