package com.weather.forecast.service;

import java.io.IOException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.stereotype.Service;

@Service
public class OpenWeatherMap {

    private static final long serialVersionUID = 1L;
    private String apiBase = "http://api.openweathermap.org/data/2.5/weather?q=";
    private String apiForecast = "http://api.openweathermap.org/data/2.5/forecast?q=";
    private String units = "imperial"; // metric
    private String lang = "en";
    private String apiKey = "d2929e9483efc82c82c32ee7e02d563e";

    public JSONObject fetch(String location, int nbPeriod) throws IOException, InterruptedException, ParseException, ParseException {
        String apiUrl = apiForecast + URLEncoder.encode(location, "utf-8") + "&appid=" + apiKey + "&mode=json&units=" + units + "&lang=" + lang + "&cnt=" + nbPeriod;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(response.body());

        return json;
    }
}
