package controller;

import java.util.ArrayList;
import java.util.Scanner;

import dao.AdminDao;
import dao.StudentAcadDao;
import dao.StudentPersonalDao;
import dao.UserDao;
import model.LoginModel;
import model.StudentAcadModel;
import model.StudentPersonalModel;
import service.AdminService;
import service.StudentAcadService;
import service.StudentPersonalService;
import service.UserService;


public class MainController{

    public static void main(String[] args) throws ClassNotFoundException {
        StudentPersonalService ad = new StudentPersonalService();
        StudentAcadService ar = new StudentAcadService();
        StudentPersonalDao studentPersonalDao = new StudentPersonalDao();
        StudentAcadDao studentAcadDao=new StudentAcadDao();
        System.out.println("Welcome to Student Information Management System");
        boolean login = true;
        System.out.println("\n1)Admin Login \n2)User Login \n3)Exit");
        System.out.print("\nEnter your choice: ");
        while (login) {
            Scanner sc = new Scanner(System.in);
            int option = Integer.parseInt(sc.nextLine());
            switch (option) {
                case 1: {
                	AdminDao adminDao = new AdminDao();
                	AdminService adminservice = new AdminService();
                    System.out.print("\nAdmin UserName: ");
                    String username = sc.nextLine();
                    System.out.print("Admin Password:");
                    String password = sc.nextLine();
                    boolean isAuthenticated = adminservice.authenticateAdmin(username, password);
                    if (isAuthenticated) {
                        System.out.println("Successfully Logged In");
                        boolean temp = true;
                        while (temp) {
                            System.out.println("Choose the action to be performed");
                            System.out.println("\n 1) Add student \n 2) View All students \n 3) View student by Id \n 4) View students by Department \n 5) Update student \n 6) Exit");
                            boolean flag = true;
                            int operation = 0;
                            while (flag) {
                                try {
                                	System.out.print("\nEnter your choice: ");
                                    operation = Integer.parseInt(sc.nextLine());
                                    if(operation<1 || operation >6) {
                                    	System.out.println("Enter correct Number");
                                    	continue;
                                    }
                                    flag = false;
                                } catch (Exception e) {
                                    System.out.println("Enter correct Number");
                                    flag = true;
                                }
                            }
                            switch (operation) {
                                ///ADD Student///
                                case 1: {
                                    System.out.print("\nNumber of Students to be added: ");
                                    int adminAdd = Integer.parseInt(sc.nextLine());
                                    long studentId = 0;
                                    String name, dob, address,mobile,sec;
                                    int joinYear,passYear;
                                    float cgpa;
                                    
                                    for (int i = 0; i < adminAdd; i++) {
                                        boolean id = true;
                                        while (id) {
                                            try {
                                                System.out.println("StudentId:");
                                                studentId = Long.parseLong(sc.nextLine());
                                                id = false;
                                            } catch (NumberFormatException e) {
                                                System.out.println("Enter Long Integer Type only");
                                                id = true;
                                            }
                                        }
                                        System.out.println("Name:");
                                        name = sc.nextLine();
                                        System.out.println("Date of Birth (dob):");
                                        dob = sc.nextLine();
                                        System.out.println("Address:");
                                        address = sc.nextLine();
                                        System.out.println("Mobile Number:");
                                        mobile = sc.nextLine();
                                       

                                        StudentPersonalModel student = new StudentPersonalModel(studentId,name,dob,address,mobile);
                                        ad.addStudentPersonalRecord(student);
                                        
                                        String uname=name;
                                        String pass=name+"@"+studentId;
                                        
                                        System.out.println("\nDepartment:");
                                        name = sc.nextLine();
                                        System.out.println("Section:");
                                        sec = sc.nextLine();
                                        System.out.println("Joining Year:");
                                        joinYear = sc.nextInt();
                                        System.out.println("Passing Year:");
                                        passYear = sc.nextInt();
                                        System.out.println("Cgpa:");
                                        cgpa= sc.nextFloat();
                                        
                                        StudentAcadModel acad=new StudentAcadModel(studentId,name,sec,cgpa,joinYear,passYear);
                                        ar.addStudentAcadRecord(acad);
                                        
                                        
                                        UserDao userDao=new UserDao();
                                        LoginModel user=new LoginModel();
                                        user.setId(student.getStudentId());
                                        user.setName(student.getName());
                                        user.setUsername(uname);
                                        user.setPassword(pass);
                                        
                                        userDao.storeUserRecord(user);
                                        sc.nextLine();
                	                	System.out.println("Student record added successfully");

                                    }
                                    break;
                                }
                                ////View Students///
                                case 2: {
                                    StudentPersonalDao studentPersonalDao1 = new StudentPersonalDao();

                                    // Fetch personal and academic details
                                    ArrayList<StudentPersonalModel> personalRecords = studentPersonalDao1.viewStudentPersonal();
                                    ArrayList<StudentAcadModel> acadRecords = studentAcadDao.viewStudentAcad();

                                    System.out.println("+--------------+------------------+------------+-----------------------+------------------+--------------+--------------+--------+---------------+--------------+");
                                    System.out.println("| Student ID   | Name             | DOB        | Address               | Mobile Number    | Department   | Section      | CGPA   | Joining Year  | Passing Year |");
                                    System.out.println("+--------------+------------------+------------+-----------------------+------------------+--------------+-------------+--------+---------------+---------------+");

                                    for (StudentPersonalModel personalRecord : personalRecords) {
                                        System.out.printf("| %-12s | %-16s | %-10s | %-21s | %-16s |", personalRecord.getStudentId(), personalRecord.getName(), personalRecord.getDob(), personalRecord.getAddress(), personalRecord.getMobileNumber());

                                        // Search for the academic details of the current student
                                        boolean academicFound = false;
                                        for (StudentAcadModel acadRecord : acadRecords) {
                                            if (acadRecord.getStudentId() == personalRecord.getStudentId()) {
                                                System.out.printf(" %-12s | %-12s | %-6s | %-14s | %-11s |", acadRecord.getDepartment(), acadRecord.getSection(), acadRecord.getCgpa(), acadRecord.getJoiningYear(), acadRecord.getPassingYear());
                                                academicFound = true;
                                                break;
                                            }
                                        }

                                        if (!academicFound) {
                                            System.out.printf(" %-13s | %-12s | %-6s | %-14s | %-11s |", "", "", "", "", "");
                                        }

                                        System.out.println();
                                    }
                                    System.out.println("+--------------+------------------+------------+-----------------------+------------------+--------------+-------------+--------+---------------+---------------+");
                                    break;
                                }

                                //////////Update Student/////
                                case 5: {
                                	System.out.println("\nEnter the Id to Update:");
                                	long updateStudentId = Long.parseLong(sc.nextLine());

                                	// Check if the student record exists with the given StudentId
                                	boolean studentExists = true;
                                	if (!studentExists) {
                                	    System.out.println("No student record found with ID: " + updateStudentId);
                                	    System.out.println();
                                	    break;
                                	} else {
                                	    boolean flag1 = true;
                                	    System.out.println("\nSelect any one of them to update:");
                                	    try {
                                	        while (flag1) {
                                	            System.out.println("\n 1) Name \n 2) Date of Birth (dob) \n 3) Address \n 4) Mobile Number \n 5) Department \n 6) Section \n 7) Cgpa \n 8) Joining year \n 9) Passing Year \n 10) Exit");
                                	            System.out.print("Enter your choice(1-10): ");
                                	            int update = Integer.parseInt(sc.nextLine());
                                	            switch (update) {
                                	            case 1: {
                                	                System.out.println("\nEnter student's Name to update");
                                	                String newName = sc.nextLine();
                                	                studentPersonalDao.updateStudentPersonalField(updateStudentId, update, newName);
                                	                break;
                                	            }
                                	            case 2: {
                                	                System.out.println("\nEnter student's Date of Birth (dob) to update");
                                	                String newDob = sc.nextLine();
                                	                studentPersonalDao.updateStudentPersonalField(updateStudentId, update, newDob);
                                	                break;
                                	            }
                                	            case 3: {
                                	                System.out.println("\nEnter student's Address to update");
                                	                String newAddress = sc.nextLine();
                                	                studentPersonalDao.updateStudentPersonalField(updateStudentId, update, newAddress);
                                	                break;
                                	            }
                                	            case 4: {
                                	                System.out.println("\nEnter student's Mobile Number to update");
                                	                String newMob = sc.nextLine();
                                	                studentPersonalDao.updateStudentPersonalField(updateStudentId, update, newMob);
                                	                break;
                                	            }
                                	            case 5: {
                                	                System.out.println("\nEnter student's Department to update");
                                	                String newDept = sc.nextLine();
                                	                studentAcadDao.updateStudentAcadField(updateStudentId, update, newDept);
                                	                break;
                                	            }
                                	            case 6: {
                                	                System.out.println("\nEnter student's Section to update");
                                	                String newSec = sc.nextLine();
                                	                studentAcadDao.updateStudentAcadField(updateStudentId, update, newSec);
                                	                break;
                                	            }
                                	            case 7: {
                                	                System.out.println("\nEnter student's Section to Cgpa");
                                	                String newcgpa = sc.nextLine();
                                	                studentAcadDao.updateStudentAcadField(updateStudentId, update, newcgpa);
                                	                break;
                                	            }
                                	            case 8: {
                                	                System.out.println("\nEnter student's Joining Year");
                                	                String newJoin = sc.nextLine();
                                	                studentAcadDao.updateStudentAcadField(updateStudentId, update, newJoin);
                                	                break;
                                	            }
                                	            case 9: {
                                	                System.out.println("\nEnter student's Passing Year");
                                	                String newPass = sc.nextLine();
                                	                studentAcadDao.updateStudentAcadField(updateStudentId, update, newPass);
                                	                break;
                                	            }

                                	                case 10: {
                                	                	System.out.println("Student record with ID: " + updateStudentId+" has been updated");
                                	                    flag1 = false;
                                	                    break;
                                	                }
                                	            }
                                	        }
                                	    } catch (NumberFormatException e) {
                                	        System.out.println("\nEnter the correct number");
                                	        flag1 = true;
                                	    }
                                	    break; 
                                	}
                                }

                                case 3: {
                                	System.out.print("\nEnter the student Id :");
                                	long Id = Long.parseLong(sc.nextLine());
                                    StudentPersonalDao studentPersonalDao1 = new StudentPersonalDao();

                                    // Fetch personal and academic details
                                    StudentPersonalModel personalRecord = studentPersonalDao1.viewStudentPersonal(Id);
                                    StudentAcadModel acadRecord = studentAcadDao.viewStudentAcad(Id);

                                    if (personalRecord != null && acadRecord != null) {
                                    System.out.println("+--------------+------------------+------------+-----------------------+------------------+--------------+--------------+--------+---------------+--------------+");
                                    System.out.println("| Student ID   | Name             | DOB        | Address               | Mobile Number    | Department   | Section      | CGPA   | Joining Year  | Passing Year |");
                                    System.out.println("+--------------+------------------+------------+-----------------------+------------------+--------------+-------------+--------+---------------+---------------+");

                                    
                                        System.out.printf("| %-12s | %-16s | %-10s | %-21s | %-16s |", personalRecord.getStudentId(), personalRecord.getName(), personalRecord.getDob(), personalRecord.getAddress(), personalRecord.getMobileNumber());

                                        // Search for the academic details of the current student
                                        boolean academicFound = false;
                                        
                                            if (acadRecord.getStudentId() == personalRecord.getStudentId()) {
                                                System.out.printf(" %-12s | %-12s | %-6s | %-14s | %-11s |", acadRecord.getDepartment(), acadRecord.getSection(), acadRecord.getCgpa(), acadRecord.getJoiningYear(), acadRecord.getPassingYear());
                                                academicFound = true;
                                                
                                            }
                                        

                                        if (!academicFound) {
                                            System.out.printf(" %-13s | %-12s | %-6s | %-14s | %-11s |", "", "", "", "", "");
                                        }

                                        System.out.println();
                                        System.out.println("+--------------+------------------+------------+-----------------------+------------------+--------------+-------------+--------+---------------+---------------+");
                                        break;
                                    }else {
                                    	System.out.println("No Student Records Found");
                                    	break;
                                    }
                                }
                                case 4: {
                                	System.out.print("\nEnter the Department Name:");
                                	String dept = sc.nextLine();
                                    StudentPersonalDao studentPersonalDao1 = new StudentPersonalDao();

                                    // Fetch personal and academic details
                                    ArrayList<StudentAcadModel> acadRecords = studentAcadDao.viewStudentAcad(dept);
                                    ArrayList<StudentPersonalModel> personalRecords = studentPersonalDao1.viewStudentPersonal();

                                    if (acadRecords.size()>0) {
                                    System.out.println("+--------------+------------------+------------+-----------------------+------------------+--------------+--------------+--------+---------------+--------------+");
                                    System.out.println("| Student ID   | Name             | DOB        | Address               | Mobile Number    | Department   | Section      | CGPA   | Joining Year  | Passing Year |");
                                    System.out.println("+--------------+------------------+------------+-----------------------+------------------+--------------+-------------+--------+---------------+---------------+");
                                    

                                    for (StudentPersonalModel personalRecord : personalRecords) {

                                        // Search for the academic details of the current student
                                        boolean academicFound = false;
                                        for (StudentAcadModel acadRecord : acadRecords) {
                                            if (acadRecord.getStudentId() == personalRecord.getStudentId()) {
                                            	System.out.printf("| %-12s | %-16s | %-10s | %-21s | %-16s | %-12s | %-12s | %-6s | %-14s | %-11s |", personalRecord.getStudentId(), personalRecord.getName(), personalRecord.getDob(), personalRecord.getAddress(), personalRecord.getMobileNumber(), acadRecord.getDepartment(), acadRecord.getSection(), acadRecord.getCgpa(), acadRecord.getJoiningYear(), acadRecord.getPassingYear());
                                                academicFound = true;
                                                break;
                                            }
                                        }

                                        if (!academicFound) {
                                            System.out.printf(" %-13s | %-12s | %-6s | %-14s | %-11s |", "", "", "", "", "");
                                        }

                                        System.out.println();
                                    }
                                    System.out.println("+--------------+------------------+------------+-----------------------+------------------+--------------+-------------+--------+---------------+---------------+");
                                    break;
                                    }else {
                                    	System.out.println("No Student Records Found with Department "+dept);
                                    	break;
                                    }
                                }
                                case 6: {
                                    temp = false;
                                    System.out.println("Thank you for using the Student Information Management System. Goodbye!");
                                    break;
                                }
                            }

                        }

                    }else {
                    	  System.out.println("Authentication failed. Exiting the program.");
                          return;
                    }
                    break;

                }
                case 2:{
                    UserDao userDao = new UserDao();
                    UserService userservice = new UserService();
                    System.out.println("\nUserName: ");
                    String username = sc.nextLine();
                    System.out.println("Password:");
                    String password = sc.nextLine();
                    boolean isAuthenticated = userservice.authenticateUser(username, password);
                    if (isAuthenticated) {
                        System.out.println("Successfully Logged In");
                        long userid=userservice.getUserIdByUsername(username);
                        boolean temp = true;
                        while (temp) {
                            System.out.println("Choose the action to be performed");
                            System.out.println("\n 1) View Details \n 2) Exit \n ");
                            boolean flag = true;
                            int operation = 0;
                            while (flag) {
                                try {
                                	System.out.print("\nEnter your choice: ");
                                    operation = Integer.parseInt(sc.nextLine());
                                    if(operation<1 || operation >4) {
                                    	System.out.println("Enter correct Number");
                                    	continue;
                                    }
                                    flag = false;
                                } catch (Exception e) {
                                    System.out.println("Enter correct Number");
                                    flag = true;
                                }
                            }
                            switch(operation) {
                            case 1: {
                                StudentPersonalDao studentPersonalDao1 = new StudentPersonalDao();

                                // Fetch personal and academic details
                                StudentPersonalModel personalRecord = studentPersonalDao1.viewStudentPersonal(userid);
                                StudentAcadModel acadRecord = studentAcadDao.viewStudentAcad(userid);

                                if (personalRecord != null && acadRecord != null) {
                                System.out.println("+--------------+------------------+------------+-----------------------+------------------+--------------+--------------+--------+---------------+--------------+");
                                System.out.println("| Student ID   | Name             | DOB        | Address               | Mobile Number    | Department   | Section      | CGPA   | Joining Year  | Passing Year |");
                                System.out.println("+--------------+------------------+------------+-----------------------+------------------+--------------+-------------+--------+---------------+---------------+");

                                
                                    System.out.printf("| %-12s | %-16s | %-10s | %-21s | %-16s |", personalRecord.getStudentId(), personalRecord.getName(), personalRecord.getDob(), personalRecord.getAddress(), personalRecord.getMobileNumber());

                                    // Search for the academic details of the current student
                                    boolean academicFound = false;
                                    
                                        if (acadRecord.getStudentId() == personalRecord.getStudentId()) {
                                            System.out.printf(" %-12s | %-12s | %-6s | %-14s | %-14s |", acadRecord.getDepartment(), acadRecord.getSection(), acadRecord.getCgpa(), acadRecord.getJoiningYear(), acadRecord.getPassingYear());
                                            academicFound = true;
                                            
                                        }
                                    

                                    if (!academicFound) {
                                        System.out.printf(" %-13s | %-12s | %-6s | %-14s | %-14s |", "", "", "", "", "");
                                    }

                                    System.out.println();
                                    System.out.println("+--------------+------------------+------------+-----------------------+------------------+--------------+-------------+--------+---------------+---------------+");
                                    break;
                                }else {
                                	System.out.println("No Student Record Found with Id: "+userid);
                                	break;
                                }
                            }
                         
                            case 2: {
                            	temp = false;
                                System.out.println("Thank you for using the Student Information Management System. Goodbye!");
                                break;
                            }
                            
                            }
                        }
                    } else {
                        System.out.println("Authentication failed. Exiting the program.");
                        // Don't close the Scanner here after failed authentication
                        // sc.close();
                        return;
                    }
                    // Close the Scanner here after successful authentication
                    
                    break;
                }

                case 3: {
                    login = false;
                    System.out.println("Exiting..");
                    break;
                }
            }
        }
    }

}
