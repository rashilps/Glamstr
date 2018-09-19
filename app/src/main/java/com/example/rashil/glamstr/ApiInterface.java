package com.example.rashil.glamstr;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {


    @FormUrlEncoded
    @POST("upload.php")
    Call<User> uploadImage(@Field("image") String image);


    @GET("register.php")
    Call<User> performRegistration(@Query("name") String Name,
                                   @Query("user_name") String UserName,
                                   @Query("user_password") String UserPassword);


    @GET("login.php")
    Call<User> performLogin(@Query("user_name") String UserName,
                            @Query("user_password") String UserPassword);
}


