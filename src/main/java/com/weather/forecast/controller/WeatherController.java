package com.weather.forecast.controller;

import com.weather.forecast.model.ForecastModel;
import com.weather.forecast.service.ForecastServiceProxy;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;


@Controller
public class WeatherController {


    @Autowired
    ForecastServiceProxy weatherService;

    @RequestMapping(value = "/{city}")
    public String index(@PathVariable String city, Model model) throws IOException, InterruptedException, ParseException, net.minidev.json.parser.ParseException {

        ForecastModel result = weatherService.getForecastData(city);
        if (result.listWeather == null ) {
            model.addAttribute("city", "City Not Found");
            model.addAttribute("error", result.getErrorMessage());
        } else {
            model.addAttribute("forecast", result.listWeather);
            model.addAttribute("city", result.city);
        }
        return "index";
    }
}
