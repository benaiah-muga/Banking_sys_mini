import database.DatabaseConnection;
import database.DatabaseInitializer;
import models.Account;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        // Step 1: Connect to the database
        Connection conn = DatabaseConnection.connect();

        // Step 2: Initialize the database tables
        if (conn != null) {
            DatabaseInitializer.initializeDatabase(conn);
        }

        // Step 3: Proceed with your application logic
        Account bella = new Account("24084987", "Bella Muganzi", 200_000);
        bella.deposit(100_000);
        bella.displayAccountDetails();
    }
}
