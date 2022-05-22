package com.example.latihanapiregres;

import com.google.gson.annotations.SerializedName;

public class User2 {
    @SerializedName("data")
    private DataItem2 data;

    @SerializedName("support")
    private Support support;

    public DataItem2 getData(){
        return data;
    }
    public Support getSupport(){
        return support;
    }

    @Override
    public String toString(){
        String user = "";
        user += "data: " + this.data + "\n";
        user += "support: \n" + this.support + "\n";

        return user;
    }
}
