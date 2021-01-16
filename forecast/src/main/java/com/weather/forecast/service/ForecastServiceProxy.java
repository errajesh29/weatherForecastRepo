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
    OpenWeatherMap owm ;
    ForecastServiceProxy() throws InterruptedException, ParseException, IOException {

       // prepareForecastData();

    }

    public List<String> cityList = new ArrayList<>();

    public List<String> getCityList() {
        cityList.add("London");
        cityList.add("US");
        cityList.add("India");
        cityList.add("Japan");
        cityList.add("Korea");

        return cityList;
    }


    public ForecastModel getForecastData() throws InterruptedException, ParseException, IOException, net.minidev.json.parser.ParseException {
        //OpenWeatherMap owm = new OpenWeatherMap();
        JSONObject jsonData = owm.fetch("london", 3);
        ForecastModel weatherModel = new ForecastModel();
        weatherModel.setCity(jsonData.getAsString("city"));

        List<Weather> weathers = new ArrayList<>();
        Object list = jsonData.get("list");

        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(jsonData.getAsString("list"));



        for(int i = 0; i < jsonArray.size(); i++)
        {
            Weather weather = new Weather();
            JSONObject jsonItem = (JSONObject)parser.parse (jsonArray.get(i).toString());// .get(i); //.getJSONArray(i);
            weather.setDate(jsonItem.get("dt_txt").toString());

            JSONObject jsonMain = (JSONObject)parser.parse (jsonItem.get("main").toString());
            weather.setMinTemp(jsonMain.get("temp_min").toString());
            weather.setMaxTemp(jsonMain.get("temp_max").toString());

            JSONArray jsonWeather = (JSONArray)parser.parse (jsonItem.get("weather").toString());

            JSONObject jsonInnerMain = (JSONObject)parser.parse (jsonWeather.get(0).toString());

            weather.setDesc(jsonInnerMain.get("main").toString());
            weathers.add(weather);
        }

        weatherModel.setWeather(weathers);

        return weatherModel;
    }

//    public List<String> getCities() {
//        //http request to get cities
//    }
//
//    public List<Weather> getWeather(String city) {
//        // http request to get weather
//    }

}
