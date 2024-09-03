package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {

    public static void initializeDatabase(Connection conn) {
        String createUserTable = "CREATE TABLE IF NOT EXISTS users ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "username TEXT NOT NULL,"
                + "password TEXT NOT NULL,"
                + "email TEXT NOT NULL,"
                + "is_logged_in INTEGER NOT NULL DEFAULT 0"
                + ");";

        String createAccountTable = "CREATE TABLE IF NOT EXISTS accounts ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "account_number TEXT NOT NULL,"
                + "holder_name TEXT NOT NULL,"
                + "balance REAL NOT NULL"
                + ");";

        String createTransactionTable = "CREATE TABLE IF NOT EXISTS transactions ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "transaction_id TEXT NOT NULL,"
                + "account_number TEXT NOT NULL,"
                + "amount REAL NOT NULL,"
                + "transaction_type TEXT NOT NULL,"
                + "timestamp TEXT NOT NULL,"
                + "FOREIGN KEY (account_number) REFERENCES accounts(account_number)"
                + ");";

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(createUserTable);
            stmt.execute(createAccountTable);
            stmt.execute(createTransactionTable);
            System.out.println("Database initialized successfully.");
        } catch (SQLException e) {
            System.out.println("Error initializing database: " + e.getMessage());
        }
    }
}
