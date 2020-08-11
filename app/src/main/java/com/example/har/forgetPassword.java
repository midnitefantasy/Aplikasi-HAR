package com.example.har;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class forgetPassword extends AppCompatActivity {
    Button btnResetPassword;
    TextView btnSignUp;
    EditText mEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        btnResetPassword = (Button) findViewById(R.id.btnReset);
        mEmail = (EditText) findViewById(R.id.email);
        btnSignUp = (TextView)findViewById(R.id.btnSignUp);
        btnResetPassword.setClickable(true);
        btnResetPassword.setEnabled(true);
        btnSignUp.setClickable(true);
        btnSignUp.setEnabled(true);

        btnResetPassword.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                final String email = mEmail.getText().toString();
                FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Intent intent = new Intent(getApplicationContext(), forgetPassword2.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(getApplicationContext(), "Gagal mengirim email reset", Toast.LENGTH_SHORT).show();
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
    }
}