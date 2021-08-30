package com.example.dailyforecastapp.ViewModels;
import com.example.dailyforecastapp.Models.WeatherApiResponse;
import com.example.dailyforecastapp.Models.WeatherModel;
import com.example.dailyforecastapp.Repositories.MainRepository;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    private MainRepository mainRepository;

    public MainViewModel() {

        mainRepository = new MainRepository();
    }

    public MutableLiveData<List<WeatherModel>> getWeatherApiResponseMutableLiveData(String city) {
        return mainRepository.getWeatherApiResponseMutableLiveData(city);
    }
}
