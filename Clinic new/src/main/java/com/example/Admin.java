package com.example;
import java.sql.Timestamp;
import java.util.Random;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Admin extends Person{


    private static String adminPassword="17";
    private static long IDCreator=1;
    public Admin(String firstName, String lastName, String address, String birthDate, long personalID, String password) {
        super(firstName, lastName, address, birthDate, personalID, password);
        this.setID(IDCreator);
        IDCreator++;
    }

    public Admin(long id, long randomID, String firstName, String lastName, String address, String birthDate, long personalID, String password) {
        super(id, randomID, firstName, lastName, address, birthDate, personalID, password);
    }

    public static void setIDCreator(long IDCreator) {
        Admin.IDCreator = IDCreator;
    }

    public void randomGenerator(Hospital hospital) {
        boolean checker = true;
        while(checker){
            this.setRandomID(new Random().nextLong());
            checker = false;
               if(this.getRandomID()<0){
            this.setRandomID(this.getRandomID()* -1);
        }
            for(Admin i: hospital.getAdmins()){
                if(i==this){
                    continue;
                }
                if(i.getRandomID()==this.getRandomID() ){
                    checker = true;
                    break;
                }
            }
        }
    }

    public static String getAdminPassword() {
        return adminPassword;
    }

    public static void setAdminPassword(String adminPassword) {
        Admin.adminPassword = adminPassword;
    }


    public static void signUp(Hospital hospital, Stage window){
      
        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10));
        layout.setVgap(10);
        layout.setHgap(10);
        layout.setAlignment(Pos.CENTER);
        Label welcomeMsg= new Label("pls fill the fields bellow");
        Label firstNameLabel = new Label("first name : ");
        Label lastNameLabel = new Label("last name : ");
        Label personalIDLabel = new Label("personal ID : ");
        Label birthDateLabel = new Label("birth date : ");
        Label addressLabel = new Label("address : ");
        Label password = new Label("password : ");
        Label adminPassLabel = new Label("Admin sign up code");
        TextField firstNameField = new TextField();
        firstNameField.setPromptText("first name");
        TextField lastNameField = new TextField();
        lastNameField.setPromptText("last name");
        TextField personalIDField = new TextField();
        personalIDField.setPromptText("personalID");
        TextField birthDateField = new TextField();
        birthDateField.setPromptText("birth date");
        TextField addressField = new TextField();
        addressField.setPromptText("address");
        TextField passwordField = new TextField();
        passwordField.setPromptText("password");
        TextField adminPassField = new TextField();
        adminPassField.setPromptText("secret pass");
        Button submitButton = new Button("SUBMIT");
        submitButton.setOnAction(e ->{
            try{

                if(!GlobalFunctions.checker2(firstNameField.getText(), 100, 1, true)) {
                    AlertBox.display("error", "first name entry should be between 1 and 100 letters ");
                    throw new Exception();
                }
                if(!GlobalFunctions.checker2(lastNameField.getText(), 100, 1, true)) {
                    AlertBox.display("error", "last name entry should be between 1 and 100 letters ");
                    throw new Exception();
                }
                if(!GlobalFunctions.checker2(personalIDField.getText(), 10, 9, false)||Long.parseLong(personalIDField.getText())<0 ) {
                    AlertBox.display("error", "personal ID entry should be a positive number either 9 or 10 charachters long  ");
                    throw new Exception();
                }
                for(Patient i: hospital.getPatients()){
                    if(i.getPersonalID()== Long.parseLong(personalIDField.getText())){
                        AlertBox.display("error", "there's already a person with this ID in our system ");
                        throw new Exception();
                    }
                }
                if(!GlobalFunctions.checker2(birthDateField.getText(), 100, 1, false)) {
                    AlertBox.display("error", "birthDate entry should be between 1 and 45 letters ");
                    throw new Exception();
                }
                if(!GlobalFunctions.checker2(addressField.getText(), 200, 1, false)){
                    AlertBox.display("error", "address entry should be between 1 and 200 letters ");
                    throw new Exception();
                }
                if(!GlobalFunctions.checker2(passwordField.getText(), 40, 8, false)) {
                    AlertBox.display("error", "password entry should be between 8 to 40 charachters long  ");
                     throw new Exception();
                }
                if(!adminPassField.getText().equals(Admin.getAdminPassword())){
                    AlertBox.display("error", "admin registry code is wrong");
                    throw new Exception();
                }
                Admin admin = new Admin(firstNameField.getText(),lastNameField.getText(),addressField.getText(),
                        birthDateField.getText(),Long.parseLong(personalIDField.getText()),passwordField.getText());
                DataCenter.saveAdmin(hospital,admin);
                admin.menu(hospital,window);
                }catch (Exception c){
                System.out.println(c);
            }
        });
        Button backButton = new Button("back");
        backButton.setOnAction(e -> hospital.menu(window));

        layout.add(welcomeMsg, 0, 0);
        layout.add(firstNameLabel, 0, 1);
        layout.add(lastNameLabel, 0, 2);
        layout.add(personalIDLabel, 0, 3);
        layout.add(birthDateLabel, 0, 4);
        layout.add(addressLabel, 0, 5);
        layout.add(password, 0, 6);
        layout.add(adminPassLabel, 0, 7);
        layout.add(firstNameField, 1, 1);
        layout.add(lastNameField, 1, 2);
        layout.add(personalIDField, 1, 3);
        layout.add(birthDateField, 1, 4);
        layout.add(addressField, 1, 5);
        layout.add(passwordField,1,6);
        layout.add(adminPassField,1,7);
        layout.add(submitButton,1,8);
        layout.add(backButton,0,8);
        Scene scene = new Scene(layout);
        welcomeMsg.setStyle("-fx-font-size: 24px;");
        scene.getStylesheets().addAll(App.getClass);
        window.setScene(scene);


}
    public  void addNurse(Hospital hospital,Stage window){
        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10));
        layout.setVgap(10);
        layout.setHgap(10);
        layout.setAlignment(Pos.CENTER);
        Label welcomeMsg= new Label("pls fill the fields bellow");
        Label firstNameLabel = new Label("first name : ");
        Label lastNameLabel = new Label("last name : ");
        Label personalIDLabel = new Label("personal ID : ");
        Label birthDateLabel = new Label("birth date : ");
        Label addressLabel = new Label("address : ");
        Label salaryLabel = new Label("salary : ");
        Label organceLabel = new Label("organce : ");
        Label nightShiftLabel = new Label("Night shift : ");
        RadioButton organceButton = new RadioButton();
        RadioButton nightShifButton = new RadioButton();
        Label password = new Label("password : ");
        TextField firstNameField = new TextField();
        firstNameField.setPromptText("first name");
        TextField lastNameField = new TextField();
        lastNameField.setPromptText("last name");
        TextField personalIDField = new TextField();
        personalIDField.setPromptText("personalID");
        TextField birthDateField = new TextField();
        birthDateField.setPromptText("birth date");
        TextField addressField = new TextField();
        addressField.setPromptText("address");
        TextField salaryField = new TextField();
        salaryField.setPromptText("salary");
        TextField passwordField = new TextField();
        passwordField.setPromptText("password");
        Button submitButton = new Button("SUBMIT");
        submitButton.setOnAction(e ->{
            try{

                if(!GlobalFunctions.checker2(firstNameField.getText(), 100, 1, true)) {
                    AlertBox.display("error", "first name entry should be between 1 and 100 letters ");
                    throw new Exception();
                }
                if(!GlobalFunctions.checker2(lastNameField.getText(), 100, 1, true)) {
                    AlertBox.display("error", "last name entry should be between 1 and 100 letters ");
                    throw new Exception();
                }
                if(!GlobalFunctions.checker2(personalIDField.getText(), 10, 9, false)||Long.parseLong(personalIDField.getText())<0 ) {
                    AlertBox.display("error", "personal ID entry should be a positive number either 9 or 10 charachters long  ");
                    throw new Exception();
                }
                if(!GlobalFunctions.checker2(salaryField.getText(), 10, 1, false)||Long.parseLong(salaryField.getText())<0 ) {
                    AlertBox.display("error", "salary entry should be a positive number less than 10 charachters long  ");
                    throw new Exception();
                }
                for(Patient i: hospital.getPatients()){
                    if(i.getPersonalID()== Long.parseLong(personalIDField.getText())){
                        AlertBox.display("error", "there's already a person with this ID in our system ");
                        throw new Exception();
                    }
                }
                if(!GlobalFunctions.checker2(birthDateField.getText(), 100, 1, false)) {
                    AlertBox.display("error", "birthDate entry should be between 1 and 45 letters ");
                    throw new Exception();
                }
                if(!GlobalFunctions.checker2(addressField.getText(), 200, 1, false)){
                    AlertBox.display("error", "address entry should be between 1 and 200 letters ");
                    throw new Exception();
                }
                if(!GlobalFunctions.checker2(passwordField.getText(), 40, 8, false)) {
                    AlertBox.display("error", "password entry should be between 8 to 40 charachters long  ");
                     throw new Exception();
                }
                AlertBox.display("success", "the user successfully added");
                Nurse nurse = new Nurse(firstNameField.getText(),lastNameField.getText(),addressField.getText(),
                        birthDateField.getText(),Long.parseLong(personalIDField.getText()),Long.parseLong(salaryField.getText()),
                        nightShifButton.isSelected(),organceButton.isSelected(),passwordField.getText());
               
                DataCenter.saveNurse(hospital,nurse);
    
                }catch (Exception c){
                System.out.println(c);
            }
        });
        Button backButton = new Button("back");
        backButton.setOnAction(e -> this.menu(hospital,window));


        layout.add(welcomeMsg, 0, 0);
        layout.add(firstNameLabel, 0, 1);
        layout.add(lastNameLabel, 0, 2);
        layout.add(personalIDLabel, 0, 3);
        layout.add(birthDateLabel, 0, 4);
        layout.add(addressLabel, 0, 5);
        layout.add(salaryLabel, 0, 6);
        layout.add(organceLabel, 0, 7);
        layout.add(nightShiftLabel, 0, 8);
        layout.add(password, 0, 9);
        layout.add(firstNameField, 1, 1);
        layout.add(lastNameField, 1, 2);
        layout.add(personalIDField, 1, 3);
        layout.add(birthDateField, 1, 4);
        layout.add(addressField, 1, 5);
        layout.add(salaryField,1,6);
        layout.add(organceButton, 1, 7);
        layout.add(nightShifButton, 1, 8);
        layout.add(passwordField,1,9);
        layout.add(submitButton,1,10);
        layout.add(backButton,0,10);
        Scene scene = new Scene(layout);
          welcomeMsg.setStyle("-fx-font-size: 24px;");
        scene.getStylesheets().addAll(App.getClass);
        window.setScene(scene);

    } 
    public  void addStaff(Hospital hospital,Stage window){
        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10));
        layout.setVgap(10);
        layout.setHgap(10);
        layout.setAlignment(Pos.CENTER);
        Label welcomeMsg= new Label("pls fill the fields bellow");
        Label firstNameLabel = new Label("first name : ");
        Label lastNameLabel = new Label("last name : ");
        Label personalIDLabel = new Label("personal ID : ");
        Label birthDateLabel = new Label("birth date : ");
        Label addressLabel = new Label("address : ");
        Label salaryLabel = new Label("salary : ");
        Label organceLabel = new Label("organce : ");
        Label nightShiftLabel = new Label("Night shift : ");
        RadioButton organceButton = new RadioButton();
        RadioButton nightShifButton = new RadioButton();
        Label password = new Label("password : ");
        TextField firstNameField = new TextField();
        firstNameField.setPromptText("first name");
        TextField lastNameField = new TextField();
        lastNameField.setPromptText("last name");
        TextField personalIDField = new TextField();
        personalIDField.setPromptText("personalID");
        TextField birthDateField = new TextField();
        birthDateField.setPromptText("birth date");
        TextField addressField = new TextField();
        addressField.setPromptText("address");
        TextField salaryField = new TextField();
        salaryField.setPromptText("salary");
        TextField passwordField = new TextField();
        passwordField.setPromptText("password");
        Button submitButton = new Button("SUBMIT");
        submitButton.setOnAction(e ->{
            try{

                if(!GlobalFunctions.checker2(firstNameField.getText(), 100, 1, true)) {
                    AlertBox.display("error", "first name entry should be between 1 and 100 letters ");
                    throw new Exception();
                }
                if(!GlobalFunctions.checker2(lastNameField.getText(), 100, 1, true)) {
                    AlertBox.display("error", "last name entry should be between 1 and 100 letters ");
                    throw new Exception();
                }
                if(!GlobalFunctions.checker2(personalIDField.getText(), 10, 9, false)||Long.parseLong(personalIDField.getText())<0 ) {
                    AlertBox.display("error", "personal ID entry should be a positive number either 9 or 10 charachters long  ");
                    throw new Exception();
                }
                if(!GlobalFunctions.checker2(salaryField.getText(), 10, 1, false)||Long.parseLong(salaryField.getText())<0 ) {
                    AlertBox.display("error", "salary entry should be a positive number less than 10 charachters long  ");
                    throw new Exception();
                }
                for(Patient i: hospital.getPatients()){
                    if(i.getPersonalID()== Long.parseLong(personalIDField.getText())){
                        AlertBox.display("error", "there's already a person with this ID in our system ");
                        throw new Exception();
                    }
                }
                if(!GlobalFunctions.checker2(birthDateField.getText(), 100, 1, false)) {
                    AlertBox.display("error", "birthDate entry should be between 1 and 45 letters ");
                    throw new Exception();
                }
                if(!GlobalFunctions.checker2(addressField.getText(), 200, 1, false)){
                    AlertBox.display("error", "address entry should be between 1 and 200 letters ");
                    throw new Exception();
                }
                if(!GlobalFunctions.checker2(passwordField.getText(), 40, 8, false)) {
                    AlertBox.display("error", "password entry should be between 8 to 40 charachters long  ");
                     throw new Exception();
                }
                AlertBox.display("success", "the user successfully added");
                Staff staff = new Staff(firstNameField.getText(),lastNameField.getText(),addressField.getText(),
                        birthDateField.getText(),Long.parseLong(personalIDField.getText()),Long.parseLong(salaryField.getText()),
                        nightShifButton.isSelected(),organceButton.isSelected(),passwordField.getText());
               
                DataCenter.saveStaff(hospital, staff);
    
                }catch (Exception c){
                System.out.println(c);
            }
        });


        Button backButton = new Button("back");
        backButton.setOnAction(e -> this.menu(hospital,window));
        layout.add(welcomeMsg, 0, 0);
        layout.add(firstNameLabel, 0, 1);
        layout.add(lastNameLabel, 0, 2);
        layout.add(personalIDLabel, 0, 3);
        layout.add(birthDateLabel, 0, 4);
        layout.add(addressLabel, 0, 5);
        layout.add(salaryLabel, 0, 6);
        layout.add(organceLabel, 0, 7);
        layout.add(nightShiftLabel, 0, 8);
        layout.add(password, 0, 9);
        layout.add(firstNameField, 1, 1);
        layout.add(lastNameField, 1, 2);
        layout.add(personalIDField, 1, 3);
        layout.add(birthDateField, 1, 4);
        layout.add(addressField, 1, 5);
        layout.add(salaryField,1,6);
        layout.add(organceButton, 1, 7);
        layout.add(nightShifButton, 1, 8);
        layout.add(passwordField,1,9);
        layout.add(submitButton,1,10);
        layout.add(backButton,0,10);
        Scene scene = new Scene(layout);
          welcomeMsg.setStyle("-fx-font-size: 24px;");
        scene.getStylesheets().addAll(App.getClass);
        window.setScene(scene);

    } 
    public  void addDoctor(Hospital hospital,Stage window){
        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10));
        layout.setVgap(10);
        layout.setHgap(10);
        layout.setAlignment(Pos.CENTER);
        Label welcomeMsg= new Label("pls fill the fields bellow");
        Label firstNameLabel = new Label("first name : ");
        Label lastNameLabel = new Label("last name : ");
        Label personalIDLabel = new Label("personal ID : ");
        Label birthDateLabel = new Label("birth date : ");
        Label addressLabel = new Label("address : ");
        Label salaryLabel = new Label("salary : ");
        Label organceLabel = new Label("organce : ");
        Label nightShiftLabel = new Label("Night shift : ");
        RadioButton organceButton = new RadioButton();
        RadioButton nightShifButton = new RadioButton();
        Label professionLabel = new Label("profession : ");
        Label password = new Label("password : ");
        TextField firstNameField = new TextField();
        firstNameField.setPromptText("first name");
        TextField lastNameField = new TextField();
        lastNameField.setPromptText("last name");
        TextField personalIDField = new TextField();
        personalIDField.setPromptText("personalID");
        TextField birthDateField = new TextField();
        birthDateField.setPromptText("birth date");
        TextField addressField = new TextField();
        addressField.setPromptText("address");
        TextField salaryField = new TextField();
        salaryField.setPromptText("salary");
        TextField passwordField = new TextField();
        passwordField.setPromptText("password");
        TextField professionField = new TextField();
        professionField.setPromptText("profession");
        Button submitButton = new Button("SUBMIT");
        submitButton.setOnAction(e ->{
            try{

                if(!GlobalFunctions.checker2(firstNameField.getText(), 100, 1, true)) {
                    AlertBox.display("error", "first name entry should be between 1 and 100 letters ");
                    throw new Exception();
                }
                if(!GlobalFunctions.checker2(lastNameField.getText(), 100, 1, true)) {
                    AlertBox.display("error", "last name entry should be between 1 and 100 letters ");
                    throw new Exception();
                }
                if(!GlobalFunctions.checker2(personalIDField.getText(), 10, 9, false)||Long.parseLong(personalIDField.getText())<0 ) {
                    AlertBox.display("error", "personal ID entry should be a positive number either 9 or 10 charachters long  ");
                    throw new Exception();
                }
                if(!GlobalFunctions.checker2(salaryField.getText(), 10, 1, false)||Long.parseLong(salaryField.getText())<0 ) {
                    AlertBox.display("error", "salary entry should be a positive number less than 10 charachters long  ");
                    throw new Exception();
                }
                for(Patient i: hospital.getPatients()){
                    if(i.getPersonalID()== Long.parseLong(personalIDField.getText())){
                        AlertBox.display("error", "there's already a person with this ID in our system ");
                        throw new Exception();
                    }
                }
                if(!GlobalFunctions.checker2(birthDateField.getText(), 100, 1, false)) {
                    AlertBox.display("error", "birthDate entry should be between 1 and 45 letters ");
                    throw new Exception();
                }
                if(!GlobalFunctions.checker2(addressField.getText(), 200, 1, false)){
                    AlertBox.display("error", "address entry should be between 1 and 200 letters ");
                    throw new Exception();
                }
                if(!GlobalFunctions.checker2(passwordField.getText(), 40, 8, false)) {
                    AlertBox.display("error", "password entry should be between 8 to 40 charachters long  ");
                     throw new Exception();
                }
                if(!GlobalFunctions.checker2(professionField.getText(), 500, 1, false)) {
                    AlertBox.display("error", "profession entry should be between 1 to 500 charachters long  ");
                     throw new Exception();
                }
                AlertBox.display("success", "the user successfully added");
                Doctor doctor = new Doctor(firstNameField.getText(),lastNameField.getText(),addressField.getText(),
                        birthDateField.getText(),Long.parseLong(personalIDField.getText()),Long.parseLong(salaryField.getText()),
                        nightShifButton.isSelected(),organceButton.isSelected(),passwordField.getText(),passwordField.getText());
               
                DataCenter.saveDoctor(hospital, doctor);
    
                }catch (Exception c){
                System.out.println(c);
            }
        });
        Button backButton = new Button("back");
        backButton.setOnAction(e -> this.menu(hospital,window));


        layout.add(welcomeMsg, 0, 0);
        layout.add(firstNameLabel, 0, 1);
        layout.add(lastNameLabel, 0, 2);
        layout.add(personalIDLabel, 0, 3);
        layout.add(birthDateLabel, 0, 4);
        layout.add(addressLabel, 0, 5);
        layout.add(salaryLabel, 0, 6);
        layout.add(organceLabel, 0, 7);
        layout.add(nightShiftLabel, 0, 8);
        layout.add(professionLabel, 0, 9);
        layout.add(password, 0, 10);
        layout.add(firstNameField, 1, 1);
        layout.add(lastNameField, 1, 2);
        layout.add(personalIDField, 1, 3);
        layout.add(birthDateField, 1, 4);
        layout.add(addressField, 1, 5);
        layout.add(salaryField,1,6);
        layout.add(organceButton, 1, 7);
        layout.add(nightShifButton, 1, 8);
        layout.add(professionField,1,9);
        layout.add(passwordField,1,10);
        layout.add(submitButton,1,11);
        layout.add(backButton, 0, 11);
        Scene scene = new Scene(layout);
          welcomeMsg.setStyle("-fx-font-size: 24px;");
        scene.getStylesheets().addAll(App.getClass);
        window.setScene(scene);

    } 
    
    @Override
    public void changeInfo(Hospital hospital, Stage window) {
        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10)); 
        layout.setVgap(10);
        layout.setHgap(10);
        layout.setAlignment(Pos.CENTER);
        Label welcomeMsg= new Label("what do you want to change?");
        Label firstNameLabel = new Label("first name : ");
        Label lastNameLabel = new Label("last name : ");
        Label personalIDLabel = new Label("personal ID : ");
        Label birthDateLabel = new Label("birth date : ");
        Label addressLabel = new Label("address : ");
        Label password = new Label("password : ");
        Label adminPassLabel = new Label("admin sign up password");
        TextField firstNameField = new TextField();
        firstNameField.setPromptText(this.getFirstName());
        TextField lastNameField = new TextField();
        lastNameField.setPromptText(this.getLastName());
        TextField personalIDField = new TextField();
        personalIDField.setPromptText(this.getPersonalID()+ "");
        TextField birthDateField = new TextField();
        birthDateField.setPromptText(this.getBirthDate());
        TextField addressField = new TextField();
        TextField oldPasswordField = new TextField();
        oldPasswordField.setPromptText("old password");
        TextField newPasswordField = new TextField();
        newPasswordField.setPromptText("new password");
        TextField adminPassField = new TextField();
        adminPassField.setPromptText("admin sign up password");
        firstNameField.setOnAction(e ->{
            if(firstNameField.getText()!= this.getFirstName()){
                if(!checker(firstNameField.getText(), 100, 1, true)){
                    AlertBox.display("error", "entry should be between 1 and 100 letters ");
                }else{
                    this.setFirstName(firstNameField.getText());
                    DataCenter.updateInfo("admin", "firstName", this.getID(), firstNameField.getText());
                    AlertBox.display("change", "your name has successsfully changed");
                }
            }
        });
        lastNameField.setOnAction(e ->{
            if(lastNameField.getText()!= this.getLastName()){
                if(!checker(lastNameField.getText(), 100, 1, true)){
                    AlertBox.display("error", "entry should be between 1 and 100 letters ");
                }else{
                    this.setFirstName(lastNameField.getText());
                    DataCenter.updateInfo("admin", "lastName", this.getID(), lastNameField.getText());
                    AlertBox.display("change", "your name has successsfully changed");
                }
            }
        });
        personalIDField.setOnAction(e ->{
            try{
            if(Long.parseLong(personalIDField.getText()) != this.getPersonalID()){
                if(!checker(personalIDField.getText(), 10, 9, false)||Long.parseLong(personalIDField.getText())<0 ){
                    throw new Exception();
                }else{
                     for(Admin i: hospital.getAdmins()){
                         if(i==this){
                             continue;
                         }
                         if(i.getPersonalID()== Long.parseLong(personalIDField.getText())){
                            throw new IndexOutOfBoundsException();
                         }
                        }
                 }
                 this.setPersonalID(Long.parseLong(personalIDField.getText()));
                    DataCenter.updateInfo("admin", "personalID", this.getID(), Long.parseLong(personalIDField.getText()));
                    AlertBox.display("change", "your personal ID has successsfully changed");
            }
        }catch(IndexOutOfBoundsException c){
            AlertBox.display("error", "thers's already a person with this ID in our system ");
        }catch(Exception c){
            AlertBox.display("error", "entry should be a positive number either 9 or 10 charachters long  ");
        }
        });
        birthDateField.setOnAction(e ->{
            if(birthDateField.getText()!= this.getBirthDate()){
                if(!checker(birthDateField.getText(), 100, 1, false)){
                    AlertBox.display("error", "entry should be between 1 and 45 letters ");
                }else{
                    this.setBirthDate(birthDateField.getText());
                    DataCenter.updateInfo("admin", "birthDate", this.getID(), birthDateField.getText());
                    AlertBox.display("change", "your birthDate has successsfully changed");
                }
            }
        });
        addressField.setOnAction(e ->{
            if(addressField.getText()!= this.getAddress()){
                if(!checker(addressField.getText(), 200, 1, false)){
                    AlertBox.display("error", "entry should be between 1 and 200 letters ");
                }else{
                    this.setAddress(addressField.getText());
                    DataCenter.updateInfo("admin", "address", this.getID(), addressField.getText());
                    AlertBox.display("change", "your address has successsfully changed");
                }
            }
        });
        newPasswordField.setOnAction(e ->{
            try{
                if(!checker(newPasswordField.getText(), 40, 8, false)){
                    throw new Exception();
                }else{
                    if(!oldPasswordField.getText().equals(this.getPassword())){
                        throw new IndexOutOfBoundsException();
                    }
                 }
                 this.setPassword(newPasswordField.getText());
                    DataCenter.updateInfo("admin", "password", this.getID(), newPasswordField.getText());
                    AlertBox.display("change", "your password has successsfully changed");
        }catch(IndexOutOfBoundsException c){
            AlertBox.display("error", "old password is wrong");
        }catch(Exception c){
            AlertBox.display("error", "entry should be between 8 to 40 charachters long  ");
        }
        });
        adminPassField.setOnAction(e ->{
            try{
            if(!checker(adminPassField.getText(), 40, 8, false)){
                AlertBox.display("error", "entry should be between 8 to 40 charachters long  ");
                throw new Exception();
            }else{
                Admin.setAdminPassword(adminPassField.getText());
                DataCenter.updateInfo("admin", "adminPassword", this.getID(), newPasswordField.getText());
                    AlertBox.display("change", "your password has successsfully changed");
            }
        }catch(Exception c){
            System.out.println(c);
        }
        });
        Button backButton = new Button("back");
        backButton.setOnAction(e -> this.menu(hospital,window));
        layout.add(welcomeMsg, 0, 0);
        layout.add(firstNameLabel, 0, 1);
        layout.add(lastNameLabel, 0, 2);
        layout.add(personalIDLabel, 0, 3);
        layout.add(birthDateLabel, 0, 4);
        layout.add(addressLabel, 0, 5);
        layout.add(password, 0, 6);
        layout.add(adminPassLabel,0,8);
        layout.add(firstNameField, 1, 1);
        layout.add(lastNameField, 1, 2);
        layout.add(personalIDField, 1, 3);
        layout.add(birthDateField, 1, 4);
        layout.add(addressField, 1, 5);
        layout.add(oldPasswordField, 1, 6);
        layout.add(newPasswordField, 1, 7);
        layout.add(adminPassField, 1, 8);
        layout.add(backButton,0,9);
        Scene scene = new Scene(layout);
          welcomeMsg.setStyle("-fx-font-size: 24px;");
        scene.getStylesheets().addAll(App.getClass);
        window.setScene(scene);
    }
    public void getVisitList(Hospital hospital){
        System.out.println(123);
        Stage window = new Stage();
        TableView patientTable = new TableView<>();
        window.setTitle("Visit List");
        TableColumn<Patient,Long > IDColumn = new TableColumn<>("ID");
        IDColumn.setMinWidth(100);
        IDColumn.setCellValueFactory(new PropertyValueFactory<>("ID") );
        TableColumn<Patient,Long > randomIDColumn = new TableColumn<>("Random generated ID");
        randomIDColumn.setMinWidth(100);
        randomIDColumn.setCellValueFactory(new PropertyValueFactory<>("randomID") );
        TableColumn<Patient,String > patientNameColumn = new TableColumn<>("Patient name");
        patientNameColumn.setMinWidth(100);
        patientNameColumn.setCellValueFactory(new PropertyValueFactory<>("patientName") );
        TableColumn<Patient,String > doctorNameColumn = new TableColumn<>("Doctor name");
        doctorNameColumn.setMinWidth(100);
        doctorNameColumn.setCellValueFactory(new PropertyValueFactory<>("doctorName") );
        TableColumn<Patient,String > diagnoseColumn = new TableColumn<>("Diagnose");
        diagnoseColumn.setMinWidth(100);
        diagnoseColumn.setCellValueFactory(new PropertyValueFactory<>("diagnose") );
        TableColumn<Patient,String > drugPrescriptionIDColumn = new TableColumn<>("Drug prescription");
        drugPrescriptionIDColumn.setMinWidth(100);
        drugPrescriptionIDColumn.setCellValueFactory(new PropertyValueFactory<>("drugPrescription") );
        TableColumn<Patient,String > ordersColumn = new TableColumn<>("Orders");
        ordersColumn.setMinWidth(100);
        ordersColumn.setCellValueFactory(new PropertyValueFactory<>("orders") );
        TableColumn<Patient,Timestamp > timestampColumn = new TableColumn<>("Time");
        timestampColumn.setMinWidth(100);
        timestampColumn.setCellValueFactory(new PropertyValueFactory<>("timestamp") );
        TableColumn<Patient,Long > feeColumn = new TableColumn<>("Fee");
        feeColumn.setMinWidth(100);
        feeColumn.setCellValueFactory(new PropertyValueFactory<>("fee") );
        patientTable.setItems(FXCollections.observableArrayList(hospital.getVisits()));
        patientTable.getColumns().addAll(IDColumn,randomIDColumn,patientNameColumn,doctorNameColumn,diagnoseColumn,drugPrescriptionIDColumn,ordersColumn,timestampColumn,feeColumn);
        VBox layout = new VBox(patientTable);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        scene.getStylesheets().addAll(App.getClass);
        window.show();
    }
    public void getReserveList(Hospital hospital){
        System.out.println(123);
        Stage window = new Stage();
        TableView patientTable = new TableView<>();
        window.setTitle("Reserve List");
        TableColumn<Patient,Long > IDColumn = new TableColumn<>("ID");
        IDColumn.setMinWidth(100);
        IDColumn.setCellValueFactory(new PropertyValueFactory<>("ID") );
        TableColumn<Patient,Long > randomIDColumn = new TableColumn<>("Random generated ID");
        randomIDColumn.setMinWidth(100);
        randomIDColumn.setCellValueFactory(new PropertyValueFactory<>("randomID") );
        TableColumn<Patient,String > patientNameColumn = new TableColumn<>("Patient name");
        patientNameColumn.setMinWidth(100);
        patientNameColumn.setCellValueFactory(new PropertyValueFactory<>("patientName") );
        TableColumn<Patient,String > doctorNameColumn = new TableColumn<>("Doctor name");
        doctorNameColumn.setMinWidth(100);
        doctorNameColumn.setCellValueFactory(new PropertyValueFactory<>("doctorName") );
        TableColumn<Patient,Timestamp > timestampColumn = new TableColumn<>("Time");
        timestampColumn.setMinWidth(100);
        timestampColumn.setCellValueFactory(new PropertyValueFactory<>("timestamp") );

        patientTable.setItems(FXCollections.observableArrayList(hospital.getReserves()));
        patientTable.getColumns().addAll(IDColumn,randomIDColumn,patientNameColumn,doctorNameColumn,timestampColumn);
        VBox layout = new VBox(patientTable);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();
    }
    public void getNurseList(Hospital hospital){
        Stage window = new Stage();
        TableView patientTable = new TableView<>();
        window.setTitle("Nurse List");
        TableColumn<Patient,Long > IDColumn = new TableColumn<>("ID");
        IDColumn.setMinWidth(100);
        IDColumn.setCellValueFactory(new PropertyValueFactory<>("ID") );
        TableColumn<Patient,Long > randomIDColumn = new TableColumn<>("Random generated ID");
        randomIDColumn.setMinWidth(100);
        randomIDColumn.setCellValueFactory(new PropertyValueFactory<>("randomID") );
        TableColumn<Patient,String > firstNameColumn = new TableColumn<>("First name");
        firstNameColumn.setMinWidth(100);
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName") );
        TableColumn<Patient,String > lastNameColumn = new TableColumn<>("Last name");
        lastNameColumn.setMinWidth(100);
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName") );
        TableColumn<Patient,Long> personalIDColumn = new TableColumn<>("Personal ID");
        personalIDColumn.setMinWidth(100);
        personalIDColumn.setCellValueFactory(new PropertyValueFactory<>("personalID") );
        TableColumn<Patient,String > addressColumn = new TableColumn<>("Address");
        addressColumn.setMinWidth(200);
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address") );
        TableColumn<Patient,String > birthDateColumn = new TableColumn<>("Birth date");
        birthDateColumn.setMinWidth(100);
        birthDateColumn.setCellValueFactory(new PropertyValueFactory<>("birthDate") );
        TableColumn<Patient,Long > salaryColumn = new TableColumn<>("Salary");
        salaryColumn.setMinWidth(100);
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary") );
        TableColumn<Patient,Boolean > organceColumn = new TableColumn<>("Organce");
        organceColumn.setMinWidth(100);
        organceColumn.setCellValueFactory(new PropertyValueFactory<>("organce") );
        TableColumn<Patient,Boolean > nightShiftColumn = new TableColumn<>("Night shift");
        nightShiftColumn.setMinWidth(100);
        nightShiftColumn.setCellValueFactory(new PropertyValueFactory<>("nightShift") );
        patientTable.setItems(FXCollections.observableArrayList(hospital.getNurses()));
        patientTable.getColumns().addAll(IDColumn,randomIDColumn,firstNameColumn,lastNameColumn,personalIDColumn,addressColumn,birthDateColumn,organceColumn,nightShiftColumn,salaryColumn);
        VBox layout = new VBox(patientTable);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();
    }
    public void getStaffList(Hospital hospital){
        Stage window = new Stage();
        TableView patientTable = new TableView<>();
        window.setTitle("Staff List");
        TableColumn<Patient,Long > IDColumn = new TableColumn<>("ID");
        IDColumn.setMinWidth(100);
        IDColumn.setCellValueFactory(new PropertyValueFactory<>("ID") );
        TableColumn<Patient,Long > randomIDColumn = new TableColumn<>("Random generated ID");
        randomIDColumn.setMinWidth(100);
        randomIDColumn.setCellValueFactory(new PropertyValueFactory<>("randomID") );
        TableColumn<Patient,String > firstNameColumn = new TableColumn<>("First name");
        firstNameColumn.setMinWidth(100);
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName") );
        TableColumn<Patient,String > lastNameColumn = new TableColumn<>("Last name");
        lastNameColumn.setMinWidth(100);
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName") );
        TableColumn<Patient,Long> personalIDColumn = new TableColumn<>("Personal ID");
        personalIDColumn.setMinWidth(100);
        personalIDColumn.setCellValueFactory(new PropertyValueFactory<>("personalID") );
        TableColumn<Patient,String > addressColumn = new TableColumn<>("Address");
        addressColumn.setMinWidth(200);
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address") );
        TableColumn<Patient,String > birthDateColumn = new TableColumn<>("Birth date");
        birthDateColumn.setMinWidth(100);
        birthDateColumn.setCellValueFactory(new PropertyValueFactory<>("birthDate") );
        TableColumn<Patient,Long > salaryColumn = new TableColumn<>("Salary");
        salaryColumn.setMinWidth(100);
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary") );
        TableColumn<Patient,Boolean > organceColumn = new TableColumn<>("Organce");
        organceColumn.setMinWidth(100);
        organceColumn.setCellValueFactory(new PropertyValueFactory<>("organce") );
        TableColumn<Patient,Boolean > nightShiftColumn = new TableColumn<>("Night shift");
        nightShiftColumn.setMinWidth(100);
        nightShiftColumn.setCellValueFactory(new PropertyValueFactory<>("nightShift") );
        patientTable.setItems(FXCollections.observableArrayList(hospital.getStaff()));
        patientTable.getColumns().addAll(IDColumn,randomIDColumn,firstNameColumn,lastNameColumn,personalIDColumn,addressColumn,birthDateColumn,organceColumn,nightShiftColumn,salaryColumn);
        VBox layout = new VBox(patientTable);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();
    }
    public void getDoctorList(Hospital hospital){
        Stage window = new Stage();
        TableView patientTable = new TableView<>();
        window.setTitle("Doctor List");
        TableColumn<Patient,Long > IDColumn = new TableColumn<>("ID");
        IDColumn.setMinWidth(100);
        IDColumn.setCellValueFactory(new PropertyValueFactory<>("ID") );
        TableColumn<Patient,Long > randomIDColumn = new TableColumn<>("Random generated ID");
        randomIDColumn.setMinWidth(100);
        randomIDColumn.setCellValueFactory(new PropertyValueFactory<>("randomID") );
        TableColumn<Patient,String > firstNameColumn = new TableColumn<>("First name");
        firstNameColumn.setMinWidth(100);
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName") );
        TableColumn<Patient,String > lastNameColumn = new TableColumn<>("Last name");
        lastNameColumn.setMinWidth(100);
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName") );
        TableColumn<Patient,Long> personalIDColumn = new TableColumn<>("Personal ID");
        personalIDColumn.setMinWidth(100);
        personalIDColumn.setCellValueFactory(new PropertyValueFactory<>("personalID") );
        TableColumn<Patient,Long> professionColumn = new TableColumn<>("Personal ID");
        professionColumn.setMinWidth(200);
        professionColumn.setCellValueFactory(new PropertyValueFactory<>("profession") );
        TableColumn<Patient,String > addressColumn = new TableColumn<>("Address");
        addressColumn.setMinWidth(200);
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address") );
        TableColumn<Patient,String > birthDateColumn = new TableColumn<>("Birth date");
        birthDateColumn.setMinWidth(100);
        birthDateColumn.setCellValueFactory(new PropertyValueFactory<>("birthDate") );
        TableColumn<Patient,Long > salaryColumn = new TableColumn<>("Salary");
        salaryColumn.setMinWidth(100);
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary") );
        TableColumn<Patient,Boolean > organceColumn = new TableColumn<>("Organce");
        organceColumn.setMinWidth(100);
        organceColumn.setCellValueFactory(new PropertyValueFactory<>("organce") );
        TableColumn<Patient,Boolean > nightShiftColumn = new TableColumn<>("Night shift");
        nightShiftColumn.setMinWidth(100);
        nightShiftColumn.setCellValueFactory(new PropertyValueFactory<>("nightShift") );
        patientTable.setItems(FXCollections.observableArrayList(hospital.getDoctors()));
        patientTable.getColumns().addAll(IDColumn,randomIDColumn,firstNameColumn,lastNameColumn,personalIDColumn,professionColumn,addressColumn,birthDateColumn,organceColumn,nightShiftColumn,salaryColumn);
        VBox layout = new VBox(patientTable);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();
    }
    public void getPatientList(Hospital hospital){
        Stage window = new Stage();
        TableView patientTable = new TableView<>();
        window.setTitle("Patient List");
        TableColumn<Patient,Long > IDColumn = new TableColumn<>("ID");
        IDColumn.setMinWidth(100);
        IDColumn.setCellValueFactory(new PropertyValueFactory<>("ID") );
        TableColumn<Patient,Long > randomIDColumn = new TableColumn<>("Random generated ID");
        randomIDColumn.setMinWidth(100);
        randomIDColumn.setCellValueFactory(new PropertyValueFactory<>("randomID") );
        TableColumn<Patient,String > firstNameColumn = new TableColumn<>("First name");
        firstNameColumn.setMinWidth(100);
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName") );
        TableColumn<Patient,String > lastNameColumn = new TableColumn<>("Last name");
        lastNameColumn.setMinWidth(100);
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName") );
        TableColumn<Patient,Long> personalIDColumn = new TableColumn<>("Personal ID");
        personalIDColumn.setMinWidth(100);
        personalIDColumn.setCellValueFactory(new PropertyValueFactory<>("personalID") );
        TableColumn<Patient,String > addressColumn = new TableColumn<>("Address");
        addressColumn.setMinWidth(200);
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address") );
        TableColumn<Patient,String > birthDateColumn = new TableColumn<>("Birth date");
        birthDateColumn.setMinWidth(100);
        birthDateColumn.setCellValueFactory(new PropertyValueFactory<>("birthDate") );

        patientTable.setItems(FXCollections.observableArrayList(hospital.getPatients()));
        patientTable.getColumns().addAll(IDColumn,randomIDColumn,firstNameColumn,lastNameColumn,personalIDColumn,addressColumn,birthDateColumn);
        VBox layout = new VBox(patientTable);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();
    }
    public void menu(Hospital hospital, Stage window){
        GridPane layout = new GridPane();
        Label welcomeMsg= new Label("welcome "+ this.getFirstName());
        Button changeInfo = new Button("change your info");
        changeInfo.setOnAction(e -> {
            System.out.println("check2");
            this.changeInfo(hospital,window);});
        Button  getInfoButton = new Button("get info");
        getInfoButton.setOnAction(e -> getInfoMenu(hospital, window));
        Button  removeButton = new Button("remove user");
        removeButton.setOnAction(e -> remove(hospital, window));
        Button changeSalaryButton = new Button("change salary");
        Button giveDayOffButton = new Button("give day off");
        giveDayOffButton.setOnAction(e -> this.giveDayOff(hospital, window));
        changeSalaryButton.setOnAction(e-> changeSalary(hospital,window));
        Button addButton = new Button("add user");
        addButton.setOnAction(e -> this.addMenu(hospital, window));    
        Button backButton = new Button("back");
        backButton.setOnAction(e -> hospital.signInMenu(hospital,window));
       
        layout.setPadding(new Insets(10, 10, 10, 10)); 
        layout.setVgap(5);
        layout.setHgap(5);
        layout.setAlignment(Pos.CENTER);
        layout.add(welcomeMsg, 0,0);
        layout.add(getInfoButton, 1,1);
        layout.add(changeInfo, 1,2);
        layout.add(changeSalaryButton,1,3);
        layout.add(giveDayOffButton, 1, 4);
        layout.add(addButton, 1, 5);
        layout.add(removeButton, 1,6);
        layout.add(backButton,0,7);
        Scene scene = new Scene(layout);
          welcomeMsg.setStyle("-fx-font-size: 24px;");
        scene.getStylesheets().addAll(App.getClass);
        window.setScene(scene);
    }
    public void getInfoMenu(Hospital hospital, Stage window){
        GridPane layout = new GridPane();
        Label welcomeMsg = new Label("Information Menu");
        Button getInfo = new Button("get your info");
        getInfo.setOnAction(e -> {

            this.getInfo(hospital,window);});
        Button getPatientList = new Button("get the list of all patients");
         getPatientList.setOnAction(e -> this.getPatientList(hospital));
        Button getVisitList = new Button("get the list of all visits");
        getVisitList.setOnAction(e -> this.getVisitList(hospital));
        Button getReserveList = new Button("get the list of all resreves");
        getReserveList.setOnAction(e -> this.getReserveList(hospital));
        Button getNurseList = new Button("get the list of all nurses");
        getNurseList.setOnAction(e -> this.getNurseList(hospital));
        Button getDoctorList = new Button("get the list of all doctors");
        getDoctorList.setOnAction(e -> this.getDoctorList(hospital));
        Button getStaffList = new Button("get the list of all staffs");
        getStaffList.setOnAction(e -> this.getStaffList(hospital));
        layout.setPadding(new Insets(10, 10, 10, 10)); 
        layout.setVgap(5);
        layout.setHgap(5);
        layout.setAlignment(Pos.CENTER);
        Button backButton = new Button("back");
        backButton.setOnAction(e -> this.menu(hospital,window));
        layout.add(welcomeMsg, 0,0);
        layout.add(getInfo, 1,1);
        layout.add(getDoctorList, 1,2);
        layout.add(getPatientList, 1,3);
        layout.add(getVisitList, 1,4);
        layout.add(getReserveList, 1,5);
        layout.add(getNurseList, 1,6);
        layout.add(getStaffList, 1,7);
        layout.add(backButton,0,8);
        Scene scene = new Scene(layout);
          welcomeMsg.setStyle("-fx-font-size: 24px;");
        scene.getStylesheets().addAll(App.getClass);
        window.setScene(scene);
    }
    public void remove(Hospital hospital, Stage window){
        GridPane layout = new GridPane();
        layout.setAlignment(Pos.CENTER);
        layout.setVgap(10);
        layout.setHgap(10);
        layout.setPadding(new Insets(10));
        Label welcomeLabel = new Label("who do you want\nto remove ?");
        Label IDLabel = new Label("user ID : ");
        TextField IDField = new TextField();
        Button removePatientButton = new Button("remove patient");
        removePatientButton.setOnAction(e -> {
            boolean checker = false;
            if(Long.parseLong(IDField.getText())==1){
                AlertBox.display("ERROR", "you cant remove the default user");
            }else{
            try {
                for(Patient patient : hospital.getPatients()){
                    if(patient.getID()==Long.parseLong(IDField.getText())){
                        AlertBox.display("success", "the user successfully removed");
                        DataCenter.remove("patient", Long.parseLong(IDField.getText()));
                        hospital.getPatients().remove(patient);
                        for(Reserve reserve : patient.getReserves(hospital)){
                            hospital.getReserves().remove(reserve);
                        }
                        for(Visit visit : patient.getVisits(hospital)){
                            hospital.getVisits().remove(visit);
                        }
                        checker = true;
                        break;
                    }
                }
                if(!checker){
                    throw new Exception();
                }
            } catch (Exception c) {
                AlertBox.display("not found", "no patient with this ID has been found");
            }
        }
    });
        Button removeDoctorButton = new Button("remove doctor");
        removeDoctorButton.setOnAction(e -> {
            boolean checker = false;
                if(Long.parseLong(IDField.getText())==1){
                AlertBox.display("ERROR", "you cant remove the default user");
            }else{
            try {
                for(Doctor doctor : hospital.getDoctors()){
                    if(doctor.getID()==Long.parseLong(IDField.getText())){
                        AlertBox.display("success", "the user successfully removed");
                        DataCenter.remove("doctor", Long.parseLong(IDField.getText()));
                        hospital.getDoctors().remove(doctor);
                        for(Reserve reserve : doctor.getReserves(hospital)){
                            hospital.getReserves().remove(reserve);
                        }
                        for(Visit visit : doctor.getVisits(hospital)){
                            hospital.getVisits().remove(visit);
                        }
                        checker = true;
                        break;
                    }
                }
                if(!checker){
                    throw new Exception();
                }
            } catch (Exception c) {
                AlertBox.display("not found", "no patient with this ID has been found");
            }
        }});
        Button removeNurseButton = new Button("remove Nurse");
        removeNurseButton.setOnAction(e -> {
            boolean checker = false;
                if(Long.parseLong(IDField.getText())==1){
                AlertBox.display("ERROR", "you cant remove the default user");
            }else{
            try {
                for(Nurse nurse : hospital.getNurses()){
                    if(nurse.getID()==Long.parseLong(IDField.getText())){
                        AlertBox.display("success", "the user successfully removed");
                        DataCenter.remove("Nurse", Long.parseLong(IDField.getText()));
                        hospital.getNurses().remove(nurse);
                        checker = true;
                        break;
                    }
                }
                if(!checker){
                    throw new Exception();
                }
            } catch (Exception c) {
                AlertBox.display("not found", "no patient with this ID has been found");
            }
        }});
        Button removeStaffButton = new Button("remove Staff");
        removeStaffButton.setOnAction(e -> {
            boolean checker = false;
                if(Long.parseLong(IDField.getText())==1){
                AlertBox.display("ERROR", "you cant remove the default user");
            }else{
            try {
                for(Staff staff : hospital.getStaff()){
                    if(staff.getID()==Long.parseLong(IDField.getText())){
                        AlertBox.display("success", "the user successfully removed");
                        DataCenter.remove("Staff", Long.parseLong(IDField.getText()));
                        hospital.getStaff().remove(staff);
                        checker = true;
                        break;
                    }
                }
                if(!checker){
                    throw new Exception();
                }
            } catch (Exception c) {
                AlertBox.display("not found", "no patient with this ID has been found");
            }
        }});
        Button backButton = new Button("back");
        backButton.setOnAction(e -> this.menu(hospital,window));
        layout.add(welcomeLabel, 0, 0);
        layout.add(IDLabel, 0, 1);
        layout.add(IDField, 1, 1);
        layout.add(removePatientButton, 0, 2);
        layout.add(removeDoctorButton, 1, 2);
        layout.add(removeNurseButton, 0, 3);
        layout.add(removeStaffButton, 1, 3);
        layout.add(backButton,0,4);
        Scene scene = new Scene(layout);
          welcomeLabel.setStyle("-fx-font-size: 24px;");
        scene.getStylesheets().addAll(App.getClass);
        window.setScene(scene);
    }
    public void giveDayOff(Hospital hospital, Stage window){
         GridPane layout = new GridPane();
        layout.setAlignment(Pos.CENTER);
        layout.setVgap(10);
        layout.setHgap(10);
        layout.setPadding(new Insets(10));
        Label welcomLabel = new Label("Give a day off:    ");
        Label IDLabel = new Label("user ID : ");
        TextField IDField = new TextField();
        Label dateLabel = new Label("select date :");
        DatePicker datePicker = new DatePicker();
        Button doctorButton = new Button("doctor");
        doctorButton.setOnAction(e ->{
            try{
                if(hospital.searchDoctor(Long.parseLong(IDField.getText()))==null){
                    AlertBox.display("404", "couldn't find any doctor with this ID");
                    throw new Exception();
                }
                Timestamp timestamp = Patient.checkReserve(datePicker.getValue().getYear(), datePicker.getValue().getMonth().getValue(), datePicker.getValue().getDayOfMonth(),hospital.searchDoctor(Long.parseLong(IDField.getText())) , hospital);
                if(timestamp.getHours()==8 && timestamp.getMinutes()==0 ){
                    for(int i=1;i<=25;i++){
                        timestamp=Patient.checkReserve(datePicker.getValue().getYear(), datePicker.getValue().getMonth().getValue(), datePicker.getValue().getDayOfMonth(),hospital.searchDoctor(Long.parseLong(IDField.getText())) , hospital);
                    Reserve reserve = new Reserve(hospital.getPatients().get(0), hospital.searchDoctor(Long.parseLong(IDField.getText())), timestamp);
                    DataCenter.saveReserve(reserve, hospital);    
                }
                AlertBox.display("success", "the doctor "+hospital.searchDoctor(Long.parseLong(IDField.getText())).getFirstName()+" "+hospital.searchDoctor(Long.parseLong(IDField.getText())).getLastName() + " is successfully given a day off");
                }else{
                    AlertBox.display("impossible", "this day is already reserved");
                throw new Exception();
                }    
        
        }catch(Exception c){
        }
        });
          Button nurseButton = new Button("Nurse");
    nurseButton.setOnAction(e-> {
        try{
        boolean checker = false;
        for(Nurse nurse : hospital.getNurses()){
            if(nurse.getID()==Long.parseLong(IDField.getText())){
                AlertBox.display("success", "the nurse "+nurse.getFirstName()+" "+nurse.getLastName() +" is successfully given a day off");
                checker=true;
            }
            if(!checker){
                throw new Exception();
            }
        }
        }catch(Exception c){
            AlertBox.display("404", "no nurse with this ID has been found");
        }
    });
              Button staffButton = new Button("staff     ");
    staffButton.setOnAction(e-> {
        try{
        boolean checker = false;
        for(Staff staff : hospital.getStaff()){
            if(staff.getID()==Long.parseLong(IDField.getText())){
                AlertBox.display("success", "the staff "+staff.getFirstName()+" "+staff.getLastName() +" is successfully given a day off");
                checker=true;
            }
            if(!checker){
                throw new Exception();
            }
        }
        }catch(Exception c){
            AlertBox.display("404", "no staff with this ID has been found");
        }
    });
           Button backButton = new Button("back");
        backButton.setOnAction(e -> this.menu(hospital,window));
        layout.add(welcomLabel, 0, 0);
        layout.add(IDLabel, 0, 1);
        layout.add(IDField, 1, 1);
        layout.add(dateLabel, 0, 2);
        layout.add(datePicker,1,2);
        layout.add(doctorButton,0,3);
        layout.add(nurseButton, 1, 3);
        layout.add(staffButton, 2, 3);
        layout.add(backButton, 0, 4);
               Scene scene = new Scene(layout);
          welcomLabel.setStyle("-fx-font-size: 24px;");
        scene.getStylesheets().addAll(App.getClass);
        window.setScene(scene);
    }
    public void changeSalary(Hospital hospital, Stage window){
        GridPane layout = new GridPane();
        layout.setAlignment(Pos.CENTER);
        layout.setVgap(10);
        layout.setHgap(10);
        layout.setPadding(new Insets(10));
        Label welcomLabel = new Label("who's salary you\nwant to change ?");
        Label IDLabel = new Label("user ID : ");
        TextField IDField = new TextField();
        Label salaryLabel = new Label("new salary : ");
        TextField salaryField = new TextField();

    
        Button changeDoctorButton = new Button("apply for doctor");
        changeDoctorButton.setOnAction(e -> {
            boolean checker = false;
            try {
                for(Doctor doctor : hospital.getDoctors()){
                    if(doctor.getID()==Long.parseLong(IDField.getText())){
                        AlertBox.display("success", "the users salary successfully changed");
                        DataCenter.updateInfo("doctor", "salary", Long.parseLong(IDField.getText()) , Long.parseLong(salaryField.getText()) );
                        doctor.setSalary( Long.parseLong(salaryField.getText()));
                        checker = true;
                        break;
                    }
                }
                if(!checker){
                    throw new Exception();
                }
            } catch (Exception c) {
                AlertBox.display("not found", "no doctor with this ID has been found");
            }
        });
        Button changeNurseButton = new Button("apply for Nurse");
        changeNurseButton.setOnAction(e -> {
            boolean checker = false;
            try {
                for(Nurse nurse : hospital.getNurses()){
                    if(nurse.getID()==Long.parseLong(IDField.getText())){
                            AlertBox.display("success", "the users salary successfully changed");
                            DataCenter.updateInfo("nurse", "salary", Long.parseLong(IDField.getText()) , Long.parseLong(salaryField.getText()) );
                            nurse.setSalary( Long.parseLong(salaryField.getText()));
                            checker = true;
                            break;
                        }
                }
                if(!checker){
                    throw new Exception();
                }
            } catch (Exception c) {
                AlertBox.display("not found", "no nurse with this ID has been found");
            }
        });
        Button changeStaffButton = new Button("apply for Staff");
        changeStaffButton.setOnAction(e -> {
            boolean checker = false;
            try {
                for(Staff staff : hospital.getStaff()){
                    if(staff.getID()==Long.parseLong(IDField.getText())){
                        AlertBox.display("success", "the users salary successfully changed");
                        DataCenter.updateInfo("staff", "salary", Long.parseLong(IDField.getText()) , Long.parseLong(salaryField.getText()) );
                        staff.setSalary( Long.parseLong(salaryField.getText()));
                        checker = true;
                        break;
                    }
                }
                if(!checker){
                    throw new Exception();
                }
            } catch (Exception c) {
                AlertBox.display("not found", "no staff with this ID has been found");
            }
        });
        Button backButton = new Button("back");
        backButton.setOnAction(e -> this.menu(hospital,window));
        layout.add(welcomLabel, 0, 0);
        layout.add(IDLabel, 0, 1);
        layout.add(IDField, 1, 1);
        layout.add(salaryLabel, 0, 2);
        layout.add(salaryField, 1, 2);
        layout.add(changeDoctorButton, 0, 3);
        layout.add(changeNurseButton, 1, 3);
        layout.add(changeStaffButton, 2, 3);
        layout.add(backButton,0,4);
        window.setMinHeight(800);
        window.setMinWidth(600);
               Scene scene = new Scene(layout);
          welcomLabel.setStyle("-fx-font-size: 24px;");
        scene.getStylesheets().addAll(App.getClass);
        window.setScene(scene);
    }
    public void addMenu(Hospital hospital,Stage window){
                GridPane layout = new GridPane();
        Label welcomeMsg = new Label("Information Menu");
        Button getInfo = new Button("get your info");
        getInfo.setOnAction(e -> {

            this.getInfo(hospital,window);});
        Button addDoctor = new Button("add doctor");
         addDoctor.setOnAction(e -> this.addDoctor(hospital,window));
        Button addNurse = new Button("add nurse");
        addNurse.setOnAction(e -> this.addNurse(hospital,window));
        Button addStaff = new Button("add staff");
        addStaff.setOnAction(e -> this.addStaff(hospital,window));
        layout.setPadding(new Insets(10, 10, 10, 10)); 
        layout.setVgap(5);
        layout.setHgap(5);
        layout.setAlignment(Pos.CENTER);
        Button backButton = new Button("back");
        backButton.setOnAction(e -> this.menu(hospital,window));
        layout.add(welcomeMsg, 0,0);
        layout.add(addDoctor, 1,1);
        layout.add(addNurse, 1,2);
        layout.add(addStaff, 1,3);
        layout.add(backButton,0,4);
               Scene scene = new Scene(layout);
          welcomeMsg.setStyle("-fx-font-size: 24px;");
        scene.getStylesheets().addAll(App.getClass);
        window.setScene(scene);
    } 
}
