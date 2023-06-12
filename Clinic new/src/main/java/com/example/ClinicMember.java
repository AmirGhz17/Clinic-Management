package com.example;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public abstract class ClinicMember extends Person{
    private long salary;
    private boolean nightShift;
    private boolean organce;
    //idc
    public ClinicMember(String firstName, String lastName, String address, String birthDate, long personalID,
            long salary, boolean nightShift, boolean organce,String password) {
        super(firstName, lastName, address, birthDate, personalID, password);
        this.salary = salary;
        this.nightShift = nightShift;
        this.organce = organce;
    }
    public ClinicMember(long ID,long randomID,String firstName, String lastName, String address, String birthDate, long personalID,
            long salary, boolean nightShift, boolean organce,String password) {
        super(ID,randomID,firstName, lastName, address, birthDate, personalID, password);
        this.salary = salary;
        this.nightShift = nightShift;
        this.organce = organce;
    }
    public long getSalary() {
        return salary;
    }
    public void setSalary(long salary) {
        this.salary = salary;
    }
    public boolean isNightShift() {
        return nightShift;
    }
    public void setNightShift(boolean nightShift) {
        this.nightShift = nightShift;
    }
    public boolean isOrgance() {
        return organce;
    }
    public void setOrgance(boolean organce) {
        this.organce = organce;
    }
    public void getInfo(Hospital hospital,Stage window){
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
        Label organceLabel = new Label("organce : ");
        Label nightShiftLabel = new Label("night shift : ");
        Label salaryLabel = new Label("salary : ");
        Label nameField = new Label(this.getFirstName() + " " + this.getLastName());
        Label IDField = new Label(this.getID()+ "");
        Label personalIDField = new Label(this.getPersonalID()+ "");
        Label randomIDField = new Label(this.getRandomID()+ "");
        Label birthDateField = new Label(this.getBirthDate());
        Label addressField = new Label(this.getAddress());
        Label organceField = new Label();
        if(this.isOrgance()){
            organceField.setText("Yes");
        }else{
            organceField.setText("No");
        }
        Label nightShiftField = new Label();
        if(this.isNightShift()){
            nightShiftField.setText("Yes");
        }else{
            nightShiftField.setText("No");
        }
        Label salaryField = new Label(Long.toString(this.getSalary()));
        Button backButton = new Button("back");
        backButton.setOnAction(e -> this.menu(hospital,window));
        layout.add(welcomeMsg, 0, 0);
        layout.add(nameLabel, 0, 1);
        layout.add(IDLabel, 0, 2);
        layout.add(personalIDLabel, 0, 3);
        layout.add(randomIDLabel, 0, 4);
        layout.add(birthDateLabel, 0, 5);
        layout.add(addressLabel, 0, 6);
        layout.add(organceLabel, 0, 7);
        layout.add(nightShiftLabel, 0, 8);
        layout.add(salaryLabel, 0, 9);
        layout.add(nameField, 1, 1);
        layout.add(IDField, 1, 2);
        layout.add(personalIDField, 1, 3);
        layout.add(randomIDField, 1, 4);
        layout.add(birthDateField, 1, 5);
        layout.add(addressField, 1, 6);
        layout.add(organceField, 1, 7);
        layout.add(nightShiftField, 1, 8);
        layout.add(salaryField, 1, 9);
        layout.add(backButton,0,10);
               Scene scene = new Scene(layout);
          welcomeMsg.setStyle("-fx-font-size: 24px;");
        scene.getStylesheets().addAll(App.getClass);
        window.setScene(scene);
       
        

    }
    public void menu(Hospital hospital,Stage window){
        GridPane layout = new GridPane();
        Label welcomeMsg= new Label("welcome "+ this.getFirstName());
        Button getInfo = new Button("get your info");
        getInfo.setOnAction(e -> {
            System.out.println("check");
            this.getInfo(hospital,window);});
        Button changeInfo = new Button("change your info");
        changeInfo.setOnAction(e -> {
            this.changeInfo(hospital,window);});
             Button backButton = new Button("back");
        backButton.setOnAction(e -> hospital.signInMenu(hospital,window));
        layout.setPadding(new Insets(10, 10, 10, 10)); 
        layout.setVgap(5);
        layout.setHgap(5);
        layout.setAlignment(Pos.CENTER);
        layout.add(welcomeMsg, 0,0);
        layout.add(getInfo, 1,1);
        layout.add(changeInfo, 1,2);
        layout.add(backButton, 0, 3);
             Scene scene = new Scene(layout);
          welcomeMsg.setStyle("-fx-font-size: 24px;");
        scene.getStylesheets().addAll(App.getClass);
        window.setScene(scene);
    }
    public abstract void changeInfo(Hospital hospital,Stage window);

}
