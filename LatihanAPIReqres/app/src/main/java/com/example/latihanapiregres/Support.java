package com.example.latihanapiregres;

import com.google.gson.annotations.SerializedName;

public class Support{

	@SerializedName("url")
	private String url;
	@SerializedName("text")
	private String text;

	public String getUrl(){
		return url;
	}

	public String getText(){
		return text;
	}

	@Override
 	public String toString() {
		String support = "";
		support += "\t\turl: " + this.url + "\n";
		support += "\t\ttext: " + this.text + "\n";

		return support;
	}
}