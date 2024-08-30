package services;
import models.Transaction;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class TransactionService {
    private List<Transaction> transactionHistory;

    public TransactionService() {
        this.transactionHistory = new ArrayList<>();
    }

    // Record a transaction
    public void recordTransaction(String accountNumber, double amount, String transactionType) {
        // Generate a unique transaction ID
        String transactionId = UUID.randomUUID().toString();

        // Create a new Transaction object with all required parameters
        Transaction transaction = new Transaction(transactionId, accountNumber, amount, transactionType);
        transactionHistory.add(transaction);

        System.out.println("Transaction recorded: " + transactionType + " of " + amount + " for account " + accountNumber);
    }

    // Get all transactions for a specific account
    public List<Transaction> getTransactions(String accountNumber) {
        List<Transaction> accountTransactions = new ArrayList<>();
        for (Transaction transaction : transactionHistory) {
            if (transaction.getAccountNumber().equals(accountNumber)) {
                accountTransactions.add(transaction);
            }
        }
        return accountTransactions;
    }
}
