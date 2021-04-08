package com.example.supersecretproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

//admin screen used to host fragments.



public class Admin_screen extends AppCompatActivity {


    androidx.appcompat.widget.Toolbar toolBar;
    EditText editTextMOTD;
    BottomNavigationView bottomNavBar;
    NavController navController;
    private Button updateMOTDButton, viewUsersButton;

    //bottom nav bar, switch case picks up selection and plays the appropirate animations
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment;

                    //gets the item selected and transitions to that fragment with animations
                    switch (item.getItemId()) {

                        case R.id.dashBoardFragment:
                            selectedFragment = new DashBoardFragment();
                            getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right)
                                    .replace(R.id.fragmentContainer, selectedFragment).addToBackStack(null).commit();
                            break;

                        case R.id.usersFragment:
                            selectedFragment = new UsersFragment();

                            getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
                                    .replace(R.id.fragmentContainer, selectedFragment).addToBackStack(null).commit();
                            break;
                    }

                    return true;
                }

            };

    //on create setup the Nav Bar controller
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_screen);
        BottomNavigationView bottomNavBar = findViewById(R.id.bottomNav);
        bottomNavBar.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new DashBoardFragment()).commit();
        FirebaseAuth auth = FirebaseAuth.getInstance();


        toolBar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbar);
        //sets up tool bar and logout button (on click listener)
//logout button
        toolBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                Intent intent = new Intent(getApplicationContext(), LoginScreen.class);
                startActivity(intent);
            }
        });



    }

}














