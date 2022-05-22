package com.example.latihanapiregres;

import com.google.gson.annotations.SerializedName;

public class DataItem1 {

	@SerializedName("id")
	private int id;
	@SerializedName("email")
	private String email;
	@SerializedName("first_name")
	private String firstName;
	@SerializedName("last_name")
	private String lastName;
	@SerializedName("avatar")
	private String avatar;

	public int getId(){
		return id;
	}

	public String getEmail(){
		return email;
	}

	public String getFirstName(){
		return firstName;
	}

	public String getLastName(){
		return lastName;
	}

	public String getAvatar(){
		return avatar;
	}

	@Override
 	public String toString(){
		String data = "";
		data += "\n\t\tid: " + this.id + "\n";
		data += "\t\temail: " + this.email + "\n";
		data += "\t\tfirst name: " + this.firstName +"\n";
		data += "\t\tlast name: " + this.lastName + "\n";
		data += "\t\tavatar: " + this.avatar + "\n";
		return data;
		}
}