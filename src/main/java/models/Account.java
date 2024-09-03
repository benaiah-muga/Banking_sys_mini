package models;

import database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Account {
    private String accountNumber;
    private String accountHolderName;
    private double balance;

    public Account(String accountNumber, String accountHolderName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = initialBalance;
    }

    // Deposit method
    public void deposit(double amount) {
        this.balance += amount;
        updateBalanceInDatabase();  // Update the balance in the database
    }

    // Withdraw method
    public void withdraw(double amount) {
        if (this.balance >= amount) {
            this.balance -= amount;
            updateBalanceInDatabase();  // Update the balance in the database
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    // Getter for balance
    public double getBalance() {
        return this.balance;
    }

    // Method to save the account to the database
    public void save() {
        String sql = "INSERT INTO accounts(account_number, account_holder_name, balance) VALUES(?, ?, ?)";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, this.accountNumber);
            pstmt.setString(2, this.accountHolderName);
            pstmt.setDouble(3, this.balance);
            pstmt.executeUpdate();
            System.out.println("Account saved to database.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Method to find an account by account number
    public static Account findByAccountNumber(String accountNumber) {
        String sql = "SELECT * FROM accounts WHERE account_number = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, accountNumber);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String accountHolderName = rs.getString("account_holder_name");
                double balance = rs.getDouble("balance");
                return new Account(accountNumber, accountHolderName, balance);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    // Update balance in the database
    private void updateBalanceInDatabase() {
        String sql = "UPDATE accounts SET balance = ? WHERE account_number = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, this.balance);
            pstmt.setString(2, this.accountNumber);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Display account details
    public void displayAccountDetails() {
        System.out.println("Account Number: " + this.accountNumber);
        System.out.println("Account Holder Name: " + this.accountHolderName);
        System.out.println("Balance: $" + this.balance);
    }

    // Additional methods like find, delete, etc., can be added here as needed
}
