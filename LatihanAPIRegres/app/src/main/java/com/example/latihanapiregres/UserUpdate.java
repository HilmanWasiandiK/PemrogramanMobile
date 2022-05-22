package com.example.latihanapiregres;

import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class UserUpdate{

	@SerializedName("name")
	private String name;
	@SerializedName("job")
	private String job;
	@SerializedName("updatedAt")
	private String updatedAt;

	public UserUpdate(String name, String job) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date date = new Date();
		this.name = name;
		this.job = job;
		this.updatedAt = formatter.format(date);
	}

	public String getName(){
		return name;
	}

	public String getJob(){
		return job;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	@Override
 	public String toString(){
		String userUpdate = "";
		userUpdate += "name: " + this.name + "\n";
		userUpdate += "job: " + this.job + "\n";
		userUpdate += "updated at: " + this.updatedAt +"\n\n";

		return userUpdate;
		}
}