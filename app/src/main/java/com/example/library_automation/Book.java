package com.example.library_automation;

public class Book {
    private String bid;
    private String bname;
    private String author;
    private String isbn;
    private String numcopy;
    private String genre;
    private String location;
    private String publication;

    public Book() {}
    public Book(String bid,String bname){
        this.bid = bid;
        this.bname=bname;
    }
    public Book(String author){
        //this.bid = bid;
        this.author=author;
    }
    public Book(String bid,String bname,String isbn) {
        this.bid = bid;
        this.bname=bname;
        this.isbn = isbn;
    }
    public Book(String bid,String bname,String author,String isbn,String numcopy) {
        this.bid = bid;
        this.bname = bname;
        this.author = author;
        this.isbn = isbn;
        this.numcopy = numcopy;
    }
    public Book(String bid,String bname,String author,String isbn,String numcopy,String genre,String location,String publication ) {
        this.bid = bid;
        this.bname=bname;
        this.author = author;
        this.isbn = isbn;
        this.numcopy=numcopy;
        this.genre = genre;
        this.location = location;
        this.publication = publication;
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

    public void setBid(String bid) {
        this.bid = bid;
    }
    public void setBname(String bname) {
        this.bname = bname;
    }
    public void setAuthor(String rollno) {
        this.author=author;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public void setNumcopy(String numcopy) {
        this.numcopy = numcopy;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public void setLocation(String location) {
        this.genre = location;
    }
    public void setPublication(String publication) {
        this.publication = publication;
    }

    public String getBid() {
        return bid;
    }
    public String getBname() {
        return bname;
    }
    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }
    public String getNumcopy() {
        return numcopy;
    }
    public String getGenre() {
        return genre;
    }
    public String getLocation() {
        return location;
    }
    public String getPublication() {
        return publication;
    }

}
