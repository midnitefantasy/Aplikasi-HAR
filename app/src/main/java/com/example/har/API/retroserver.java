package com.example.har.API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class retroserver {
    private static final String baseURL = "http://10.0.2.2:80/";
    private static Retrofit retro;
    private static Gson gson;

    public static Retrofit connectRetrofit(){
        if(retro == null){
            gson = new GsonBuilder()
                    .setLenient()
                    .create();
             retro = new Retrofit.Builder()
                     .baseUrl(baseURL)
                     .addConverterFactory(GsonConverterFactory.create(gson))
                     .build();
        }
        return retro;
    }
}
