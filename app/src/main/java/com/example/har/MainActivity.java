package com.example.har;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.har.ui.login.LoginActivity;

import java.util.TimerTask;
import android.content.Intent;
import java.util.Timer;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        }, 3000);
    }
}
