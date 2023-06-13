package com.example;
import java.util.Random;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Staff extends ClinicMember {
    String placeholder;
    private static long IDCreator=1;
    public Staff(String firstName, String lastName, String address, String birthDate, long personalID, long salary,
            boolean nightShift, boolean organce, String password) {
        super(firstName, lastName, address, birthDate, personalID, salary, nightShift, organce, password);
        this.setID(IDCreator);
        IDCreator++;
    }

    public Staff(long ID, long randomID, String firstName, String lastName, String address, String birthDate, long personalID, long salary, boolean nightShift, boolean organce, String password) {
        super(ID, randomID, firstName, lastName, address, birthDate, personalID, salary, nightShift, organce, password);
    }

    public void randomGenerator(Hospital hospital) {
        boolean checker = true;
       while(checker){
        this.setRandomID(new Random().nextLong());
        checker = false;
           if(this.getRandomID()<0){
            this.setRandomID(this.getRandomID()* -1);
        }
        for(Staff i: hospital.getStaff()){
            if(i==this){
                continue;
            }
            if(i.getRandomID()==this.getRandomID()){
                checker = true;
                break;
            }
        }
       }
    }
    public static long getIDCreator() {
        return IDCreator;
    }
    public static void setIDCreator(long iDCreator) {
        IDCreator = iDCreator;
    }


    public void changeInfo(Hospital hospital, Stage window){
        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10)); 
        layout.setVgap(10);
        layout.setHgap(10);
        layout.setAlignment(Pos.CENTER);
        Label welcomeMsg= new Label("what do you want to change? \n press enter to finalize changes");
        Label firstNameLabel = new Label("first name : ");
        Label lastNameLabel = new Label("last name : ");
        Label personalIDLabel = new Label("personal ID : ");
        Label birthDateLabel = new Label("birth date : ");
        Label addressLabel = new Label("address : ");
        Label organceLabel = new Label("organce : ");
        Label nightShiftLabel = new Label("night shift : ");
        Label password = new Label("password : ");
        
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
        firstNameField.setOnAction(e ->{
            if(firstNameField.getText()!= this.getFirstName()){
                if(!checker(firstNameField.getText(), 100, 1, true)){
                    AlertBox.display("error", "entry should be between 1 and 100 letters ");
                }else{
                    this.setFirstName(firstNameField.getText());
                    DataCenter.updateInfo("staff", "firstName", this.getID(), firstNameField.getText());
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
                    DataCenter.updateInfo("staff", "lastName", this.getID(), lastNameField.getText());
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
                     for(Staff i: hospital.getStaff()){
                         if(i==this){
                             continue;
                         }
                         if(i.getPersonalID()== Long.parseLong(personalIDField.getText())){
                            throw new IndexOutOfBoundsException();
                         }
                        }
                 }
                 this.setPersonalID(Long.parseLong(personalIDField.getText()));
                    DataCenter.updateInfo("staff", "personalID", this.getID(), Long.parseLong(personalIDField.getText()));
                    AlertBox.display("change", "your personalID has successsfully changed");
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
                    DataCenter.updateInfo("staff", "birthDate", this.getID(), birthDateField.getText());
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
                    DataCenter.updateInfo("staff", "address", this.getID(), addressField.getText());
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
                 this.setPassword(newPasswordField.getText());;
                    DataCenter.updateInfo("staff", "password", this.getID(), newPasswordField.getText());
                    AlertBox.display("change", "your password has successsfully changed");
        }catch(IndexOutOfBoundsException c){
            AlertBox.display("error", "old password is wrong");
        }catch(Exception c){
            AlertBox.display("error", "entry should be between 8 to 40 charachters long  ");
        }
        });
        RadioButton organceButton = new RadioButton();
        organceButton.setSelected(this.isOrgance());
        organceButton.setOnAction(e -> {

            DataCenter.updateInfo("staff", "organce", this.getID(), organceButton.isSelected());
            if(organceButton.isSelected()){
                    AlertBox.display("change", "you're now in the organce crew");
            }else{
                AlertBox.display("change", "you successfully left the organce crew");
            }
        });
        RadioButton nightShiftButton = new RadioButton();
        nightShiftButton.setSelected(this.isNightShift());
        nightShiftButton.setOnAction(e -> {

            DataCenter.updateInfo("staff", "nightShift", this.getID(), nightShiftButton.isSelected());
            if(nightShiftButton.isSelected()){
                    AlertBox.display("change", "you're now in the night shift crew");
            }else{
                AlertBox.display("change", "you successfully left the night shift crew");
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
        layout.add(organceLabel, 0, 6);
        layout.add(nightShiftLabel, 0, 7);
        layout.add(password, 0, 8);
        layout.add(firstNameField, 1, 1);
        layout.add(lastNameField, 1, 2);
        layout.add(personalIDField, 1, 3);
        layout.add(birthDateField, 1, 4);
        layout.add(addressField, 1, 5);
        layout.add(organceButton, 1, 6);
        layout.add(nightShiftButton, 1, 7);
        layout.add(oldPasswordField, 1, 8);
        layout.add(newPasswordField, 1, 9);
        layout.add(backButton, 0, 10);
          Scene scene = new Scene(layout);
          welcomeMsg.setStyle("-fx-font-size: 24px;");
        scene.getStylesheets().addAll(App.getClass);
        window.setScene(scene);

    }
}
