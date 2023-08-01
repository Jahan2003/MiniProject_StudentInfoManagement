package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.StudentAcadModel;
import utility.ConnectionManager;

public class StudentAcadDao extends GetConnection {

    Connection dbConn = null;

    @Override
    public Connection getDbConnection() throws ClassNotFoundException {
        Connection dbCon = ConnectionManager.getConnection();
        return dbCon;
    }

    // Insert a single student academic record
    public void insertStudentAcad(StudentAcadModel studentAcadModel) throws ClassNotFoundException {
        dbConn = getDbConnection();
        String INSERT = "INSERT INTO student_acad(StudentId, Department, Section, CGPA, JoiningYear, PassingYear) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = dbConn.prepareStatement(INSERT);
            ps.setLong(1, studentAcadModel.getStudentId());
            ps.setString(2, studentAcadModel.getDepartment());
            ps.setString(3, studentAcadModel.getSection());
            ps.setFloat(4, studentAcadModel.getCgpa());
            ps.setInt(5, studentAcadModel.getJoiningYear());
            ps.setInt(6, studentAcadModel.getPassingYear());
            ps.executeUpdate();
            System.out.println("Student Academic Record Added Successfully");
        } catch (SQLException e) {
            System.out.println("Failed to add student academic record");
            e.printStackTrace();
        }
    }

    // Update a single student academic record
    public void updateStudentAcadField(long studentId, int update, String newValue) throws ClassNotFoundException {
        dbConn = getDbConnection();
        String UPDATE_FIELD = null;
        switch (update) {
            case 4:
                UPDATE_FIELD = "UPDATE student_acad SET Department = ? WHERE StudentId = ?";
                break;
            case 5:
                UPDATE_FIELD = "UPDATE student_acad SET JoiningYear = ? WHERE StudentId = ?";
                break;
            case 6:
                UPDATE_FIELD = "UPDATE student_acad SET CGPA = ? WHERE StudentId = ?";
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
                System.out.println("Student Academic Record Updated");
            } else {
                System.out.println("Failed to Update Student Academic Record");
            }
        } catch (SQLException e) {
            System.out.println("Failed to Update Student Academic Record");
            e.printStackTrace();
        }
    }

    // View all student academic records
    public ArrayList<StudentAcadModel> viewStudentAcad() throws ClassNotFoundException {
        ArrayList<StudentAcadModel> studentAcadRecords = new ArrayList<>();
        dbConn = getDbConnection();
        String VIEW_ALL = "SELECT * FROM student_acad";
        try {
            Statement stmt = dbConn.createStatement();
            ResultSet rs = stmt.executeQuery(VIEW_ALL);
            while (rs.next()) {
                long studentId = rs.getLong("StudentId");
                String department = rs.getString("Department");
                String section = rs.getString("Section");
                float cgpa = rs.getFloat("CGPA");
                int joiningYear = rs.getInt("JoiningYear");
                int passingYear = rs.getInt("PassingYear");
                StudentAcadModel acadModel = new StudentAcadModel(studentId, department, section, cgpa, joiningYear, passingYear);
                studentAcadRecords.add(acadModel);
            }
        } catch (SQLException e) {
            System.out.println("Failed to view student academic records");
            e.printStackTrace();
        }
        return studentAcadRecords;
    }
    //view by id
    public StudentAcadModel viewStudentAcad(long studentId) throws ClassNotFoundException {
        dbConn = getDbConnection();
        String VIEW_BY_ID = "SELECT * FROM student_acad WHERE StudentId = ?";
        try {
            PreparedStatement ps = dbConn.prepareStatement(VIEW_BY_ID);
            ps.setLong(1, studentId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String department = rs.getString("Department");
                String section = rs.getString("Section");
                float cgpa = rs.getFloat("CGPA");
                int joiningYear = rs.getInt("JoiningYear");
                int passingYear = rs.getInt("PassingYear");
                StudentAcadModel acadModel = new StudentAcadModel(studentId, department, section, cgpa, joiningYear, passingYear);
                return acadModel;
            } else {
//                System.out.println("No student academic record found with ID: " + studentId);
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Failed to view student academic record");
            e.printStackTrace();
            return null;
        }
    }
    
    public ArrayList<StudentAcadModel> viewStudentAcad(String department) throws ClassNotFoundException {
        ArrayList<StudentAcadModel> studentAcadRecords = new ArrayList<>();
        dbConn = getDbConnection();
        String VIEW_BY_DEPARTMENT = "SELECT * FROM student_acad WHERE Department = ?";
        try {
            PreparedStatement ps = dbConn.prepareStatement(VIEW_BY_DEPARTMENT);
            ps.setString(1, department);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                long studentId = rs.getLong("StudentId");
                String section = rs.getString("Section");
                float cgpa = rs.getFloat("CGPA");
                int joiningYear = rs.getInt("JoiningYear");
                int passingYear = rs.getInt("PassingYear");
                StudentAcadModel acadModel = new StudentAcadModel(studentId, department, section, cgpa, joiningYear, passingYear);
                studentAcadRecords.add(acadModel);
            }
        } catch (SQLException e) {
            System.out.println("Failed to view student academic records by department");
            e.printStackTrace();
        }
        return studentAcadRecords;
    }
}
