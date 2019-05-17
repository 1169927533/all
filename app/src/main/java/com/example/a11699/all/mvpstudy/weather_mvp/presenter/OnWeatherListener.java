package com.example.a11699.all.mvpstudy.weather_mvp.presenter;

import com.example.a11699.all.mvpstudy.weather_mvp.model.Weather;



/**
 * Created by Administrator on 2015/2/7.
 * 在Presenter层实现，给Model层回调，更改View层的状态，确保Model层不直接操作View层
 */
public interface OnWeatherListener {
    /**
     * 成功时回调
     *
     * @param weather
     */
    void onSuccess(Weather weather);
    /**
     * 失败时回调，简单处理，没做什么
     */
    void onError();

}
