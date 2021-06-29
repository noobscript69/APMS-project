package com.example.loginapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {

    private EditText mEmail, mPass;
    private TextView mTextView;
    private Button signInBtn;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmail =findViewById(R.id.signin);
        mPass=findViewById(R.id.passsignin);
        signInBtn=findViewById(R.id.signin_btn);

        mTextView=findViewById(R.id.textView2);
        mAuth=FirebaseAuth.getInstance();

        mTextView.setOnClickListener((v -> {
            startActivity(new Intent(SignInActivity.this , MainActivity.class));
        }
        ));

        signInBtn.setOnClickListener((v)-> {logInUser();});

    }
    private void logInUser() {

        String email = mEmail.getText().toString();
        String pass = mPass.getText().toString();

        if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            if (!pass.isEmpty()) {

                mAuth.signInWithEmailAndPassword(email, pass)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast.makeText(SignInActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignInActivity.this, HomeActivity.class));
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(SignInActivity.this, "Invalid Input", Toast.LENGTH_SHORT).show();
                    }
                });
            }else {
                mPass.setError("Empty Fields Are Not Allowed");
            }
        }else if (email.isEmpty()){
            mEmail.setError("Empty Fields Are Not Allowed");
        }else {
            mEmail.setError("Please Enter Correct Email");
        }
    }

    }


