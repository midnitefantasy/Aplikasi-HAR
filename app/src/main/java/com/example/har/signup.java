package com.example.har;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.har.ui.login.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class signup extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText nNamaLengkap,nEmail,nPassword,nUmur,nBerat,nTinggi;
    Spinner nJK;
    Button nSignupBtn, btnBack;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        nNamaLengkap    = findViewById(R.id.nama);
        nEmail          = findViewById(R.id.email);
        nUmur           = findViewById(R.id.umur);
        nBerat          = findViewById(R.id.bb);
        nTinggi         = findViewById(R.id.tb);
        nJK             = findViewById(R.id.jeniskelamin);
        nPassword       = findViewById(R.id.password);
        nSignupBtn      = findViewById(R.id.btnSign);
        btnBack         = findViewById(R.id.btnBack);
        fAuth           = FirebaseAuth.getInstance();
        nSignupBtn.setEnabled(true);
        nSignupBtn.setClickable(true);
        btnBack.setEnabled(true);
        btnBack.setClickable(true);
        Spinner spin = (Spinner) findViewById(R.id.jeniskelamin);
        spin.setOnItemSelectedListener(this);
        String[] jk = {"Pilih", "Laki-laki", "Perempuan"};
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item,jk);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);
        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), Home.class));
            finish();
        }
        nSignupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = nEmail.getText().toString().trim();
                String password = nPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    nEmail.setError("Email is Required");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    nPassword.setError("Password is Required");
                    return;
                }
                if (password.length() < 8){
                    nPassword.setError("Password must be >= 8 characters");
                    return;
                }
                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(signup.this, "User Created, silahkan Login", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));

                        } else {
                            Toast.makeText(signup.this, "Error", Toast.LENGTH_SHORT).show();


                        }
                    }
                });
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        //Toast.makeText(parent.getContext(), "Selected : " + item, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0){
    }


}