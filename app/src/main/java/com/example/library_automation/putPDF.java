package com.example.library_automation;

public class putPDF {

    public String fname;
    public String url;

    public putPDF(){

    }
    public putPDF(String fname,String url){
        this.fname=fname;
        this.url=url;
    }
    public String getFname(){
        return fname;
    }
    public void setFname(String fname){
        this.fname=fname;
    }
    public String getUrl(){
        return url;
    }
    public void setUrl(String url){
        this.url=url;
    }
}
