package services;

import models.User;

public class AccountService {

    public User login(String username, String password) {

        if ((username.equals("adam") && password.equals("password") || username.equals("betty") && password.equals("password"))) // ***
            return new User(username, null);
        
        return null;
    }
}