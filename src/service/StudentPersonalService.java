package service;

import java.util.ArrayList;
import dao.StudentPersonalDao;
import model.StudentPersonalModel;

public class StudentPersonalService {

    private final StudentPersonalDao studentPersonalDao;

    public StudentPersonalService() {
        studentPersonalDao = new StudentPersonalDao();
    }

    public void addStudentPersonalRecord(StudentPersonalModel personalModel) throws ClassNotFoundException {
        studentPersonalDao.insertStudentPersonal(personalModel);
    }

  

    public ArrayList<StudentPersonalModel> viewAllStudentPersonalRecords() throws ClassNotFoundException {
        return studentPersonalDao.viewStudentPersonal();
    }
}

