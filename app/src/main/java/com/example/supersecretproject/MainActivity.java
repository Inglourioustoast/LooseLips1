package com.example.supersecretproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    ImageView spyImage;
    public Button startButton;
    TextView textViewTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null ){
            // user is logged in
            // verify against user type
            // is admin
            // is reader
            Intent intent = new Intent(this, Admin_screen.class);
            startActivity(intent);
        }
        startButton = (Button) findViewById(R.id.loginButton);

    startButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Log.d("Info", "Button pressed, moving to login screen");
        startActivity(new Intent(MainActivity.this, LoginScreen.class));

    }
});
    }


}