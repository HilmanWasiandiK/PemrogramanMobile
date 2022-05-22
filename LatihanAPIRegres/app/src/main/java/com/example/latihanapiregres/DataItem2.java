package com.example.latihanapiregres;

import com.google.gson.annotations.SerializedName;

public class DataItem2 {

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("year")
    private int year;
    @SerializedName("color")
    private String color;
    @SerializedName("pantone_value")
    private String pantone_value;

    public int getId(){
        return id;
    }

    public void setName(String name) { this.name = name; }

    public void setYear(int year) { this.year = year; }

    public void setColor(String color) { this.color = color; }

    public void setPantone_value(String pantone_value) { this.pantone_value = pantone_value; }

    @Override
    public String toString(){
        String data = "";
        data += "\n\t\tid: " + this.id + "\n";
        data += "\t\tname: " + this.name + "\n";
        data += "\t\tyear: " + this.year +"\n";
        data += "\t\tcolor: " + this.color + "\n";
        data += "\t\tpantone value: " + this.pantone_value + "\n";
        return data;
    }
}
