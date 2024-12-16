package com.example.atmprojectgui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class ATM extends Application {

    private double balance = 1000.00; // Default balance for the user
    private String pin = "1234"; // Default PIN for the user
    private boolean isAuthenticated = true; // To track if the user is authenticated

    private TextField pinField;
    private Label statusLabel;
    private Label balanceLabel;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("ATM Application");

        // Create the main layout
        BorderPane root = new BorderPane();

        // Create a scene and set the root layout
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);

        // Pin Input Section
        VBox pinBox = new VBox(10);
        pinBox.setPadding(new Insets(20));

        Label pinLabel = new Label("Enter your PIN:");
        pinField = new TextField();
        pinField.setPromptText("PIN");
        pinField.setMaxWidth(150);
        Button loginButton = new Button("Login");

        loginButton.setOnAction(e -> User());

        pinBox.getChildren().addAll(pinLabel, pinField, loginButton);

        // ATM Operations Section (Initially Hidden)
        VBox operationsBox = new VBox(10);
        operationsBox.setPadding(new Insets(20));
        operationsBox.setVisible(false); // Hide until logged in

        Button withdrawButton = new Button("Withdraw");
        Button depositButton = new Button("Deposit");
        Button checkBalanceButton = new Button("Check Balance");
        Button transferButton = new Button("Transfer");
        Button logoutButton = new Button("Logout");

        statusLabel = new Label();
        balanceLabel = new Label("Balance: $1000.00");

        withdrawButton.setOnAction(e -> withdrawMoney());
        depositButton.setOnAction(e -> depositMoney());
        checkBalanceButton.setOnAction(e -> checkBalance());
        transferButton.setOnAction(e -> transferMoney());
        logoutButton.setOnAction(e -> logout());

        operationsBox.getChildren().addAll(
                balanceLabel,
                withdrawButton,
                depositButton,
                checkBalanceButton,
                transferButton,
                statusLabel,
                logoutButton
        );

        // Display the login UI first
        root.setCenter(pinBox);

        // After login, show operations box
        root.setBottom(operationsBox);

        // Show the UI
        primaryStage.show();
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

    private void withdrawMoney() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Withdraw Money");
        dialog.setHeaderText("Enter amount to withdraw:");

        dialog.showAndWait().ifPresent(amount -> {
            try {
                double withdrawAmount = Double.parseDouble(amount);
                if (withdrawAmount > 0 && withdrawAmount <= balance) {
                    balance -= withdrawAmount;
                    statusLabel.setText("Successfully withdrawn $" + withdrawAmount);
                    balanceLabel.setText("Balance: $" + balance);
                } else {
                    statusLabel.setText("Insufficient balance or invalid amount.");
                }
            } catch (NumberFormatException e) {
                statusLabel.setText("Invalid amount entered.");
            }
        });
    }

    private void depositMoney() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Deposit Money");
        dialog.setHeaderText("Enter amount to deposit:");

        dialog.showAndWait().ifPresent(amount -> {
            try {
                double depositAmount = Double.parseDouble(amount);
                if (depositAmount > 0) {
                    balance += depositAmount;
                    statusLabel.setText("Successfully deposited $" + depositAmount);
                    balanceLabel.setText("Balance: $" + balance);
                } else {
                    statusLabel.setText("Invalid deposit amount.");
                }
            } catch (NumberFormatException e) {
                statusLabel.setText("Invalid amount entered.");
            }
        });
    }

    private void checkBalance() {
        statusLabel.setText("Current Balance: $" + balance);
    }

    private void transferMoney() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Transfer Money");
        dialog.setHeaderText("Enter amount to transfer:");

        dialog.showAndWait().ifPresent(amount -> {
            try {
                double transferAmount = Double.parseDouble(amount);
                if (transferAmount > 0 && transferAmount <= balance) {
                    // Simulate transfer (can be to another account, etc.)
                    balance -= transferAmount;
                    statusLabel.setText("Successfully transferred $" + transferAmount);
                    balanceLabel.setText("Balance: $" + balance);
                } else {
                    statusLabel.setText("Insufficient balance or invalid amount.");
                }
            } catch (NumberFormatException e) {
                statusLabel.setText("Invalid amount entered.");
            }
        });
    }

    private void logout() {
        isAuthenticated = false;
        balance = 1000.00; // Reset to default balance
        pinField.clear();
        pinField.setPromptText("PIN");
        statusLabel.setText("Logged out.");
        balanceLabel.setText("Balance: $1000.00");
    }
}
