package com.example.har;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.Intent;
import android.widget.Toast;

import com.example.har.ui.login.LoginActivity;

public class PrefConfig {

    public static final String SHARED_PREF_NAME = "example";

    //Username
    public static final String cEmail = "email";
    public static final String cNama = "nama";

    public static PrefConfig mInstance;

    public static Context context;


    public PrefConfig(Context context) {
        this.context = context;
    }


    public static synchronized PrefConfig getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new PrefConfig(context);
        }
        return mInstance;
    }


    //method to store user data
    public void storeEmail(String email) {
        SharedPreferences sharedPreferences = this.context
                .getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(cEmail, email);
        editor.commit();
    }

    //check if user is logged in
    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = this.context
                .getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(cEmail, null) != null;
    }


    //find logged in user
    public String LoggedInUser() {
        SharedPreferences sharedPreferences = this.context
                .getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(cEmail, null);

    }


    //Logout user
    public void logout() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
        this.context.startActivity(new Intent(this.context, LoginActivity.class));
    }

}
