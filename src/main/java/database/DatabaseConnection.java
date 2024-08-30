package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

        // SQLite connection URL
        private static final String SQLITE_URL = "jdbc:sqlite:banking_sys_mini.db";

        // PostgreSQL connection details (uncomment if using PostgreSQL)
        // private static final String POSTGRES_URL = "jdbc:postgresql://localhost:5432/banking_system";
        // private static final String POSTGRES_USER = "yourUsername";
        // private static final String POSTGRES_PASSWORD = "yourPassword";

        public static Connection connect() {
            Connection conn = null;
            try {
                // SQLite connection
                conn = DriverManager.getConnection(SQLITE_URL);

                // PostgreSQL connection (uncomment to use PostgreSQL)
                // conn = DriverManager.getConnection(POSTGRES_URL, POSTGRES_USER, POSTGRES_PASSWORD);

                System.out.println("Connected to the database successfully.");
            } catch (SQLException e) {
                System.out.println("Connection failed: " + e.getMessage());
            }
            return conn;
        }
}
