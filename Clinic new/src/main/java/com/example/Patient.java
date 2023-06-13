package com.example;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Patient extends Person implements GlobalFunctions {
    private static long IDCreator=1;
    public Patient(String firstName, String lastName, String address, String birthDate, long personalID,String password) {
        super(firstName, lastName, address, birthDate, personalID, password);
        this.setID(IDCreator);
        IDCreator++;
    }

    public Patient(long id,long randomid,String firstName, String lastName, String address, String birthDate, long personalID,String password) {
        super(id,randomid,firstName, lastName, address, birthDate, personalID, password);
    }
    public ObservableList<Reserve> getReserves(Hospital hospital) {
        ObservableList<Reserve> reserves= FXCollections.observableArrayList();
        for(Reserve i : hospital.getReserves()){
            Timestamp currentTime = new Timestamp(new Date().getTime());
                if(currentTime.compareTo(i.getTimestamp())==-1){
                if(i.getPatient()==this){
                reserves.add(i);
            }
          }
        }
        return reserves;
    }

    public ObservableList<Visit> getVisits(Hospital hospital) {
        ObservableList<Visit> visits= FXCollections.observableArrayList();
        for(Visit i : hospital.getVisits()){
            if(i.getPatient()==this){
                visits.add(i);
            }
        }
        return visits;
    }
    public void randomGenerator(Hospital hospital) {
        boolean checker = true;
       while(checker){
        this.setRandomID(new Random().nextLong());
        checker = false;
           if(this.getRandomID()<0){
            this.setRandomID(this.getRandomID()* -1);
        }
        for(Patient i: hospital.getPatients()){
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
                    Patient patient = new Patient(firstNameField.getText(),lastNameField.getText(),addressField.getText(),
                            birthDateField.getText(),Long.parseLong(personalIDField.getText()),passwordField.getText());
                    patient.randomGenerator(hospital);
                    DataCenter.savePatient(hospital,patient);
                    patient.menu(hospital,window);
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
            layout.add(firstNameField, 1, 1);
            layout.add(lastNameField, 1, 2);
            layout.add(personalIDField, 1, 3);
            layout.add(birthDateField, 1, 4);
            layout.add(addressField, 1, 5);
            layout.add(passwordField,1,6);
            layout.add(submitButton,1,7);
            layout.add(backButton, 0, 7);
                  Scene scene = new Scene(layout);
          welcomeMsg.setStyle("-fx-font-size: 24px;");
        scene.getStylesheets().addAll(App.getClass);
        window.setScene(scene);


    }
    public void searchDoctor(Hospital hospital, Stage window){
        GridPane layout = new GridPane();
        Label nameLabel = new Label("name : ");
        Label professionLabel = new Label("profession : ");
        TextField nameField = new TextField();
        TextField professionField = new TextField();
        Button nameButton = new Button("search name");
        nameButton.setOnAction(e -> showSearchResult(hospital,hospital.searchDoctorBYname(nameField.getText())));
        Button professionButton = new Button("search profession");
        professionButton.setOnAction(e -> showSearchResult(hospital,hospital.searchDoctorByProfession(professionField.getText())));
          Button backButton = new Button("back");
            backButton.setOnAction(e -> this.menu(hospital,window));
        layout.add(nameLabel,0,0);
        layout.add(nameField,1,0);
        layout.add(professionLabel,0,1);
        layout.add(professionField,1,1);
        layout.add(nameButton,0,2);
        layout.add(professionButton,1,2);
        layout.add(backButton, 0, 3);
        layout.setAlignment(Pos.CENTER);
        layout.setHgap(10);
        layout.setVgap(10);
        layout.setPadding(new Insets(10));
             Scene scene = new Scene(layout);

        scene.getStylesheets().addAll(App.getClass);
        window.setScene(scene);

    }
    public static void  showSearchResult(Hospital hospital, ObservableList<Doctor> doctors) {
        Stage window = new Stage();
        TableView patientTable = new TableView<>();
        window.setTitle("search result");
        TableColumn<Patient, Long> IDColumn = new TableColumn<>("ID");
        IDColumn.setMinWidth(100);
        IDColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        TableColumn<Patient, String> firstNameColumn = new TableColumn<>("first name");
        firstNameColumn.setMinWidth(100);
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        TableColumn<Patient, String> lastNameColumn = new TableColumn<>("last name");
        lastNameColumn.setMinWidth(100);
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        TableColumn<Patient, Long> personalIDColumn = new TableColumn<>("Profession");
        personalIDColumn.setMinWidth(100);
        personalIDColumn.setCellValueFactory(new PropertyValueFactory<>("profession"));
        TableColumn<Patient, String> addressColumn = new TableColumn<>("Organce");
        addressColumn.setMinWidth(100);
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("Organce"));
        TableColumn<Patient, Boolean> nightShiftColumn = new TableColumn<>("night shift");
        nightShiftColumn.setMinWidth(100);
        nightShiftColumn.setCellValueFactory(new PropertyValueFactory<>("nightShift"));

        patientTable.setItems(doctors);
        patientTable.getColumns().addAll(IDColumn, firstNameColumn, lastNameColumn, personalIDColumn, addressColumn,nightShiftColumn);
        VBox layout = new VBox(patientTable);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();
    }
    public void reserve(Hospital hospital,Stage window){
        GridPane layout = new GridPane();
        Label doctorIDLabel = new Label("doctor ID : ");
        TextField doctorIDField = new TextField();
        doctorIDField.setPromptText("doctor ID");
        Button seeDoctorButton = new Button("see the doctors free times for next week");
        seeDoctorButton.setOnAction(e ->{
            try {
                Doctor doctor = hospital.searchDoctor(Long.parseLong(doctorIDField.getText()));
                seeNextWeekReserves(doctor, hospital);
            } catch (Exception c) {
                AlertBox.display("error", "no doctor with this id has been found");
            }
        });
        Label dateLabel = new Label("pls choose the date");
        DatePicker datePicker = new DatePicker();
        datePicker.setOnAction(e ->{
            if(checkReserve(datePicker.getValue().getYear(), datePicker.getValue().getMonth().getValue(), datePicker.getValue().getDayOfMonth(),hospital.searchDoctor(Long.parseLong(doctorIDField.getText())) , hospital)==null){
                AlertBox.display("not free", "there's no free time for this day");
            }else{
                Timestamp timestamp = checkReserve(datePicker.getValue().getYear(), datePicker.getValue().getMonth().getValue(), datePicker.getValue().getDayOfMonth(),hospital.searchDoctor(Long.parseLong(doctorIDField.getText())) , hospital);
                AlertBox.display("found time", "you can reserve this day for "+ timestamp.getHours()+":"+timestamp.getMinutes());
            }
        });
        Button reserveButton = new Button("reserve");
        reserveButton.setOnAction(e ->{
            try {
                Timestamp timestamp = Timestamp.valueOf(datePicker.getValue().getYear()+"-"+datePicker.getValue().getMonth().getValue()+"-"+datePicker.getValue().getDayOfMonth()+" 0:0:0.0");
                Timestamp currentTime = new Timestamp(new Date().getTime());
                if(currentTime.compareTo(timestamp)==1){
                    AlertBox.display("error", "you must choose a future date");
                    throw new IndexOutOfBoundsException();
                }else if (null==checkReserve(datePicker.getValue().getYear(), datePicker.getValue().getMonth().getValue(), datePicker.getValue().getDayOfMonth(),hospital.searchDoctor(Long.parseLong(doctorIDField.getText())) , hospital)){
                    throw new Exception();
                }else{
                    timestamp=checkReserve(datePicker.getValue().getYear(), datePicker.getValue().getMonth().getValue(), datePicker.getValue().getDayOfMonth(),hospital.searchDoctor(Long.parseLong(doctorIDField.getText())) , hospital);
                    Reserve reserve = new Reserve(this, hospital.searchDoctor(Long.parseLong(doctorIDField.getText())), timestamp);
                    AlertBox.display("success", "you can successfully reserved "+ timestamp);
                    DataCenter.saveReserve(reserve,hospital);
                }
            }catch(IndexOutOfBoundsException c){
                System.out.println(c);
            }
             catch (Exception c) {
               AlertBox.display("error", "we couldnt reserve at this time from this time , \n pls check your choices again");
            }
        });
         Button backButton = new Button("back");
        backButton.setOnAction(e -> this.menu(hospital,window));
       layout.add(doctorIDLabel, 0, 0);
       layout.add(doctorIDField,1,0);
       layout.add(seeDoctorButton, 0, 1);
       layout.add(dateLabel, 0, 2);
       layout.add(datePicker,1,2);
       layout.add(reserveButton, 1, 3);
       layout.add(backButton, 0, 3);
       layout.setVgap(10);
       layout.setHgap(10);
       layout.setAlignment(Pos.CENTER);

       window.setMinWidth(500);
              Scene scene = new Scene(layout);

        scene.getStylesheets().addAll(App.getClass);
        window.setScene(scene);
    }
    public void seeNextWeekReserves(Doctor doctor,Hospital hospital){
       Stage window = new Stage();
       GridPane layout = new GridPane();
       layout.setAlignment(Pos.CENTER);
       layout.setHgap(10);
       layout.setVgap(10);
       layout.setPadding(new Insets(10));

       Label doctorLabel = new Label(doctor.getFirstName()+" "+doctor.getLastName());
       GridPane.setConstraints(doctorLabel, 0, 0);
       Label dateLabel = new Label("Date");
       Label timeLabel = new Label("Time");
       GridPane.setConstraints(dateLabel, 0, 1);
       GridPane.setConstraints(timeLabel, 1, 1);
       layout.getChildren().addAll(doctorLabel,dateLabel,timeLabel);
       Label[] labels = new Label[14];
       Date date = new Date();
        for(int i=0;i<7;i++){
            date = new Date(date.getTime()+1000*60*24*60);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            Timestamp timestamp = checkReserve(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH)+1,cal.get(Calendar.DAY_OF_MONTH),doctor,hospital);
            labels[i] = new Label(cal.get(Calendar.YEAR)+":"+(cal.get(Calendar.MONTH)+1)+":"+cal.get(Calendar.DAY_OF_MONTH)+" :");
            GridPane.setConstraints(labels[i], 0, i+2);
            labels[i+7] = new Label();
            GridPane.setConstraints(labels[i+7], 1, i+2);
            if(timestamp==null){
                labels[i+7].setText("Full");
            }else{
                labels[i+7].setText(timestamp.getHours()+":"+timestamp.getMinutes()); 
            }
            layout.getChildren().addAll(labels[i],labels[i+7]);
        }
               Scene scene = new Scene(layout);

        scene.getStylesheets().addAll(App.getClass);
        window.setScene(scene);
        window.show();
    }
    public static Timestamp checkReserve(int year,int month,int day,Doctor doctor,Hospital hospital){
        try {
           
            ArrayList<Reserve> reserves= doctor.getReservesArray(hospital);
            Timestamp lastReserve = Timestamp.valueOf(year+"-"+month+"-"+day+" 0:0:0.0");
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(lastReserve.getTime());
            if(!doctor.isOrgance()){
                if(cal.get(Calendar.DAY_OF_WEEK)==0||cal.get(Calendar.DAY_OF_WEEK)==1){
                    System.out.println("yekshanbe tatile!!!");
                    return null;
                }
            }
            for(Reserve reserve : reserves){
                cal.setTime(new Date(reserve.getTimestamp().getTime()));
                if(cal.get(Calendar.DAY_OF_MONTH)== day && cal.get(Calendar.MONTH)+1== month && cal.get(Calendar.YEAR)== year) {
                    if(reserve.getTimestamp().compareTo(lastReserve)==1){
                        lastReserve.setTime(reserve.getTimestamp().getTime());
                    }
                }
            }
            if(lastReserve.getHours()==14){
                return null;
            }
            if(lastReserve.getHours()==0){
                return Timestamp.valueOf(year+"-"+month+"-"+day+" 8:0:0.0");
            }
            if(lastReserve.getMinutes()==45){
                lastReserve.setHours(lastReserve.getHours()+1);
                lastReserve.setMinutes(0);
            }else{
                System.out.println(2);
                lastReserve.setMinutes(lastReserve.getMinutes()+15);
            }
            return lastReserve;
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }
    public void changeInfo(Hospital hospital, Stage window){
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
                    DataCenter.updateInfo("patient", "firstName", this.getID(), firstNameField.getText());
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
                    DataCenter.updateInfo("patient", "lastName", this.getID(), lastNameField.getText());
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
                     for(Patient i: hospital.getPatients()){
                         if(i==this){
                             continue;
                         }
                         if(i.getPersonalID()== Long.parseLong(personalIDField.getText())){
                            throw new IndexOutOfBoundsException();
                         }
                        }
                 }
                 this.setPersonalID(Long.parseLong(personalIDField.getText()));
                    DataCenter.updateInfo("patient", "personalID", this.getID(), Long.parseLong(personalIDField.getText()));
                    AlertBox.display("change", "your name has successsfully changed");
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
                    DataCenter.updateInfo("patient", "birthDate", this.getID(), birthDateField.getText());
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
                    DataCenter.updateInfo("patient", "address", this.getID(), addressField.getText());
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
                    DataCenter.updateInfo("patient", "password", this.getID(), newPasswordField.getText());
                    AlertBox.display("change", "your password has successsfully changed");
        }catch(IndexOutOfBoundsException c){
            AlertBox.display("error", "old password is wrong");
        }catch(Exception c){
            AlertBox.display("error", "entry should be between 8 to 40 charachters long  ");
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
        layout.add(firstNameField, 1, 1);
        layout.add(lastNameField, 1, 2);
        layout.add(personalIDField, 1, 3);
        layout.add(birthDateField, 1, 4);
        layout.add(addressField, 1, 5);
        layout.add(oldPasswordField, 1, 6);
        layout.add(newPasswordField, 1, 7);
        layout.add(backButton, 0, 8);
              Scene scene = new Scene(layout);
          welcomeMsg.setStyle("-fx-font-size: 24px;");
        scene.getStylesheets().addAll(App.getClass);
        window.setScene(scene);
        

    }

    @Override
    public void menu(Hospital hospital, Stage window) {
        GridPane layout = new GridPane();
        Label welcomeMsg= new Label("welcome "+ this.getFirstName());
        Button getInfo = new Button("get your info");
        getInfo.setOnAction(e -> {
            System.out.println("check");
            this.getInfo(hospital,window);});
        Button changeInfo = new Button("change your info");
        changeInfo.setOnAction(e -> {
            System.out.println("check2");
            this.changeInfo(hospital,window);});
        Button searchDoctorButton = new Button("search in doctors");
        searchDoctorButton.setOnAction(e -> this.searchDoctor(hospital, window));
        Button reserveButton = new Button("reserve");
        reserveButton.setOnAction(e -> this.reserve(hospital, window));
        Button getVisitList = new Button("get your visit list");
        getVisitList.setOnAction(e -> this.getVisitList(hospital));
        Button getReserveList = new Button("get your reserve list");
        getReserveList.setOnAction(e -> this.getReserveList(hospital));
         Button backButton = new Button("back");
        backButton.setOnAction(e -> hospital.signInMenu(hospital,window));
        layout.setPadding(new Insets(10, 10, 10, 10)); 
        layout.setVgap(5);
        layout.setHgap(5);
        layout.setAlignment(Pos.CENTER);
        layout.add(welcomeMsg, 0,0);
        layout.add(getInfo, 1,1);
        layout.add(changeInfo, 1,2);
        layout.add(searchDoctorButton, 1,3);
        layout.add(reserveButton, 1,4);
        layout.add(getVisitList, 1,5);
        layout.add(getReserveList, 1,6);
        layout.add(backButton, 0, 7);
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
        TableColumn<Patient,String > nameColumn = new TableColumn<>("Doctor name");
        nameColumn.setMinWidth(100);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("doctorName") );
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
        patientTable.setItems(this.getVisits(hospital));
        patientTable.getColumns().addAll(IDColumn,randomIDColumn,nameColumn,diagnoseColumn,drugPrescriptionIDColumn,ordersColumn,timestampColumn,feeColumn);
        VBox layout = new VBox(patientTable);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();
    }
    public void getReserveList(Hospital hospital){
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
        TableColumn<Patient,String > nameColumn = new TableColumn<>("Doctor name");
        nameColumn.setMinWidth(100);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("doctorName") );
        TableColumn<Patient,Timestamp > timestampColumn = new TableColumn<>("Time");
        timestampColumn.setMinWidth(100);
        timestampColumn.setCellValueFactory(new PropertyValueFactory<>("timestamp") );

        patientTable.setItems(this.getReserves(hospital));
        patientTable.getColumns().addAll(IDColumn,randomIDColumn,nameColumn,timestampColumn);
        VBox layout = new VBox(patientTable);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();
    }

    public String getPatientName(){
        return (this.getFirstName()+" "+this.getLastName());
    }
}


//
//    String firstName;
//    String lastName;
//    String address;
//    String birthDate;
//    long personalID=0;
//    String password;
//    Scanner longReader = new Scanner(System.in);
//    Scanner stringReader = new Scanner(System.in);
//        System.out.println("pls enter your first name");
//                firstName = stringReader.nextLine();
//                System.out.println("pls enter your last name");
//                lastName = stringReader.nextLine();
//                System.out.println("pls enter your address");
//                address = stringReader.nextLine();
//                System.out.println("pls enter your birthDate");
//                birthDate = stringReader.nextLine();
//                Boolean checker = true;
//                while(checker){
//                checker = false;
//                System.out.println("pls enter your personal ID number");
//                personalID = longReader.nextLong();
//                for(Patient i: hospital.getPatients()){
//                if(i.getRandomID()==personalID || personalID<0){
//        System.out.println("this personal ID is already in the system");
//        checker = true;
//        break;
//        }
//        }
//        }
//        System.out.println("pls enter your password");
//        password = stringReader.nextLine();
//        Patient patient = new Patient(firstName, lastName, address, birthDate, personalID, password);
//        patient.randomGenerator(hospital);
//        hospital.getPatients().add(patient);
//        DataCenter.savePatient(hospital, patient);
//        stringReader.close();
//        longReader.close();
