package models;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {

        private String transactionId;
        private String accountNumber;
        private double amount;
        private String transactionType;
        private String timestamp;

        // Constructor
        public Transaction(String transactionId, String accountNumber, double amount, String transactionType) {
            this.transactionId = transactionId;
            this.accountNumber = accountNumber;
            this.amount = amount;
            this.transactionType = transactionType;
            this.timestamp = getCurrentTimestamp();
        }

        // Method to get the current timestamp
        private String getCurrentTimestamp() {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            return now.format(formatter);
        }

        // Method to display transaction details
        public void getTransactionDetails() {
            System.out.println("Transaction ID: " + transactionId);
            System.out.println("Account Number: " + accountNumber);
            System.out.println("Amount: " + amount);
            System.out.println("Transaction Type: " + transactionType);
            System.out.println("Timestamp: " + timestamp);
        }

        // Getters and Setters
        public String getTransactionId() {
            return transactionId;
        }

        public void setTransactionId(String transactionId) {
            this.transactionId = transactionId;
        }

        public String getAccountNumber() {
            return accountNumber;
        }

        public void setAccountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public String getTransactionType() {
            return transactionType;
        }

        public void setTransactionType(String transactionType) {
            this.transactionType = transactionType;
        }

        public String getTimestamp() {
            return timestamp;
        }

}
