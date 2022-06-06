package com.example.latihanapiregres;

import com.google.gson.annotations.SerializedName;

public class RegisterResponse{

	@SerializedName("id")
	private int id;
	@SerializedName("token")
	private String token;

	public int getId(){
		return id;
	}

	public String getToken(){
		return token;
	}

	@Override
 	public String toString(){
		String response = "";
		response += "id: " + this.id + "\n";
		response += "token: " + this.token + "\n";

		return response;
		}
}