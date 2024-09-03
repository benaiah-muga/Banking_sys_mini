package models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class Transaction {

    private String transactionId;
    private String accountNumber;
    private double amount;
    private String transactionType;
    private String timestamp;

    public Transaction(String transactionId, String accountNumber, double amount, String transactionType) {
        this.transactionId = transactionId;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.transactionType = transactionType;
        this.timestamp = getCurrentTimestamp();
    }
    public static String generateTransactionId() {
        return UUID.randomUUID().toString();
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    private String getCurrentTimestamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.now().format(formatter);
    }

    public void save() {
        String sql = "INSERT INTO transactions(transaction_id, account_number, amount, transaction_type, timestamp) VALUES(?,?,?,?,?)";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, this.transactionId);
            pstmt.setString(2, this.accountNumber);
            pstmt.setDouble(3, this.amount);
            pstmt.setString(4, this.transactionType);
            pstmt.setString(5, this.timestamp);
            pstmt.executeUpdate();
            System.out.println("Transaction saved to database.");
        } catch (SQLException e) {
            System.out.println("Error saving transaction: " + e.getMessage());
        }
    }

    // Find a transaction by ID
    public static Transaction find(String transactionId) {
        String sql = "SELECT * FROM transactions WHERE transaction_id = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, transactionId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Transaction(
                        rs.getString("transaction_id"),
                        rs.getString("account_number"),
                        rs.getDouble("amount"),
                        rs.getString("transaction_type")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error finding transaction: " + e.getMessage());
        }
        return null;
    }

    // Delete a transaction by ID
    public void delete() {
        String sql = "DELETE FROM transactions WHERE transaction_id = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, this.transactionId);
            pstmt.executeUpdate();
            System.out.println("Transaction deleted from database.");
        } catch (SQLException e) {
            System.out.println("Error deleting transaction: " + e.getMessage());
        }
    }
}
