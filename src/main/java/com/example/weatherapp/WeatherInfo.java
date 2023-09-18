package com.example.weatherapp;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class WeatherInfo {
    public String weather;
    public double temperature;
    public double temperatureFeelsLike;
    public long pressure;
    public long humidity;
    public long visibility;
    public double windSpeed;
    public String country;
    public String city;

    public void getWeatherInfo() {
        try {
            String apiKey = "b4269571b9c697fc49518b01f5a3d35c";
            String cityName = "Tbilisi";
            String encodedCityName = URLEncoder.encode(cityName, "UTF-8");
            String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=" + encodedCityName + "&appid=" + apiKey;
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                String weatherData = response.toString();
                JSONObject jsonObject = new JSONObject(weatherData);
                System.out.println(weatherData);
                try {
                    //getting info about weather
                    JSONObject mainObject = jsonObject.getJSONObject("main");
                    weather = jsonObject.getJSONArray("weather").getJSONObject(0).getString("description");
                    temperature = mainObject.getDouble("temp") - 273.15;
                    temperatureFeelsLike = mainObject.getDouble("feels_like") - 273.15;
                    pressure = mainObject.getLong("pressure");
                    humidity = mainObject.getLong("humidity");
                    visibility = jsonObject.getLong("visibility");
                    windSpeed = jsonObject.getJSONObject("wind").getDouble("speed");
                    country = jsonObject.getJSONObject("sys").getString("country");
                    city = jsonObject.getString("name");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.err.println("API request failed with response code: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
