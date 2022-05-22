package com.example.latihanapiregres;

import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class UserCreate {

	@SerializedName("id")
	private String id;
	@SerializedName("name")
	private String name;
	@SerializedName("job")
	private String job;
	@SerializedName("createdAt")
	private String createdAt;

	public UserCreate(String name, String job) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date date = new Date();
		this.name = name;
		this.job = job;
		this.createdAt = formatter.format(date);
	}

	public String getId(){
		return id;
	}

	public String getName(){
		return name;
	}

	public String getJob(){
		return job;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	@Override
 	public String toString(){
		String userCreate = "";
		userCreate += "id: " + this.id + "\n";
		userCreate += "name: " + this.name + "\n";
		userCreate += "job: " + this.job + "\n";
		userCreate += "created at: " + this.createdAt +"\n\n";

		return userCreate;
	}
}