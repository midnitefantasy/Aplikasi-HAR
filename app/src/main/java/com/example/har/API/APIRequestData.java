package com.example.har.API;

import com.example.har.Model.LoginModel;
import com.example.har.User;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Field;

public interface APIRequestData {
    //@GET("retrieve.php")
    @POST("login.php")
    @FormUrlEncoded
    Call<LoginModel> login(@Field("email") String email, @Field("password") String password);

    //@GET("login.php")
    //Call<User> performUserLogin(@Query("email") String email, @Query("password") String password);

    //Call<ResponseModel> ardRetrieveData();
}
