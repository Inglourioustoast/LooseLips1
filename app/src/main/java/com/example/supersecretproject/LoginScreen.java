package com.example.supersecretproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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

        //Listener for pressing enter int eh password field and hitting enter --> login

        passwordText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    userLogin();
                    handled = true;
                }
                return handled;
            }
        });
    }


    //various onClickslisteners for buttons
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
                userLogin();
        }
    }

    //checks login details locally before submitting to firebase for auth
    private void userLogin() {
        String email = emailText.getText().toString().trim();
        String password = passwordText.getText().toString().trim();

        if (!Validate_User.validatePassword(password)) {
            emailText.setError("Please enter a valid Password");
            inCorrectPasswordCount++;
            emailText.requestFocus();
            progressBar.setVisibility(View.INVISIBLE);
            return;
        }
        if (!Validate_User.validateEmail(email)) {
            emailText.setError("Please enter a valid email");
            emailText.requestFocus();
            progressBar.setVisibility(View.INVISIBLE);
            return;

        }


        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()) {
                    String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    Log.d("info", "Login sucessful, user ID is : " + userID);

                    //direct to appropriate activity depending on "user status" field
                    directToCorrectLoginActivity(userID);
                    progressBar.setVisibility(View.INVISIBLE);
                } else {
                    Toast.makeText(LoginScreen.this, "user login unsuccessful, please check your details", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.INVISIBLE);
                }

            }
        });
    }



    public void directToCorrectLoginActivity(String userID) {
        mDataBase.child("Users").child(userID).child("userStatus").addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        setUserStatus(snapshot.getValue().toString());
                        Log.d("info", "getting user status for " + userID);
                        Log.d("info", "user status grabbed: " + userStatus);

                        switch (getUserStatus()) {

                            case "Awaiting validation":
                                startActivity(new Intent(LoginScreen.this, Awaiting_Validation.class));
                                return;
                            case "Validated":
                                startActivity(new Intent(LoginScreen.this, Profile_activity.class));
                                return;
                            case "Administrator":
                                startActivity(new Intent(LoginScreen.this, Admin_screen.class));
                                return;
                            case "null":
                                Toast.makeText(LoginScreen.this, "null detected, failed to direct correctly", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {


                    }

                });



    }
}