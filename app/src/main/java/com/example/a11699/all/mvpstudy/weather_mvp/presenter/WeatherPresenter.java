package com.example.a11699.all.mvpstudy.weather_mvp.presenter;

/**
 * Created by Administrator on 2015/2/6.
 * 天气 Presenter接口
 */
public interface WeatherPresenter {
    /**
     * 获取天气的逻辑 通过城市id查询天气
     */
    void getWeather(String cityNO);

}
