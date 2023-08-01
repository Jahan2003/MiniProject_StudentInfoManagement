package model;

public class StudentPersonalModel {
    private long studentId;
    private String name;
    private String dob;
    private String address;
    private String mobileNumber;

    public StudentPersonalModel(long studentId, String name, String dob, String address, String mobileNumber) {
        this.studentId = studentId;
        this.name = name;
        this.dob = dob;
        this.address = address;
        this.mobileNumber = mobileNumber;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public StudentPersonalModel() {
        // Default constructor
    }
}
