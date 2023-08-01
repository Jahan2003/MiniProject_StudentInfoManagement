package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.LoginModel;
import model.LoginModel.Role;
import utility.ConnectionManager;

public class AdminDao extends GetConnection implements StudentInterface{
	  Connection dbConn = null;
		@Override
		public Connection getDbConnection() throws ClassNotFoundException {
			Connection dbCon = ConnectionManager.getConnection();
			return dbCon;
		}

    // Get admin by username
    public LoginModel getByUsername(String username) throws ClassNotFoundException {
        Connection dbConn = getDbConnection();
        LoginModel admin = null;
        try {
            String SELECT = "SELECT * FROM admin WHERE Username=?";
            PreparedStatement ps = dbConn.prepareStatement(SELECT);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int Id = rs.getInt("Id");
                String Name = rs.getString("Name");
                String Username = rs.getString("Username");
                String Password = rs.getString("Password");
                admin = new LoginModel(Id, Name, Username, Password,Role.ADMIN);
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
        return admin;
    }
}