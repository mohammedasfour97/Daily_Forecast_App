package com.example.dailyforecastapp;

import android.app.Application;

import com.example.dailyforecastapp.api.ApiComponent;
import com.example.dailyforecastapp.api.AppModule;
import com.example.dailyforecastapp.api.DaggerApiComponent;


public class MyApplication extends Application {

    private static ApiComponent mApiComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mApiComponent = DaggerApiComponent.builder().appModule(new AppModule("https://api.openweathermap.org/data/2.5/",
                this, this))
               .build();


//        weatherDBComponent = DaggerWeatherDBComponent.builder().weatherDatabaseModule
//                (new WeatherDatabaseModule(getApplicationContext())).build();

    }

    public static ApiComponent getmApiComponent() {
        return mApiComponent;
    }
}
