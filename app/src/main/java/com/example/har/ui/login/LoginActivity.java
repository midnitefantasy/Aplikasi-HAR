package com.example.har.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.har.Home;
import com.example.har.R;
import com.example.har.forgetPassword;
import com.example.har.signup;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    EditText mPassword, mEmail;
    TextView btnSignUp, btnLupaPassword;
    Button btnLogin;
    //final String loginURL = "http://192.168.100.21/HAR/login.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // declaring obejct of EditText control
        mEmail = (EditText) findViewById(R.id.email);
        mPassword = (EditText) findViewById(R.id.password);
        btnLogin = (Button)findViewById(R.id.btnLogin);
        btnSignUp = (TextView)findViewById(R.id.btnSignUp);
        btnLupaPassword = (TextView)findViewById(R.id.btnLupaPassword);
        btnSignUp.setClickable(true);
        btnLogin.setClickable(true);
        btnLupaPassword.setClickable(true);
        btnLogin.setEnabled(true);
        btnSignUp.setEnabled(true);
        btnLupaPassword.setEnabled(true);
        mAuth = FirebaseAuth.getInstance();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = mEmail.getText().toString();
                final String password = mPassword.getText().toString();
                if (TextUtils.isEmpty(email)) {
                    mEmail.setError("Please enter email");
                    mEmail.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    mPassword.setError("Please enter password");
                    mPassword.requestFocus();
                    return;
                }

                //authenticate the uer
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                           Toast.makeText(getApplicationContext(), "User successfully login", Toast.LENGTH_SHORT).show();
                           Intent intent = new Intent(getApplicationContext(), Home.class);
                           startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), "Email/Password salah", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view2){
                Intent intent = new Intent(getApplicationContext(), signup.class);
                startActivity(intent);
            }
        });

        btnLupaPassword.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view3){
                Intent intent = new Intent(getApplicationContext(), forgetPassword.class);
                startActivity(intent);
            }
        });
    }
}
