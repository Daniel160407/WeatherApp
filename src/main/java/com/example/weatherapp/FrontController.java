package com.example.weatherapp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class FrontController {
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
    private final String whiteColor = "0xf4f4f4ff";
    private final String blackColor = "#000000";

    @FXML
    private void onDarkModeButtonAction() {
        String color = mainAnchorPane.getBackground().getFills().get(0).getFill().toString();
       darkModeColorsChanger(color);
    }
    private void darkModeColorsChanger(String color){
        if (color.equals(whiteColor)) {
            mainAnchorPane.setStyle("-fx-background-color: " + blackColor);
            darkMode.setStyle("-fx-text-fill: " + whiteColor);
        } else {
            mainAnchorPane.setStyle("-fx-background-color: " + whiteColor);
            darkMode.setStyle("-fx-text-fill: " + blackColor);
        }
    }
}