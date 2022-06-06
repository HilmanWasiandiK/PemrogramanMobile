package com.example.latihanapiregres;

import com.google.gson.annotations.SerializedName;

public class RegisterLoginRequest {

	@SerializedName("email")
	private String email;
	@SerializedName("password")
	private String password;

	public RegisterLoginRequest(String email) {
		this.email = email;
	}

	public RegisterLoginRequest(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public String getEmail(){
		return email;
	}

	public String getPassword(){
		return password;
	}

	@Override
 	public String toString(){
		String request = "";
		request += "email: " + this.email + "\n";
		request += "password: " + this.password + "\n";

		return request;
	}
}