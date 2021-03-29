package com.example.supersecretproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Profile_activity extends AppCompatActivity {

    Button logoutButton;
    TextView editTextSecretMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_activity);
        TextView textView = findViewById(R.id.editTextSecretMessage);
        logoutButton = (Button) findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener((View.OnClickListener) this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.logoutButton:
                startActivity(new Intent(this, LoginScreen.class));
                break;
        }
    }
}