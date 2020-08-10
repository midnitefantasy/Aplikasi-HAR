package com.example.har;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class forgetPassword extends AppCompatActivity {
    Button btnResetPassword;
    EditText mEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        btnResetPassword = (Button) findViewById(R.id.btnReset);
        mEmail = (EditText) findViewById(R.id.email);

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
                                }
                            }
                        });
            }
        });
    }
}