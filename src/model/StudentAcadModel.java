package model;

public class StudentAcadModel {
    private long studentId;
    private String department;
    private String section;
    private float cgpa;
    private int joiningYear;
    private int passingYear;

    public StudentAcadModel(long studentId, String department, String section, float cgpa, int joiningYear, int passingYear) {
        this.studentId = studentId;
        this.department = department;
        this.section = section;
        this.cgpa = cgpa;
        this.joiningYear = joiningYear;
        this.passingYear = passingYear;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public float getCgpa() {
        return cgpa;
    }

    public void setCgpa(float cgpa) {
        this.cgpa = cgpa;
    }

    public int getJoiningYear() {
        return joiningYear;
    }

    public void setJoiningYear(int joiningYear) {
        this.joiningYear = joiningYear;
    }

    public int getPassingYear() {
        return passingYear;
    }

    public void setPassingYear(int passingYear) {
        this.passingYear = passingYear;
    }

    public StudentAcadModel() {
        // Default constructor
    }
}
