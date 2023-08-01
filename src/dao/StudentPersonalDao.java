package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.StudentPersonalModel;
import utility.ConnectionManager;

public class StudentPersonalDao extends GetConnection {

    Connection dbConn = null;

    @Override
    public Connection getDbConnection() throws ClassNotFoundException {
        Connection dbCon = ConnectionManager.getConnection();
        return dbCon;
    }

    // Insert a single student personal record
    public void insertStudentPersonal(StudentPersonalModel studentPersonalModel) throws ClassNotFoundException {
        dbConn = getDbConnection();
        String INSERT = "INSERT INTO student_personal(StudentId, Name, dob, Address, MobileNumber) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = dbConn.prepareStatement(INSERT);
            ps.setLong(1, studentPersonalModel.getStudentId());
            ps.setString(2, studentPersonalModel.getName());
            ps.setString(3, studentPersonalModel.getDob());
            ps.setString(4, studentPersonalModel.getAddress());
            ps.setString(5, studentPersonalModel.getMobileNumber());
            ps.executeUpdate();
            System.out.println("Student Personal Record Added Successfully");
        } catch (SQLException e) {
            System.out.println("Failed to add student personal record");
            e.printStackTrace();
        }
    }

    // Update a single student personal record
    public void updateStudentPersonalField(long studentId, int update, String newValue) throws ClassNotFoundException {
        dbConn = getDbConnection();
        String UPDATE_FIELD = null;
        switch (update) {
            case 1:
                UPDATE_FIELD = "UPDATE student_personal SET Name = ? WHERE StudentId = ?";
                break;
            case 2:
                UPDATE_FIELD = "UPDATE student_personal SET dob = ? WHERE StudentId = ?";
                break;
            case 3:
                UPDATE_FIELD = "UPDATE student_personal SET Address = ? WHERE StudentId = ?";
                break;
            default:
                System.out.println("Invalid update option");
                return;
        }
        try {
            PreparedStatement ps = dbConn.prepareStatement(UPDATE_FIELD);
            ps.setString(1, newValue);
            ps.setLong(2, studentId);
            int result = ps.executeUpdate();
            if (result == 1) {
                System.out.println("Student Personal Record Updated");
            } else {
                System.out.println("Failed to Update Student Personal Record");
            }
        } catch (SQLException e) {
            System.out.println("Failed to Update Student Personal Record");
            e.printStackTrace();
        }
    }

    // View all student personal records
    public ArrayList<StudentPersonalModel> viewStudentPersonal() throws ClassNotFoundException {
        ArrayList<StudentPersonalModel> studentPersonalRecords = new ArrayList<>();
        dbConn = getDbConnection();
        String VIEW_ALL = "SELECT * FROM student_personal";
        try {
            Statement stmt = dbConn.createStatement();
            ResultSet rs = stmt.executeQuery(VIEW_ALL);
            while (rs.next()) {
                long studentId = rs.getLong("StudentId");
                String name = rs.getString("Name");
                String dob = rs.getString("dob");
                String address = rs.getString("Address");
                String mobileNumber = rs.getString("MobileNumber");
                StudentPersonalModel personalModel = new StudentPersonalModel(studentId, name, dob, address, mobileNumber);
                studentPersonalRecords.add(personalModel);
            }
        } catch (SQLException e) {
            System.out.println("Failed to view student personal records");
            e.printStackTrace();
        }
        return studentPersonalRecords;
    }
    //view by id
    public StudentPersonalModel viewStudentPersonal(long studentId) throws ClassNotFoundException {
        dbConn = getDbConnection();
        String VIEW_BY_ID = "SELECT * FROM student_personal WHERE StudentId = ?";
        try {
            PreparedStatement ps = dbConn.prepareStatement(VIEW_BY_ID);
            ps.setLong(1, studentId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String name = rs.getString("Name");
                String dob = rs.getString("dob");
                String address = rs.getString("Address");
                String mobileNumber = rs.getString("MobileNumber");
                StudentPersonalModel personalModel = new StudentPersonalModel(studentId, name, dob, address, mobileNumber);
                return personalModel;
            } else {
//                System.out.println("No student record found with ID: " + studentId);
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Failed to view student personal record");
            e.printStackTrace();
            return null;
        }
    }
}
