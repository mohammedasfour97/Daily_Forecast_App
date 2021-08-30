package com.example.dailyforecastapp.api;

import com.example.dailyforecastapp.BaseClasses.BaseRepository;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface ApiComponent {
    void inject(BaseRepository baseRepository);
}
