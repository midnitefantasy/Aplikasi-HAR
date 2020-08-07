package com.example.har.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.har.API.APIRequestData;
import com.example.har.API.retroserver;
import com.example.har.Home;
import com.example.har.MainActivity;
import com.example.har.Model.LoginModel;
import com.example.har.PrefConfig;
import com.example.har.R;
import com.example.har.signup;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.Manifest.permission.READ_CONTACTS;

public class LoginActivity extends AppCompatActivity {
   // private static final int REQUEST_READ_CONTACTS = 0;

    //private static final String[] DUMMY_CREDENTIALS = new String[]{
    //        "foo@example.com:hello", "bar@example.com:world"
    //};

    EditText mPassword, mEmail;
    TextView btnSignUp;
    Button btnLogin;
    final String loginURL = "http://192.168.100.21/HAR/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // declaring obejct of EditText control
        mEmail = (EditText) findViewById(R.id.email);
        mPassword = (EditText) findViewById(R.id.password);
        btnLogin = (Button)findViewById(R.id.btnLogin);
        btnSignUp = (TextView)findViewById(R.id.btnSignUp);
        btnSignUp.setClickable(true);
        btnLogin.setClickable(true);
        btnSignUp.setClickable(true);
        btnLogin.setEnabled(true);

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
                loginUser(email, password);
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), signup.class);
                startActivity(intent);
            }
        });
    }
    private void loginUser(String mEmail, String mPassword){
        APIRequestData api = retroserver.connectRetrofit().create(APIRequestData.class);
        Call<LoginModel> call = api.login(mEmail, mPassword);
        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {

                int isSuccess = response.body().getIsSuccess();
                if (response.isSuccessful()) {
                    //Toast.makeText(getApplicationContext(), isSucess, MainActivity.class);
                    if (isSuccess == 1) {
                        //get email
                        String email = response.body().getEmail();
                        //storing nama di PrefShared
                        PrefConfig.getInstance(LoginActivity.this).storeEmail(email);
                        Toast.makeText(getApplicationContext(), response.body().getEmail(), Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), Home.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                } else{
                    Toast.makeText(getApplicationContext(), "Some ERROR occured", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }

        });

        }
       // mLoginFormView = findViewById(R.id.login);
    // mProgressView = findViewById(R.id.login_progress);
    }
