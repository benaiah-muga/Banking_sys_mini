package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DatabaseConnection {

    private static final String SQLITE_URL = "jdbc:sqlite:banking_sys_mini.db";

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(SQLITE_URL);
            System.out.println("Connected to the database successfully.");
            initializeDatabase(conn);
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
        return conn;
    }

    private static void initializeDatabase(Connection conn) {
        try (Statement stmt = conn.createStatement()) {
            BufferedReader reader = new BufferedReader(new FileReader("init.sql"));
            String line;
            while ((line = reader.readLine()) != null) {
                stmt.execute(line);
            }
            reader.close();
            System.out.println("Database initialized successfully.");
        } catch (SQLException | IOException e) {
            System.out.println("Failed to initialize database: " + e.getMessage());
        }
    }
}
