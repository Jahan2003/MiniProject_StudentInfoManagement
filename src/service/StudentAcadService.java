package service;

import java.util.ArrayList;
import dao.StudentAcadDao;
import model.StudentAcadModel;

public class StudentAcadService {

    private final StudentAcadDao studentAcadDao;

    public StudentAcadService() {
        studentAcadDao = new StudentAcadDao();
    }

    public void addStudentAcadRecord(StudentAcadModel acadModel) throws ClassNotFoundException {
        studentAcadDao.insertStudentAcad(acadModel);
    }

   

    public ArrayList<StudentAcadModel> viewAllStudentAcadRecords() throws ClassNotFoundException {
        return studentAcadDao.viewStudentAcad();
    }
}
