package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.LoginModel;
import model.LoginModel.Role;
import utility.ConnectionManager;

public class UserDao extends GetConnection implements StudentInterface{
	  Connection dbConn = null;
		@Override
		public Connection getDbConnection() throws ClassNotFoundException {
			Connection dbCon = ConnectionManager.getConnection();
			return dbCon;
		}
  
		
		public void storeUserRecord(LoginModel user) throws ClassNotFoundException {
		    dbConn = getDbConnection();
		    String INSERT = "INSERT INTO user(Id, Name, Username, Password, role) VALUES (?, ?, ?, ?, ?)";
		    try {
		        PreparedStatement ps = dbConn.prepareStatement(INSERT);      
		                ps.setLong(1, user.getId());
		                ps.setString(2, user.getName());
		                ps.setString(3, user.getUsername());
		                ps.setString(4, user.getPassword());
		                ps.setString(5,Role.USER.toString());
		                ps.executeUpdate();
		    } catch (SQLException e) {
		        System.out.println("Failed to add records");
		        e.printStackTrace();
		    }
		}

    // Get admin by username
    public LoginModel getByUsername(String username) throws ClassNotFoundException {
        Connection dbConn = getDbConnection();
        LoginModel user = null;
        try {
            String SELECT = "SELECT * FROM user WHERE Username=?";
            PreparedStatement ps = dbConn.prepareStatement(SELECT);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int Id = rs.getInt("Id");
                String Name = rs.getString("Name");
                String Username = rs.getString("Username");
                String Password = rs.getString("Password");
                user = new LoginModel(Id, Name, Username, Password, Role.USER);            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (dbConn != null) {
                    dbConn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }
    
    public long getUserIdByUsername(String username) throws ClassNotFoundException {
        dbConn = getDbConnection();
        long userId = -1; // Default value if user not found
        try {
            String SELECT = "SELECT Id FROM user WHERE Username=?";
            PreparedStatement ps = dbConn.prepareStatement(SELECT);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                userId = rs.getLong("Id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (dbConn != null) {
                    dbConn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return userId;
    }
}
