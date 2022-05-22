package com.example.latihanapiregres;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("token")
    private String token;

    public String getToken(){
        return token;
    }

    @Override
    public String toString(){
        String response = "";
        response += "token: " + this.token + "\n";

        return response;
    }
}
