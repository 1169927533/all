package com.example.a11699.all.mvpstudy.weather_mvp.presenter.impl;

import com.example.a11699.all.mvpstudy.weather_mvp.model.Weather;
import com.example.a11699.all.mvpstudy.weather_mvp.model.dao.WeatherModel;
import com.example.a11699.all.mvpstudy.weather_mvp.model.daoImp.WeatherModelImp;
import com.example.a11699.all.mvpstudy.weather_mvp.presenter.OnWeatherListener;
import com.example.a11699.all.mvpstudy.weather_mvp.presenter.WeatherPresenter;
import com.example.a11699.all.mvpstudy.weather_mvp.ui.dao.WeatherView_dao;


/**
 * Created by Administrator on 2015/2/6.
 * 天气 Prestener实现
 */

/**
 * 这个类要实现通过城市id查询天气
 * 然后实现网络监听
 */
public class WeatherPresenterImpl implements WeatherPresenter, OnWeatherListener {
	/*Presenter作为中间层，持有View和Model的引用*/
	private WeatherView_dao weatherView;
	private WeatherModel weatherModel;

	public WeatherPresenterImpl(WeatherView_dao weatherView) {
		this.weatherView = weatherView;
		weatherModel = new WeatherModelImp();
	}

	@Override
	public void getWeather(String cityNO) {
		weatherView.showLoading();
		weatherModel.loadWeather(cityNO, this);
	}

	@Override
	public void onSuccess(Weather weather) {
		weatherView.hideLoading();
		weatherView.setWeatherinfo(weather);
	}

	@Override
	public void onError() {
		weatherView.hideLoading();
		weatherView.showError();
	}
}
