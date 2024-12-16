package com.example.atmprojectgui;

import java.util.Scanner;

public class User {
    private String pin;
    private Account account;

    public User(String pin, double initialBalance) {
        this.pin = pin;
        this.account = new Account(initialBalance);
    }

    public String getPin() {
        return pin;
    }

    public Account getAccount() {
        return account;
    }

    public boolean authenticate(String enteredPin) {
        return pin.equals(enteredPin);
    }
}

