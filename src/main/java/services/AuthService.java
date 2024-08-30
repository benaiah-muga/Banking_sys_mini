package services;
import models.User;
import java.util.HashMap;
import java.util.Map;
public class AuthService {

        private Map<String, User> userDatabase;

        public AuthService() {
            userDatabase = new HashMap<>();
        }

        // Registering  a new user
        public void registerUser(String username, String password, String email) {
            if (userDatabase.containsKey(username)) {
                System.out.println("Username already exists. Please choose another.");
            } else {
                User newUser = new User(username, password, email);
                userDatabase.put(username, newUser);
                System.out.println("User registered successfully.");
            }
        }

        // Login a user
        public void loginUser(String username, String password) {
            User user = userDatabase.get(username);
            if (user != null) {
                user.login(username, password);
            } else {
                System.out.println("User not found. Please register first.");
            }
        }

        // Logout a user
        public void logoutUser(String username) {
            User user = userDatabase.get(username);
            if (user != null) {
                user.logout();
            } else {
                System.out.println("User not found. Please register first.");
            }
        }

        // Changing user password
        public void changeUserPassword(String username, String oldPassword, String newPassword) {
            User user = userDatabase.get(username);
            if (user != null) {
                user.changePassword(oldPassword, newPassword);
            } else {
                System.out.println("User not found. Please register first.");
            }
        }

        // Checking if a user is logged in
        public boolean isUserLoggedIn(String username) {
            User user = userDatabase.get(username);
            return user != null && user.isLoggedIn();
        }

}
