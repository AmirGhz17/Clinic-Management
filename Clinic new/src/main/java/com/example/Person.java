package com.example;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public abstract class Person implements GlobalFunctions {
    private String firstName;
    private String lastName;
    private String address;
    private String birthDate;
    private long personalID;
    private long ID;
    private String password;
    private long randomID;
    public Person(String firstName, String lastName, String address, String birthDate, long personalID,String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.birthDate = birthDate;
        this.personalID = personalID;
        this.password = password;
    }
    public Person(long id,long randomID,String firstName, String lastName, String address, String birthDate, long personalID,String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.birthDate = birthDate;
        this.personalID = personalID;
        this.password = password;
        this.ID= id;
        this.randomID = randomID;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
    public long getPersonalID() {
        return personalID;
    }
    public void setPersonalID(long personalID) {
        this.personalID = personalID;
    }
    public long getID() {
        return ID;
    }
    public void setID(long iD) {
        ID = iD;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public long getRandomID() {
        return randomID;
    }
    public void setRandomID(long randomID) {
        this.randomID = randomID;
    }
    public void getInfo(Hospital hospital, Stage window){
        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10, 10, 10, 10)); 
        layout.setVgap(10);
        layout.setHgap(10);
        layout.setAlignment(Pos.CENTER);
        Label welcomeMsg= new Label("your information");
        Label nameLabel = new Label("name : ");
        Label IDLabel = new Label("ID : ");
        Label personalIDLabel = new Label("personal ID : ");
        Label randomIDLabel = new Label("random generated ID : ");
        Label birthDateLabel = new Label("birth date : ");
        Label addressLabel = new Label("address : ");
        Label nameField = new Label(this.getFirstName() + " " + this.getLastName());
        Label IDField = new Label(this.getID()+ "");
        Label personalIDField = new Label(this.getPersonalID()+ "");
        Label randomIDField = new Label(this.getRandomID()+ "");
        Label birthDateField = new Label(this.getBirthDate());
        Label addressField = new Label(this.getAddress());
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> this.menu(hospital, window));
        layout.add(welcomeMsg, 0, 0);
        layout.add(nameLabel, 0, 1);
        layout.add(IDLabel, 0, 2);
        layout.add(personalIDLabel, 0, 3);
        layout.add(randomIDLabel, 0, 4);
        layout.add(birthDateLabel, 0, 5);
        layout.add(addressLabel, 0, 6);
        layout.add(nameField, 1, 1);
        layout.add(IDField, 1, 2);
        layout.add(personalIDField, 1, 3);
        layout.add(randomIDField, 1, 4);
        layout.add(birthDateField, 1, 5);
        layout.add(addressField, 1, 6);
        layout.add(backButton, 0, 7);
            Scene scene = new Scene(layout);
          welcomeMsg.setStyle("-fx-font-size: 24px;");
        scene.getStylesheets().addAll(App.getClass);
        window.setScene(scene);
        

    }
    public abstract void changeInfo(Hospital hospital, Stage window);
    public boolean checkSignIn(String name , String password ){
        if(this.getLastName().toLowerCase().equals(name.toLowerCase())&&this.getPassword().equals(password)){
        return true;
        }else{
            return false;
        }
    }
    public boolean checkSignIn(long ID , String password ){
        if(this.getRandomID()==ID && this.getPassword().equals(password)){
        return true;
        }else{
            return false;
        }
    }
   
}
