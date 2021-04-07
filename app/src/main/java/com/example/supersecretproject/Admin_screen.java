package com.example.supersecretproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;

//admin screen used to host fragments.



public class Admin_screen extends AppCompatActivity {

    private Button updateMOTDButton, viewUsersButton;
    EditText editTextMOTD;
    BottomNavigationView bottomNavBar;
    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_screen);
        BottomNavigationView bottomNavBar =  findViewById(R.id.bottomNav);
        bottomNavBar.setOnNavigationItemSelectedListener(navListener);

    }
//bottom nav bar, switch case picks up selection and plays the appropirate animations
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {

                        case R.id.dashBoardFragment:
                            selectedFragment = new DashBoardFragment();
                           getSupportFragmentManager().beginTransaction().setCustomAnimations(android.R.anim.slide_in_left,  android.R.anim.slide_out_right, R.anim.slide_in_left, R.anim.slide_in_left)
                                   .replace(R.id.fragment, selectedFragment).addToBackStack(null).commit();
                            break;

                        case R.id.usersFragment:
                            selectedFragment = new UsersFragment();
                            getSupportFragmentManager().beginTransaction().setCustomAnimations(android.R.anim.slide_out_right,  android.R.anim.slide_in_left, R.anim.slide_in_left, R.anim.slide_in_left)
                                    .replace(R.id.fragment, selectedFragment).addToBackStack(null).commit();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment, selectedFragment).commit();

                    return true;
                }

            };

}














