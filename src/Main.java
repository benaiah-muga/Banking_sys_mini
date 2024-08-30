import models.Account;
import services.AccountService;
import services.AuthService;
import services.TransactionService;

public class Main {
    public static void main(String[] args) {
        Account bella = new Account("24084987", "Bella Muganzi", 200_000 );
        bella.deposit(100_000);
        bella.displayAccountDetails();
    }
}