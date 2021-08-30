package com.example.dailyforecastapp.api;

import com.example.dailyforecastapp.Models.WeatherApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestApiService {
    @GET("forecast")
    Call<WeatherApiResponse> getWeatherList(@Query("q") String city , @Query("units") String unit, @Query("appid") String appid);
}
