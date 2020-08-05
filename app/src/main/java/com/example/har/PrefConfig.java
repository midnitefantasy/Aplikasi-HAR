package com.example.har;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.Intent;
import android.widget.Toast;

public class PrefConfig {
    private SharedPreferences sharedPref;
 //   private Context context;

    /*public PrefConfig(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences (context.getString(R.string.pref_file), Context.MODE_PRIVATE);
    }

    public void writeLoginStatus (boolean status){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(context.getString(R.string.pref_login_status), status);
        editor.commit();
    }

    public boolean readLoginStatus(){
        return sharedPreferences.getBoolean(context.getString(R.string.pref_login_status), false);
    }

    public void writeName(String name){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_user_name),name);
        editor.commit();
    }
    public String readName(){
        return sharedPreferences.getString(context.getString(R.string.pref_user_name), "User");
    }

    public void displayToast(String message){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }

}*/
    //Storage File
    public static final String SHARED_PREF_NAME = "prefLogin";

    //Username
    public static final String email = "email";

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
    public void storeNama(String nama) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(nama, email);
        editor.commit();
    }

    //check if user is logged in
    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = context
                .getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(email, null) != null;
    }


    //find logged in user
    public String LoggedInUser() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(email, null);

    }


    //Logout user
    public void logout() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
        context.startActivity(new Intent(context, MainActivity.class));
    }

}
