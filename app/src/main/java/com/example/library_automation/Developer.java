package com.example.library_automation;

public class Developer {
    public String dname;
    public String demail;
    public String subject;
    public String dmessage;

    public Developer(){
    }
    public Developer(String dname,String demail,String subject,String dmessage){
        this.dname=dname;
        this.demail=demail;
        this.dmessage=dmessage;
        this.subject=subject;
    }

    public String getDname(){
        return dname;
    }
    public void setDname(String fname){
        this.dname=dname;
    }
    public String getDemail(){
        return demail;
    }
    public void setDemail(String demail){
        this.demail=demail;
    }
    public String getSubject(){
        return subject;
    }
    public void setSubject(String subject){
        this.subject=subject;
    }
    public String getDmessage(){
        return dmessage;
    }
    public void setDmessage(String dmessage){
        this.dmessage=dmessage;
    }

}
