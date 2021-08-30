package com.example.dailyforecastapp.api;

import android.app.Application;
import android.content.Context;

import com.example.dailyforecastapp.RoomDB.WeatherDatabase;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import androidx.room.Room;
import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {

    String mBaseUrl;
    Application application;
    Context context;

    public AppModule(String mBaseUrl, Application application, Context context) {
        this.application = application;
        this.mBaseUrl = mBaseUrl;
        this.context = context;
    }

    @Singleton
    @Provides
    AppModule apiModule(){

        return this;
    }
    @Singleton
    @Provides
    Cache provideHttpCache(Application application) {
        int cacheSize = 10 * 1024 * 1024;
        Cache cache = new Cache(application.getCacheDir(), cacheSize);
        return cache;
    }

    @Singleton
    @Provides
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Singleton
    @Provides
    OkHttpClient provideOkhttpClient(Cache cache) {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.cache(cache);
        return client.build();
    }

    @Singleton
    @Provides
    public Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(mBaseUrl)
                .client(okHttpClient)
                .build();
    }

    public Retrofit getRetrofit(){

        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(provideGson()))
                .baseUrl(mBaseUrl)
                .client(provideOkhttpClient(provideHttpCache(application)))
                .build();
    }

    public synchronized WeatherDatabase getInstance() {

        return Room.databaseBuilder(context,
                WeatherDatabase.class, "weather_database")
                .fallbackToDestructiveMigration()
                .build();
    }
}
