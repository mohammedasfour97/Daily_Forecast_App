package com.example.dailyforecastapp.Repositories;

import android.os.AsyncTask;

import com.example.dailyforecastapp.BaseClasses.BaseRepository;
import com.example.dailyforecastapp.Models.WeatherApiResponse;
import com.example.dailyforecastapp.Models.WeatherModel;
import com.example.dailyforecastapp.api.RestApiService;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepository extends BaseRepository {

    private MutableLiveData<List<WeatherModel>> weatherApiResponseMutableLiveData;
    private WeatherModel[] weatherModel;
    private List<WeatherModel> weatherModelList;

    public MainRepository() {
        super();
    }

    public MutableLiveData<List<WeatherModel>> getWeatherApiResponseMutableLiveData(String city) {

        weatherApiResponseMutableLiveData = new MutableLiveData<>();

        RestApiService restApiService = getRetrofit().create(RestApiService.class);
        Call<WeatherApiResponse> weatherApiResponseCall = restApiService.getWeatherList(city,"metric",
                "ebc78396b5256568776a2fcae5da933a");

        weatherModel = new WeatherModel[1];
        weatherModelList = new ArrayList<>();

        weatherApiResponseCall.enqueue(new Callback<WeatherApiResponse>() {
            @Override
            public void onResponse(Call<WeatherApiResponse> call, Response<WeatherApiResponse> response) {

                WeatherApiResponse[] weatherApiResponse = new WeatherApiResponse[1];

                weatherApiResponse[0] = response.body();

                if (weatherApiResponse[0] != null && weatherApiResponse[0].getList() != null){

                    com.example.dailyforecastapp.Models.List weatherApi;

                    for (int a = 0; a < weatherApiResponse[0].getList().size(); a++){

                        weatherApi = weatherApiResponse[0].getList().get(a);
                        weatherModel[0] = new WeatherModel(city,String.valueOf(Math.round(weatherApi.getMain().getTemp())),
                                String.valueOf(Math.round(weatherApi.getMain().getTempMin())),
                                String.valueOf(Math.round(weatherApi.getMain().getTempMax())),
                                weatherApi.getWeather().get(0).getDescription(), weatherApi.getDtTxt());
                        weatherModelList.add(weatherModel[0]);

                        new UpdateWeatherAsyncTask(city).execute();
                    }


                }

                else {

                    weatherModelList.add(weatherModel[0]);
                    weatherApiResponseMutableLiveData.postValue(weatherModelList);

                    new SelectWeatherAsyncTask(city).execute();

                }

                weatherApiResponseMutableLiveData.postValue(weatherModelList);
            }

            @Override
            public void onFailure(Call<WeatherApiResponse> call, Throwable t) {

                weatherModelList.add(weatherModel[0]);
                weatherApiResponseMutableLiveData.postValue(weatherModelList);

                new SelectWeatherAsyncTask(city).execute();
            }
        });

        return weatherApiResponseMutableLiveData;
    }


    private class InsertWeatherAsyncTask extends AsyncTask<WeatherModel, Void, Void> {

        private WeatherModel weatherModel;

        public InsertWeatherAsyncTask(WeatherModel weatherModel) {
            this.weatherModel = weatherModel;
        }

        @Override
        protected Void doInBackground(WeatherModel... weatherModels) {
            getWeatherDao().insert(weatherModel);
            return null;
        }

    }


    private class UpdateWeatherAsyncTask extends AsyncTask<Void, Void, Void>  {

        private String city;
        private LiveData<List<WeatherModel>> weatherModelList;

        public UpdateWeatherAsyncTask(String city) {
            this.city = city;
        }


        @Override
        protected Void doInBackground(Void... voids) {

            weatherModelList = getWeatherDao().getWeather(city);
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);

            if (weatherModelList.getValue() != null && !weatherModelList.getValue().isEmpty())
                getWeatherDao().delete(city);

            for (WeatherModel weatherModel : MainRepository.this.weatherModelList){

                new InsertWeatherAsyncTask(weatherModel).execute();
            }

        }

    }

    private class SelectWeatherAsyncTask extends AsyncTask<Void, Void, Void> {

        private String city;
        private LiveData<List<WeatherModel>> weatherModelList;

        public SelectWeatherAsyncTask(String city) {
            this.city = city;
        }


        @Override
        protected Void doInBackground(Void... voids) {

            weatherModelList = getWeatherDao().getWeather(city);
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);

            if (weatherModelList.getValue() != null && !weatherModelList.getValue().isEmpty())
                weatherApiResponseMutableLiveData.postValue(weatherModelList.getValue());
            else{

                MainRepository.this.weatherModelList.add(weatherModel[0]);
                weatherApiResponseMutableLiveData.postValue(MainRepository.this.weatherModelList);
            }

        }
    }
}
