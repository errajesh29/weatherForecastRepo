package com.weather.forecast.service;

import com.weather.forecast.model.Weather;
import com.weather.forecast.model.ForecastModel;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ForecastServiceProxy {

    @Autowired
    OpenWeatherMap owm;
    ForecastServiceProxy() throws InterruptedException, ParseException, IOException {
    }

    public ForecastModel getForecastData(String city) throws InterruptedException, ParseException, IOException, net.minidev.json.parser.ParseException {
        //OpenWeatherMap owm = new OpenWeatherMap();
        JSONObject jsonData = owm.fetch(city, 20);
        ForecastModel weatherModel = new ForecastModel();
        JSONParser parser = new JSONParser();

        if( jsonData.size() == 2) {
           // JSONArray jsonError = (JSONArray) parser.parse(jsonData.toString());
            weatherModel.setErrorMessage(jsonData.toString());
            weatherModel.listWeather = null;
         } else {

            JSONObject jsonCity = (JSONObject) parser.parse(jsonData.getAsString("city"));
            weatherModel.setCity(jsonCity.getAsString("name"));

            List<Weather> weathers = new ArrayList<>();

            JSONArray jsonArray = (JSONArray) parser.parse(jsonData.getAsString("list"));

            int lastDate = -1;
            int dayCount = 0;
            for (int i = 0; i < jsonArray.size(); i++) {
                if ( dayCount == 3) {
                    break;
                }
                Weather weather = new Weather();
                JSONObject jsonItem = (JSONObject) parser.parse(jsonArray.get(i).toString());
                String strDate = jsonItem.get("dt_txt").toString();
                weather.setDate(strDate);

                int ttDate = Integer.parseInt(strDate.substring(8,10));
                if (ttDate != lastDate) {
                    dayCount++;
                    lastDate = ttDate;
                } else {
                    continue;
                }

                JSONObject jsonMain = (JSONObject) parser.parse(jsonItem.get("main").toString());
                weather.setMinTemp(jsonMain.get("temp_min").toString());
                weather.setMaxTemp(jsonMain.get("temp_max").toString());

                JSONArray jsonWeather = (JSONArray) parser.parse(jsonItem.get("weather").toString());

                JSONObject jsonInnerMain = (JSONObject) parser.parse(jsonWeather.get(0).toString());

                weather.setDesc(jsonInnerMain.get("main").toString());
                weathers.add(weather);
            }

            weatherModel.setWeather(weathers);
        }

        return weatherModel;
    }

}
