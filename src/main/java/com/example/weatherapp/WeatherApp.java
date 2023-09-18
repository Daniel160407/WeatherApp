package com.example.weatherapp;

import org.json.JSONObject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class WeatherApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            String apiKey = "b4269571b9c697fc49518b01f5a3d35c";
            String cityName = "Paris";
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
                    JSONObject mainObject = jsonObject.getJSONObject("main");
                    double temperatureKelvin = mainObject.getDouble("temp");
                    double temperatureCelsius = temperatureKelvin - 273.15;
                    System.out.println("Temperature in Celsius: " + temperatureCelsius + " °C");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.err.println("API request failed with response code: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        FXMLLoader fxmlLoader = new FXMLLoader(WeatherApp.class.getResource("front.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}