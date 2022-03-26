package com.example.library_automation;


public class User {

    private String rollno;
    private String fname;
    private String lname;
    private String email;
    private String course;
    private String dept;
    private String password;
    private int year;
    private String contact;
    //private String sex;

    public User() {}

    public User(String fname,String lname,String email,String password) {
        this.fname = fname;
        this.lname=lname;
        this.email = email;
        this.password=password;
    }

    public User(String rollno,String fname,String lname,String email,String password,String course,String dept,int year,String contact) {
        this.rollno=rollno;
        this.email = email;
        this.fname = fname;
        this.lname = lname;
        this.course = course;
        this.dept = dept;
        this.year = year;
        this.contact = contact;
        this.password=password;
    }

/*    public HashMap<String, Object> getAsMap(){
        HashMap<String, Object> userAsMap = new HashMap<>();
        userAsMap.put("username",username);
        userAsMap.put("password",password);
        userAsMap.put("age",age);
        userAsMap.put("name",name);
        //Add or remove more key value pair
        return userAsMap;
    }*/

   // public String getSex() { return sex; }
    /*public void setpassword(String password) {
        this.password = password;
    }*/

    public void setFname(String fname) {
        this.fname = fname;
    }
    public void setLname(String lname) {
        this.lname = lname;
    }
    public void setRollno(String rollno) {
        this.rollno=rollno;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setCourse(String course) {
        this.course = course;
    }
    public void setDept(String dept) {
        this.dept = dept;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getFname() {
        return fname;
    }
    public String getLname() {
        return lname;
    }
    public String getRollno() {
        return rollno;
    }

    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public String getCourse() {
        return course;
    }

    public String getDept() {
        return dept;
    }
    public int getYear() {
        return year;
    }
    public String getContact() {
        return contact;
    }

   // public void setSex(String sex) { this.sex = sex; }

    //public String getpassword() {
   //     return password;
    //}
}