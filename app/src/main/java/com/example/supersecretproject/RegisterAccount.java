package com.example.supersecretproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterAccount extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;

    private Button registerUser;

    private EditText editTextSecretPassword;
    private EditText editTextAge;
    private EditText editTextEmail;
    private EditText editTextPasswordConfirm;
    private EditText editTextFirstName;
    private EditText editTextUserPassword;
    private EditText editTextLastName;

    private Button RegisterAccountButton;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_account);

        mAuth = FirebaseAuth.getInstance();
        registerUser = (Button) findViewById(R.id.registerAccountButton);
        registerUser.setOnClickListener(this);
        editTextFirstName = (EditText) findViewById(R.id.editTextFirstName);
        editTextLastName = (EditText) findViewById(R.id.editTextLastName);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPasswordConfirm = (EditText) findViewById(R.id.editTextPasswordConfirm);
        editTextAge = (EditText) findViewById(R.id.editTextAge);
        editTextSecretPassword = (EditText) findViewById(R.id.editTextSecretPassword);
        editTextUserPassword = (EditText) findViewById(R.id.editTextUserPassword);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.registerAccountButton:
                registerUser();
                break;

        }

    }
//on clicking register User:
    private void registerUser() {
        String email = editTextEmail.getText().toString().trim();
        String passwordConfirm = editTextPasswordConfirm.getText().toString().trim();
        String age = editTextAge.getText().toString().trim();
        String password = editTextUserPassword.getText().toString().trim();
        String firstName = editTextFirstName.getText().toString().trim();
        String name = editTextFirstName.getText().toString().trim();
        String superSecretPassword = editTextSecretPassword.getText().toString().trim();
        String lastName = editTextLastName.getText().toString().trim();
        String fullName = combineNames(editTextFirstName, editTextLastName);
        int inCorrectPasswordCount = 0;


        if (!Validate_User.validateFirstName(firstName)) {
            editTextFirstName.setError("please enter a valid first name");
            editTextFirstName.requestFocus();
            return;
        }

        if (!Validate_User.validateLastName(lastName)) {
            editTextFirstName.setError("Please enter a valid Last Name");
            editTextFirstName.requestFocus();
            return;
        }

        if (!Validate_User.validatePassword(password)) {
            editTextUserPassword.setError("Please enter a valid Password");
            inCorrectPasswordCount++;
            editTextUserPassword.requestFocus();
            return;
        }
        if (!Validate_User.validateEmail(email)) {
            editTextEmail.setError("Please enter a valid email");
            editTextEmail.requestFocus();
            return;
        }
        if (!Validate_User.validatePasswordConfirmation(passwordConfirm, password)) {
            editTextPasswordConfirm.setError("Password confirmation does not match");
            editTextPasswordConfirm.requestFocus();
            return;
        } else {


            progressBar.setVisibility(View.VISIBLE);
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                User user = new User(fullName, age, email, superSecretPassword);
                                user.setUserStatus("Awaiting validation");
                                Log.d("Account creation", "account created: fullname :" + fullName);
                                FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>()

                                {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if (task.isSuccessful()) {

                                            Toast.makeText(RegisterAccount.this, "User has been registered sucessfully", Toast.LENGTH_LONG).show();
                                            progressBar.setVisibility(View.INVISIBLE);
                                            startActivity(new Intent(RegisterAccount.this, Awaiting_Validation.class));


                                        } else {

                                            Toast.makeText(RegisterAccount.this, "User Registration Failed", Toast.LENGTH_LONG).show();

                                            progressBar.setVisibility(View.GONE);
                                        }
                                    }
                                });
                            } else {
                                Toast.makeText(RegisterAccount.this, "User Registration Failed", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }
    }


    private String combineNames(EditText editTextFirstName, EditText editTextLastName) {
        return editTextFirstName.getText().toString().trim() + " " + editTextLastName.getText().toString().trim();
    }

}