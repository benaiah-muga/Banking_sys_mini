package services;
import models.Account;
import models.Transaction;
import java.util.HashMap;
import java.util.Map;
public class AccountService {

        private Map<String, Account> accountDatabase;
        private services.TransactionService transactionService;

        public AccountService(services.TransactionService transactionService) {
            this.accountDatabase = new HashMap<>();
            this.transactionService = transactionService;
        }

        // Create a new account
        public void createAccount(String accountHolderName, double initialBalance) {
            String accountNumber = generateAccountNumber();
            Account newAccount = new Account(accountNumber, accountHolderName, initialBalance);
            newAccount.save();  // Save the account to the database
        }

    public void deposit(String accountNumber, double amount) {
        Account account = Account.findByAccountNumber(accountNumber);  // Retrieve from database
        if (account != null) {
            account.deposit(amount);
            Transaction transaction = new Transaction(
                    Transaction.generateTransactionId(),
                    accountNumber,
                    amount,
                    "Deposit"
            );
            transaction.save();  // Save the transaction to the database
        }
    }

    // Withdraw money from an account
        public void withdraw(String accountNumber, double amount) {
            Account account = accountDatabase.get(accountNumber);
            if (account != null) {
                if (account.getBalance() >= amount) {
                    account.withdraw(amount);
                    transactionService.recordTransaction(accountNumber, amount, "Withdrawal");
                    System.out.println("Withdrawal successful. New balance: " + account.getBalance());
                } else {
                    System.out.println("Insufficient balance.");
                }
            } else {
                System.out.println("Account not found.");
            }
        }

        // Transfer money between accounts
        public void transfer(String fromAccountNumber, String toAccountNumber, double amount) {
            Account fromAccount = accountDatabase.get(fromAccountNumber);
            Account toAccount = accountDatabase.get(toAccountNumber);

            if (fromAccount != null && toAccount != null) {
                if (fromAccount.getBalance() >= amount) {
                    fromAccount.withdraw(amount);
                    toAccount.deposit(amount);
                    transactionService.recordTransaction(fromAccountNumber, amount, "Transfer to " + toAccountNumber);
                    transactionService.recordTransaction(toAccountNumber, amount, "Transfer from " + fromAccountNumber);
                    System.out.println("Transfer successful. New balance of " + fromAccountNumber + ": " + fromAccount.getBalance());
                } else {
                    System.out.println("Insufficient balance.");
                }
            } else {
                System.out.println("One or both accounts not found.");
            }
        }

    // Helper method to generate unique account numbers
    private String generateAccountNumber() {
        return "ACC" + (accountDatabase.size() + 1);
    }
}


