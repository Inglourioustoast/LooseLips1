package com.example.supersecretproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginScreen extends AppCompatActivity implements View.OnClickListener {



    public String userStatus;
    EditText emailText, passwordText;
    Button loginButton;
    TextView register;
    ProgressBar progressBar;
    TextView forgotPassword;
    int inCorrectPasswordCount;
    String userID;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }


    private FirebaseAuth mAuth;
    private DatabaseReference mDataBase;

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        mAuth = FirebaseAuth.getInstance();
        mDataBase = FirebaseDatabase.getInstance().getReference();
        emailText = (EditText) findViewById(R.id.editTextEmail);
        passwordText = (EditText) findViewById(R.id.editTextPassword);
        loginButton = (Button) findViewById(R.id.loginButton);
        forgotPassword = (TextView) findViewById(R.id.textViewForgotPassword);
        register = (TextView) findViewById(R.id.textViewRegister);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        loginButton.setOnClickListener(this);
        forgotPassword.setOnClickListener(this);
        register.setOnClickListener(this);
        setUserStatus("null1");
    }

    public void onClick(View v) {
        switch (v.getId()) {
            ///  case R.id.loginButton
            //add new intent here...
            case R.id.textViewForgotPassword:
                startActivity(new Intent(this, ForgotPassword.class));
                break;
            case R.id.textViewRegister:
                startActivity(new Intent(this, RegisterAccount.class));
                break;
            case R.id.loginButton:
                progressBar.setVisibility(View.VISIBLE);
                String email = emailText.getText().toString().trim();
                String password = passwordText.getText().toString().trim();

                if (!Validate_User.validatePassword(password)) {
                    emailText.setError("Please enter a valid Password");
                    inCorrectPasswordCount++;
                    emailText.requestFocus();
                    return;
                }
                if (!Validate_User.validateEmail(email)) {
                    emailText.setError("Please enter a valid email");
                    emailText.requestFocus();

                } else {
                    //login proccess, signs into firebase + pulls validation status
                    progressBar.setVisibility(View.VISIBLE);
                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {


                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(LoginScreen.this, "Sucesfully logged in", Toast.LENGTH_SHORT).show();
                                Log.d("debug", "user singed in");

                                Log.d("debug", "about to grab user status");
                                userID = task.getResult().getUser().getUid();
                                Log.d("debug", "user status is " + userID);
                            } else {
                                Log.d("login failed", "failed to log in");
                            }
                        }
                    });

                    Log.d("debug", "about to pull status for " + getUserID());
                                mDataBase.child("Users").child(getUserID()).child("userStatus").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    //pulls current user status and switches the login page depending on their status.
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        userStatus = snapshot.getValue().toString();
                                        Log.d("debug", "user status " + userStatus);
                                    }
                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {
                                    }});




                        }



                    };
                    Log.d("info", "Starting switchcase");
                          switch (userStatus) {
                                    case "Awaiting Validation": {
                                        startActivity(new Intent(LoginScreen.this, Awaiting_Validation.class));
                                        finish();
                                    }
                                    case "Administrator": {
                                        startActivity(new Intent(LoginScreen.this, Admin_screen.class));
                                        finish();
                                    }
                                    case "Validated": {
                                        startActivity(new Intent(LoginScreen.this, Profile_activity.class));
                                        finish();
                                    }
                              case "null": {
                                  Log.d("debug", "cant pull user status" );
                              }








                            };





                    ;}}





