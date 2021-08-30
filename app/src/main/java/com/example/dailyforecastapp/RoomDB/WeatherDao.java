package com.example.dailyforecastapp.RoomDB;

import com.example.dailyforecastapp.Models.WeatherModel;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface WeatherDao {

    @Query("SELECT * FROM weather_table WHERE city = :city")
    LiveData<List<WeatherModel>> getWeather(String city);

    @Insert
    void insert(WeatherModel weatherModel);

    @Update
    void update(WeatherModel weatherModel);

    @Query("DELETE FROM weather_table WHERE city = :city")
    void delete(String city);
}
