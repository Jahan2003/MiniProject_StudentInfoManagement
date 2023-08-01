package service;

import dao.AdminDao;
import model.LoginModel;

public class AdminService {
    private AdminDao adminDao = new AdminDao();

    // Authenticate admin based on username and password
    public boolean authenticateAdmin(String username, String password) throws ClassNotFoundException {
        LoginModel admin = adminDao.getByUsername(username);
        if (admin != null && admin.getPassword().equals(password)) {
            return true; // Authentication successful
        }
        return false; // Authentication failed
    }
}