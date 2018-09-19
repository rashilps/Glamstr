package com.example.rashil.glamstr;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("image")
    private String Image;


    @SerializedName("response")
    private String Response;


    @SerializedName("name")
    private String Name;

    public String getResponse() {
        return Response;
    }

    public String getName() {
        return Name;
    }
}
