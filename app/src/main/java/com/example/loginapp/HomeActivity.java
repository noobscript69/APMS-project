package com.example.loginapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    private TextView signOutBtn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

      signOutBtn= findViewById(R.id.signOut_Btn);

      mAuth= FirebaseAuth.getInstance();
      signOutBtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            mAuth.signOut();
            startActivity(new Intent(HomeActivity.this,SignInActivity.class));
            finish();
          }
      });
    }
}
