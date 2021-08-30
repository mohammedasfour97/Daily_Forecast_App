package com.example.dailyforecastapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import retrofit2.Retrofit;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import com.awesomedialog.blennersilva.awesomedialoglibrary.interfaces.Closure;
import com.example.dailyforecastapp.Adapters.WeatherAdapter;
import com.example.dailyforecastapp.BaseClasses.BaseActivity;
import com.example.dailyforecastapp.Models.WeatherApiResponse;
import com.example.dailyforecastapp.Models.WeatherModel;
import com.example.dailyforecastapp.ViewModels.MainViewModel;
import com.example.dailyforecastapp.databinding.ActivityMainBinding;
import com.google.inject.Inject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private MainViewModel mainViewModel;
    private ActivityMainBinding activityMainBinding;
    private Observer<List<WeatherModel>> listObserver;

    /// vars for recycler
    private List<WeatherModel> weatherModelList;
    private WeatherAdapter weatherAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        initUI();
        initObservers();
    }


    private void initUI(){

        //// configure recyclerview ////

        weatherModelList = new ArrayList<>();
        weatherAdapter = new WeatherAdapter(weatherModelList , this);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        activityMainBinding.weatherRecycler.recyclerview.setLayoutManager(mLayoutManager);
        activityMainBinding.weatherRecycler.recyclerview.setAdapter(weatherAdapter);


        //// configure search box ////

        activityMainBinding.searchBar.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if(actionId== EditorInfo.IME_ACTION_SEARCH){

                    loadWeather();
                }
                return false;
            }
        });


        activityMainBinding.searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loadWeather();
            }
        });

    }


    private void initObservers(){

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        listObserver = new Observer<List<WeatherModel>>() {
            @Override
            public void onChanged(List<WeatherModel> weatherApiResponse) {

                if (weatherApiResponse!= null){

                    if (getLifecycle().getCurrentState()== Lifecycle.State.RESUMED){

                        hideProgress();

                        if (weatherApiResponse.get(0) !=null){

                            activityMainBinding.mainFillImg.setVisibility(View.GONE);
                            activityMainBinding.weatherRecycler.recyclerview.setVisibility(View.VISIBLE);

                            weatherModelList.addAll(weatherApiResponse);
                            weatherAdapter.notifyDataSetChanged();
                        }

                        else
                            showFailedDialogWithCustomButton(getResources().getString(R.string.no_data),
                                    getResources().getString(R.string.retry), new Closure() {
                                        @Override
                                        public void exec() {

                                            hideFailedDialog();
                                            loadWeather();
                                        }
                                    } ,true);
                    }


                }
            }
        };
    }


    private void loadWeather(){

        if (TextUtils.isEmpty(activityMainBinding.searchBar.getText()))
            Toast.makeText(MainActivity.this, getResources().getString(R.string.enter_city_name),
                    Toast.LENGTH_SHORT).show();
        else {

            loadWeatherData(activityMainBinding.searchBar.getText().toString());

            activityMainBinding.searchBar.clearFocus();
            hideKeyboard();
        }
    }



    private void loadWeatherData(String city){

        if (hasInternetConnection()){

            showProgressDialog(getResources().getString(R.string.loading), getResources().getString(R.string.loading_weather_msg),
                    false);
            mainViewModel.getWeatherApiResponseMutableLiveData(city).observe(this, listObserver);
        }

    }
}