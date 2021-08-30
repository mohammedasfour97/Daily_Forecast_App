package com.example.dailyforecastapp.BaseClasses;

import com.example.dailyforecastapp.MyApplication;
import com.example.dailyforecastapp.RoomDB.WeatherDao;
import com.example.dailyforecastapp.RoomDB.WeatherDatabase;
import com.example.dailyforecastapp.api.AppModule;

import javax.inject.Inject;

import retrofit2.Retrofit;

public class BaseRepository {

    @Inject
    AppModule appModule;

    private Retrofit retrofit;
    private WeatherDao weatherDao;

    public BaseRepository(){

       MyApplication.getmApiComponent().inject(this);

       retrofit = appModule.getRetrofit();
       weatherDao = appModule.getInstance().weatherDao();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public WeatherDao getWeatherDao() {
        return weatherDao;
    }
}
