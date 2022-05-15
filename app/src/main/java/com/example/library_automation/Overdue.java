package com.example.library_automation;

public class Overdue {
    public String duerollno;
    public String duebid;
    public String duedate;
    public String payment_status;
    public int fine;


    public Overdue() {

    }

    public Overdue(String duerollno, String duebid, String duedate, String payment_status, int fine) {
        this.duerollno = duerollno;
        this.duebid = duebid;
        this.duedate = duedate;
        this.payment_status = payment_status;
        this.fine = fine;
    }

    public String getDuerollno() {
        return duerollno;
    }

    public String getDuebid() {
        return duebid;
    }

    public String getDuedate() {

        return duedate;
    }
    public String getPayment_status(){
        return payment_status;
    }
    public int getFine(){
        return fine;
    }

    public void setDuerollno(String duerollno) {
        this.duerollno = duerollno;
    }
    public void setDuebid(String duebid) {
        this.duebid = duebid;
    }
    public void setDuedate(String duedate) {
        this.duedate = duedate;
    }
    public void setPayment_status(String payment_status) {
        this.payment_status = payment_status;
    }
    public void setFine(int fine) {
        this.fine = fine;
    }
}
