package com.example.dailyforecastapp.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "weather_table")
public class WeatherModel {

    @PrimaryKey(autoGenerate = true)
    private int id ;
    @ColumnInfo(name = "city")
    private String city;
    @ColumnInfo(name = "temp")
    private String temp;
    @ColumnInfo(name = "temp_min")
    private String temp_min;
    @ColumnInfo(name = "temp_max")
    private String temp_max;
    @ColumnInfo(name = "description")
    private String description;
    @ColumnInfo(name = "dt_txt")
    private String dt_txt;

    public WeatherModel(String city, String temp, String temp_min, String temp_max, String description, String dt_txt) {
        this.temp = temp;
        this.temp_min = temp_min;
        this.temp_max = temp_max;
        this.description = description;
        this.dt_txt = dt_txt;
        this.city = city;
    }

    public WeatherModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(String temp_min) {
        this.temp_min = temp_min;
    }

    public String getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(String temp_max) {
        this.temp_max = temp_max;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDt_txt() {
        return dt_txt;
    }

    public void setDt_txt(String dt_txt) {
        this.dt_txt = dt_txt;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
