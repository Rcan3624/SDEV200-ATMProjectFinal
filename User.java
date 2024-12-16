package com.example.atmprojectgui;

import java.util.Scanner;

public class User {
    // Method to deposit money
    public void depositMoney() {    // Method changed to public temporarily to test out User methods via TestUser
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter amount to deposit: $");
        double amount = scanner.nextDouble();
        if (amount > 0) {
            System.out.println("Deposited $" + amount + ". Your new balance is $" + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    // Method to withdraw money
    public void withdrawMoney() {           // Method changed to public temporarily to test out User methods via TestUser
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter amount to withdraw: $");
        double amount = scanner.nextDouble();
        double balance = 0;
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrew $" + amount + ". Your new balance is $" + balance);
        } else if (amount > balance) {
            System.out.println("Insufficient balance.");
        } else {
            System.out.println("Invalid withdrawal amount.");
        }

    }
}
