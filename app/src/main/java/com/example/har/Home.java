package com.example.har;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.har.ui.login.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Home extends AppCompatActivity {
    TextView nama;
    Button btnProfile;
    private FirebaseAuth mAuth;

    // @Override
    //public onStart(){
    //   super.onStart();
    //  FirebaseUser currentUser = mAuth.getCurrentUser();
    //  if(!FirebaseUser currentUser.isEmailVerified()){
    //      startActivity(new Intent(this, MainActivity.class));
    //      finish();
    //  }
    // }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        nama = findViewById(R.id.nama);
        btnProfile = findViewById(R.id.btnProfile);
        btnProfile.setClickable(true);
        btnProfile.setEnabled(true);

        btnProfile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                startActivity(new Intent(getApplicationContext(), Profile.class));
            }
        });


    }
}

