package com.example.supersecretproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
        String PasswordConfirm = editTextPasswordConfirm.getText().toString().trim();
        String age = editTextAge.getText().toString().trim();
        String password = editTextUserPassword.getText().toString().trim();
        String firstName = editTextFirstName.getText().toString().trim();
        String name = editTextFirstName.getText().toString().trim();
        String superSecretPassword = editTextSecretPassword.getText().toString().trim();
        String lastName = editTextLastName.getText().toString().trim();
        String fullName = combineNames(editTextFirstName, editTextLastName);

        if (firstName.isEmpty()) {
            editTextFirstName.setError("Please enter a valid Name");
            editTextFirstName.requestFocus();
            return;
        }

        if (lastName.isEmpty()) {
            editTextFirstName.setError("Please enter a valid Last Name");
            editTextFirstName.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextUserPassword.setError("Please enter a valid Password");
            editTextUserPassword.requestFocus();
            return;
        }
        if (email.isEmpty()) {
            editTextEmail.setError("Please enter a valid email");
            editTextEmail.requestFocus();
            return;
        }
        if (PasswordConfirm.isEmpty()) {
            editTextEmail.setError("Please confirm your Password");
            editTextEmail.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            User user = new User(fullName, age, email, superSecretPassword );
                            Log.d("Account creation", "account created: fullname :" + fullName);
                            FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())

                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if(task.isSuccessful()) {
                                        Toast.makeText(RegisterAccount.this, "User has been registered sucessfully", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.INVISIBLE);
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



    private String combineNames(EditText editTextFirstName, EditText editTextLastName) {
        return editTextFirstName.getText().toString().trim() + " " + editTextLastName.getText().toString().trim();
    }

}