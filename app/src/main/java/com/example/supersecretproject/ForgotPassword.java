package com.example.supersecretproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity  {

    private FirebaseAuth mAuth;
    EditText editTextEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        mAuth = FirebaseAuth.getInstance();
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);

    }

//begins the password reset process, handled with firebase mostly.
    public void resetPassword(View v) {
        String email = editTextEmail.getText().toString().trim();

        if (!Validate_User.validateEmail(email)) {
            editTextEmail.setError("Please enter a valid email");
            editTextEmail.requestFocus();
        } else {
            {
                mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ForgotPassword.this, "Password reset requested, check your email", Toast.LENGTH_LONG).show();
                            Log.d("info", "Password reset request sent, please check your email.");
                            finish();
                        }
                    }
                });

            }
        }
    }
}