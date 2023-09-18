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
        WeatherInfo weatherInfo = new WeatherInfo();
        weatherInfo.getWeatherInfo();
        FXMLLoader fxmlLoader = new FXMLLoader(WeatherApp.class.getResource("home.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}