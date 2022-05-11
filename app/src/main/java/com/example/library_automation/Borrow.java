package com.example.library_automation;

public class Borrow {
    private String rollno;
    private String bid;
    private String bname;
    private String issuedate;
    private String duedate;
    public Borrow() {}

    public Borrow(String rollno,String bid) {
        this.rollno = rollno;
        this.bid = bid;
    }
    public Borrow(String rollno,String bid,String bname,String issuedate,String duedate) {
        this.rollno=rollno;
        this.bid = bid;
        this.bname = bname;
        this.issuedate = issuedate;
        this.duedate = duedate;
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
    public void setBname(String bname) {
        this.bname = bname;
    }
    public void setBid(String bid) {
        this.bid = bid;
    }
    public void setRollno(String rollno) {
        this.rollno=rollno;
    }
    public void setIssuedate(String issuedate) {
        this.issuedate = issuedate;
    }
    public void setDuedate(String duedate) {
        this.duedate = duedate;
    }
    public String getBname() {
        return bname;
    }
    public String getBid() {
        return bid;
    }
    public String getRollno() {
        return rollno;
    }
    public String getIssuedate() {
        return issuedate;
    }
    public String getDuedate() {
        return duedate;
    }

}
