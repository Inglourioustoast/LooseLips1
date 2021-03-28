package com.example.supersecretproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class LoginScreen extends AppCompatActivity implements View.OnClickListener {

    EditText emailText;
    EditText passwordText;
    Button loginButton;
    TextView register;
    ProgressBar progressBar;
    TextView forgotPassword;
    int inCorrectPasswordCount;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        emailText = (EditText) findViewById(R.id.editTextEmail);
        passwordText = (EditText) findViewById(R.id.editTextPassword);
        loginButton = (Button) findViewById(R.id.loginButton);
        forgotPassword = (TextView) findViewById(R.id.textViewForgotPassword);
        register = (TextView) findViewById(R.id.textViewRegister);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        loginButton.setOnClickListener(this);
        forgotPassword.setOnClickListener(this);
        register.setOnClickListener(this);


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
                    return;


                } else {

                    //login proccess
                    progressBar.setVisibility(View.VISIBLE);
                    Toast.makeText(this, "Sucesfully logged in", Toast.LENGTH_SHORT).show();
                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            //pulls current user status and switches the login page depending on their status.
                        }


                    });

                }
        }

    }
}

