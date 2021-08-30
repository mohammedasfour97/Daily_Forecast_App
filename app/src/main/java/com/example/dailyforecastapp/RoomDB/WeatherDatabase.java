package com.example.dailyforecastapp.RoomDB;

import com.example.dailyforecastapp.Models.WeatherModel;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {WeatherModel.class}, version = 1)
public abstract class WeatherDatabase extends RoomDatabase {

    public abstract WeatherDao weatherDao();
}
