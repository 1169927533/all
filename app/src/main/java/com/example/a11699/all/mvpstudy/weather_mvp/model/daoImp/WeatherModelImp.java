package com.example.a11699.all.mvpstudy.weather_mvp.model.daoImp;

import android.os.Handler;
import android.os.Message;

import com.example.a11699.all.mvpstudy.weather_mvp.model.Weather;
import com.example.a11699.all.mvpstudy.weather_mvp.model.dao.WeatherModel;
import com.example.a11699.all.mvpstudy.weather_mvp.presenter.OnWeatherListener;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 作者：余智强
 * 2019/3/17
 */
public class WeatherModelImp implements WeatherModel {
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            OnWeatherListener listener = (OnWeatherListener) msg.obj;
            if(msg.what == 0){
                listener.onError();
            }else{
                listener.onSuccess(day_object);
            }
        }
    };
    Weather day_object;
    @Override
    public void loadWeather(String cityNO, final OnWeatherListener listener) {
        String url = "http://www.weather.com.cn/data/sk/" + cityNO + ".html";
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder().url(url).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.sendEmptyMessage(0);

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String fds = response.body().string();
                Gson gson = new Gson();
                try {
                    JSONObject jsonObject = new JSONObject(fds);
                    JSONObject jsonArray = jsonObject.getJSONObject("weatherinfo");
                    day_object = gson.fromJson(String.valueOf(jsonArray), Weather.class);
                    Message message = new Message();
                    message.obj = listener;
                    handler.sendMessage(message);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
