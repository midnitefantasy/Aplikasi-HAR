package com.example.har;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.har.ui.login.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class Profile extends AppCompatActivity {
    TextView btnEditProfile, btnHistory, btnLogout;
    TextView tb,bb,jk,nama,email;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        btnLogout = (TextView) findViewById(R.id.btnLogOut);
        btnEditProfile = (TextView) findViewById(R.id.btnEditProfile);
        btnHistory = (TextView) findViewById(R.id.btnHistory);
        fAuth = FirebaseAuth.getInstance();
        btnLogout.setEnabled(true);
        btnLogout.setClickable(true);
        btnEditProfile.setEnabled(true);
        btnEditProfile.setClickable(true);
        btnHistory.setEnabled(true);
        btnHistory.setClickable(true);

        btnLogout.setOnClickListener(new View.OnClickListener (){
            public void onClick (View view){
                FirebaseAuth.getInstance().signOut();
                Toast.makeText( getApplicationContext(), "Logout Berhasil", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });
    }
}