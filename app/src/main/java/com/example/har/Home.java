package com.example.har;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.har.ui.login.LoginActivity;

public class Home extends AppCompatActivity {
    TextView nama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        nama = findViewById(R.id.nama);
        if (!PrefConfig.getInstance(this).isLoggedIn()){
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
        String loggedNama = PrefConfig.getInstance(this).LoggedInUser();
        nama.setText(loggedNama);
    }

    /*public static PrefConfig prefConfig;
    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefConfig = new PrefConfig (this);

        if (findViewById (R.id.fragment_container_view_tag)!=null)
        {
            if(savedInstanceState!=null)
            {
                return;
            }

            if(prefConfig.readLoginStatus()){
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_container);
            }

        }
    }*/
}