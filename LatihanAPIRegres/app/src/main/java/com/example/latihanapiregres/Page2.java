package com.example.latihanapiregres;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Page2{

	@SerializedName("page")
	private int page;
	@SerializedName("per_page")
	private int perPage;
	@SerializedName("total")
	private int total;
	@SerializedName("total_pages")
	private int totalPages;
	@SerializedName("data")
	private List<DataItem2> data;
	@SerializedName("support")
	private Support support;

	public int getPage(){
		return page;
	}

	public int getPerPage(){
		return perPage;
	}

	public int getTotal(){
		return total;
	}

	public int getTotalPages(){
		return totalPages;
	}

	public List<DataItem2> getData(){
		return data;
	}

	public Support getSupport(){
		return support;
	}

	@Override
	public String toString(){
		String page = "";
		page += "page: " + this.page + "\n";
		page += "per_page: " + this.perPage + "\n";
		page += "total: " + this.total + "\n";
		page += "total pages: " + this.totalPages + "\n";
		page += "data: \n" + this.data + "\n";
		page += "support: \n" + this.support +"\n\n";
		return page;
	}
}