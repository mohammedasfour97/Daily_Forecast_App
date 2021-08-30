package com.example.dailyforecastapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.dailyforecastapp.Models.Weather;
import com.example.dailyforecastapp.Models.WeatherModel;
import com.example.dailyforecastapp.R;
import com.example.dailyforecastapp.databinding.ItemWeatherBinding;

import java.util.List;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder> {

    private List<WeatherModel> weatherModelList;
    Context context;


    public static class WeatherViewHolder extends RecyclerView.ViewHolder {

        private ItemWeatherBinding itemWeatherBinding;


        public WeatherViewHolder(ItemWeatherBinding itemWeatherBinding) {
            super(itemWeatherBinding.getRoot());
            this.itemWeatherBinding = itemWeatherBinding;
        }

    }

    public WeatherAdapter(List<WeatherModel> weatherModelList, Context context) {
        this.weatherModelList = weatherModelList;
        this.context = context;

    }

    @Override
    public WeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemWeatherBinding itemWeatherBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_weather, parent, false);
        return new WeatherViewHolder(itemWeatherBinding);

    }


    @Override
    public void onBindViewHolder(WeatherViewHolder holder, int position) {

        WeatherModel weatherModel = weatherModelList.get(position);

        holder.itemWeatherBinding.temperatureText.setText(weatherModel.getTemp() + "º");
        holder.itemWeatherBinding.weatherIndicatorImage.setImageResource(context.getResources().getIdentifier("icon_" +
                weatherModel.getDescription().replace(" ", ""), "drawable", context.getPackageName()));
        holder.itemWeatherBinding.minMaxTemp.setText(context.getResources().getString(R.string.min) + weatherModel.getTemp_min() +
                "   " + context.getResources().getString(R.string.max) + weatherModel.getTemp_max());
        holder.itemWeatherBinding.weatherDescriptionText.setText(weatherModel.getDescription());
        holder.itemWeatherBinding.dataeTimeText.setText(weatherModel.getDt_txt());
    }



    @Override
    public int getItemCount() {
        return weatherModelList.size();
    }
}