package com.example.latihanapiregres;

import com.google.gson.annotations.SerializedName;

public class User1 {

	@SerializedName("data")
	private DataItem1 data;

	@SerializedName("support")
	private Support support;

	public DataItem1 getData(){
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