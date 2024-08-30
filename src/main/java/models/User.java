package models;

public class User {

        private String username;
        private String password;
        private String email;
        private boolean isLoggedIn;

        // Constructor
        public User(String username, String password, String email) {
            this.username = username;
            this.password = password;
            this.email = email;
            this.isLoggedIn = false;
        }

        // Method to handle login
        public void login(String username, String password) {
            if (this.username.equals(username) && this.password.equals(password)) {
                this.isLoggedIn = true;
                System.out.println("Login successful!");
            } else {
                System.out.println("Login failed. Invalid username or password.");
            }
        }

        // Method to handle logout
        public void logout() {
            if (isLoggedIn) {
                isLoggedIn = false;
                System.out.println("Logout successful!");
            } else {
                System.out.println("You are not logged in.");
            }
        }

        // Method to change password
        public void changePassword(String oldPassword, String newPassword) {
            if (this.password.equals(oldPassword)) {
                this.password = newPassword;
                System.out.println("Password changed successfully.");
            } else {
                System.out.println("Incorrect old password.");
            }
        }

        // Method to create a new user
        public static User createNewUser(String username, String password, String email) {
            return new User(username, password, email);
        }

        // Getters and Setters
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public boolean isLoggedIn() {
            return isLoggedIn;
        }
}


