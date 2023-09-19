package com.example.weatherapp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class HomeController {
    @FXML
    private RadioButton darkMode;
    @FXML
    private AnchorPane mainAnchorPane;
    @FXML
    private HBox mainHBox;
    @FXML
    private Pane leftSidePane;
    @FXML
    private Pane centerPane;
    @FXML
    private Pane rightSidePane;
    @FXML
    private TextField search;
    @FXML
    private Label weatherText;
    @FXML
    private ImageView weatherIcon;
    @FXML
    private Label temperature;
    @FXML
    private Label temperatureFeelsLike;
    @FXML
    private Label pressure;
    @FXML
    private Label humidity;
    @FXML
    private Label visibility;
    @FXML
    private Label windSpeed;
    @FXML
    private Label country_city;

    @FXML
    private final String whiteColor = "0xf4f4f4ff";
    private final String blackColor = "#000000";
    private final WeatherInfo weatherInfo = new WeatherInfo();

    @FXML
    private void onDarkModeButtonAction() {
        String color = mainAnchorPane.getBackground().getFills().get(0).getFill().toString();
        darkModeColorsChanger(color);
    }

    @FXML
    private void onSearchAction() {
        weatherInfo.getWeatherInfo(search.getText());
        weatherIconChanger();
        weatherText.setText("Weather: " + weatherInfo.weather);
        temperature.setText("Temperature: " + (int) weatherInfo.temperature + "°C");
        temperatureFeelsLike.setText("Feels like: " + (int) weatherInfo.temperatureFeelsLike + "°C");
        pressure.setText("Pressure: " + (int) weatherInfo.pressure);
        humidity.setText("Humidity: " + (int) weatherInfo.humidity + '%');
        visibility.setText("Visibility: " + weatherInfo.visibility + "m");
        windSpeed.setText("Wind speed: " + weatherInfo.windSpeed + "km/h");
        country_city.setText(weatherInfo.country + ": " + weatherInfo.city);
    }

    private void darkModeColorsChanger(String color) {
        if (color.equals(whiteColor)) {
            mainAnchorPane.setStyle("-fx-background-color: " + blackColor);
            darkMode.setStyle("-fx-text-fill: " + "white");
            weatherText.setStyle("-fx-text-fill: " + "white");
            temperature.setStyle("-fx-text-fill: " + "white");
            temperatureFeelsLike.setStyle("-fx-text-fill: " + "white");
            pressure.setStyle("-fx-text-fill: " + "white");
            humidity.setStyle("-fx-text-fill: " + "white");
            visibility.setStyle("-fx-text-fill: " + "white");
            windSpeed.setStyle("-fx-text-fill: " + "white");
            country_city.setStyle("-fx-text-fill: " + "white");
        } else {
            mainAnchorPane.setStyle("-fx-background-color: " + whiteColor);
            darkMode.setStyle("-fx-text-fill: " + blackColor);
            weatherText.setStyle("-fx-text-fill: " + blackColor);
            temperature.setStyle("-fx-text-fill: " + blackColor);
            temperatureFeelsLike.setStyle("-fx-text-fill: " + blackColor);
            pressure.setStyle("-fx-text-fill: " + blackColor);
            humidity.setStyle("-fx-text-fill: " + blackColor);
            visibility.setStyle("-fx-text-fill: " + blackColor);
            windSpeed.setStyle("-fx-text-fill: " + blackColor);
            country_city.setStyle("-fx-text-fill: " + blackColor);
        }
    }

    private void weatherIconChanger() {
        if (weatherInfo.weather.equals("clear sky")) {
            weatherIcon.setImage(new Image("https://cdn.dribbble.com/users/2120934/screenshots/6193524/19_mostlysunny.gif"));
        } else if (weatherInfo.weather.contains("rain")) {
            weatherIcon.setImage(new Image("https://forums.synfig.org/uploads/default/original/2X/3/31d749625faa93271be23874d416f9be755b7cb9.gif"));
        } else if (weatherInfo.weather.contains("clouds")) {
            weatherIcon.setImage(new Image("https://cdnl.iconscout.com/lottie/premium/thumb/cloudy-moon-windy-5493183-4574028.gif"));
        } else if (weatherInfo.weather.equals("mist") || weatherInfo.weather.equals("drizzle")) {
            weatherIcon.setImage(new Image("https://cdn.dribbble.com/users/2120934/screenshots/6193458/13_snow.gif"));
        }
    }
}