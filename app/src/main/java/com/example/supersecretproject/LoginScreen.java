package com.example.supersecretproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginScreen extends AppCompatActivity implements View.OnClickListener {

    EditText emailText;
    EditText passwordText;
    Button loginButton;
    TextView register;

    TextView forgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        emailText = (EditText) findViewById(R.id.editTextEmail);
        passwordText = (EditText) findViewById(R.id.editTextPassword);
        loginButton= (Button) findViewById(R.id.resetPasswordButton);
        forgotPassword= (TextView) findViewById(R.id.textViewForgotPassword);
        register= (TextView) findViewById(R.id.textViewRegister);

        loginButton.setOnClickListener(this);
        forgotPassword.setOnClickListener(this);
        register.setOnClickListener(this);

    }


    public void onClick(View v) {
        switch(v.getId()) {
          ///  case R.id.loginButton
                //add new intent here...
            case R.id.textViewForgotPassword:
                startActivity(new Intent(this, ForgotPassword.class));
                break;
            case R.id.textViewRegister:
                startActivity(new Intent(this, RegisterAccount.class));
                break;


        }

    }








}