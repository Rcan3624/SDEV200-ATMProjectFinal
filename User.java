package com.example.atmprojectgui;

import javafx.application.Application;
import javafx.stage.Stage;

public class User extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private void User() {
        String enteredPin = pinField.getText();
        if (enteredPin.equals(pin)) {
            isAuthenticated = true;
            statusLabel.setText("Login successful!");
            balanceLabel.setText("Balance: $" + balance);
            pinField.clear();
            pinField.setPromptText("PIN");
            statusLabel.setStyle("-fx-text-fill: green;");
        } else {
            statusLabel.setText("Incorrect PIN! Try again.");
            statusLabel.setStyle("-fx-text-fill: red;");
        }
    }

    @Override
    public void start(Stage primaryStage) {

    }
}
