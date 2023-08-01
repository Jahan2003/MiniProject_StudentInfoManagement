package service;

import dao.UserDao;
import model.LoginModel;

public class UserService {
    private UserDao userDao = new UserDao();

    // Authenticate user based on username and password
    public boolean authenticateUser(String username, String password) throws ClassNotFoundException {
        LoginModel user = userDao.getByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return true; // Authentication successful
        }
        return false; // Authentication failed
    }

    public long getUserIdByUsername(String username) throws ClassNotFoundException {
        return userDao.getUserIdByUsername(username);
    }
}

