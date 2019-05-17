package com.example.a11699.all.mvpstudy.weather_mvp.model.dao;

import com.example.a11699.all.mvpstudy.weather_mvp.presenter.OnWeatherListener;

/**
 * 作者：余智强
 * 2019/3/17
 */
public interface WeatherModel {
    void loadWeather(String cityNO, OnWeatherListener listener);
}
