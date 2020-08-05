package com.example.har;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("response")
    private String response;

    @SerializedName("nama")
    private String nama;

    public String getResponse(){
        return response;
    }
    public String getName(){
        return nama;
    }

}
