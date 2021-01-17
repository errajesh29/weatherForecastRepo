package com.weather.forecast.model;

import java.util.List;

public class ForecastModel {

    public String city;
    public String err;
    public List<Weather> listWeather;

    public List<Weather> getWeather(String city) {
        return this.listWeather;
    }

    public void setCity(String value) {
        this.city = value;
    }

    public void setWeather(List<Weather> value) {
        this.listWeather = value;
    }

    public void setErrorMessage(String value) {
        err = value;
    }

    public String getErrorMessage() {
        return err;
    }
}
