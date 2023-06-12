package com.example;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Hospital {
    private String name;
    private long phoneNumber;
    private ArrayList<Patient> patients;
    private ArrayList<Doctor> doctors;
    private ArrayList<Reserve> reserves;
    private ArrayList<Visit>visits;
    private ArrayList<Nurse>nurses;
    private ArrayList<Staff>staff;
    private ArrayList<Admin>admins;


    public Hospital(String name, long phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        patients= new ArrayList<>();
        doctors= new ArrayList<>();
        reserves= new ArrayList<>();
        visits= new ArrayList<>();
        nurses=new ArrayList<>();
        staff=new ArrayList<>();
        admins=new ArrayList<>();
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public long getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public ArrayList<Patient> getPatients() {
        return patients;
    }
    public void setPatients(ArrayList<Patient> patients) {
        this.patients = patients;
    }
    public ArrayList<Doctor> getDoctors() {
        return doctors;
    }
    public void setDoctors(ArrayList<Doctor> doctors) {
        this.doctors = doctors;
    }
    public ArrayList<Reserve> getReserves() {
        return reserves;
    }
    public void setReserves(ArrayList<Reserve> reserves) {
        this.reserves = reserves;
    }
    public ArrayList<Visit> getVisits() {
        return visits;
    }
    public void setVisits(ArrayList<Visit> visits) {
        this.visits = visits;
    }
    public ArrayList<Nurse> getNurses() {
        return nurses;
    }
    public void setNurses(ArrayList<Nurse> nurses) {
        this.nurses = nurses;
    }
    public ArrayList<Staff> getStaff() {
        return staff;
    }
    public void setStaff(ArrayList<Staff> staff) {
        this.staff = staff;
    }

    public ArrayList<Admin> getAdmins() {
        return admins;
    }

    public void setAdmins(ArrayList<Admin> admins) {
        this.admins = admins;
    }
    public void  signUpmenu(Stage window){
        GridPane layout2 = new GridPane();
        Label welcomeMsg= new Label("who do you want to sign up as?");
        Button patientSignUpButton = new Button("sign up as patient");
        patientSignUpButton.setOnAction(e -> Patient.signUp(this, window));
        Button adminSignUpButton = new Button("sign up as admin");
        adminSignUpButton.setOnAction(e-> Admin.signUp(this,window));
         Button backButton = new Button("back");
        backButton.setOnAction(e -> this.menu(window));
        layout2.setPadding(new Insets(10, 10, 10, 10)); 
        layout2.setVgap(5);
        layout2.setHgap(5);
        layout2.setAlignment(Pos.CENTER);
        layout2.add(welcomeMsg, 0,0);
        layout2.add(patientSignUpButton, 1,1);
        layout2.add(adminSignUpButton, 1,2);
        layout2.add(backButton, 0, 3);
        Scene scene = new Scene(layout2);
                welcomeMsg.setStyle("-fx-font-size: 24px;");
           scene.getStylesheets().addAll(getClass().getResource("menu.css").toExternalForm());
        window.setScene(scene);
        window.show();
    }
        public void searchDoctor(Hospital hospital, Stage window){
        GridPane layout = new GridPane();
        Label nameLabel = new Label("name : ");
        Label professionLabel = new Label("profession : ");
        TextField nameField = new TextField();
        TextField professionField = new TextField();
        Button nameButton = new Button("search name");
        nameButton.setOnAction(e -> Patient.showSearchResult(hospital,hospital.searchDoctorBYname(nameField.getText())));
        Button professionButton = new Button("search profession");
        professionButton.setOnAction(e -> Patient.showSearchResult(hospital,hospital.searchDoctorByProfession(professionField.getText())));
          Button backButton = new Button("back");
            backButton.setOnAction(e -> this.menu(window));
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

           scene.getStylesheets().addAll(getClass().getResource("menu.css").toExternalForm());
        window.setScene(scene);

    }
    public void  menu(Stage window){

        GridPane layout2 = new GridPane();
        Label welcomeMsg= new Label("welcome to our clinic");
        Button signUpButton = new Button("sign up as patient");
        signUpButton.setOnAction(e -> Patient.signUp(this, window));
        Button adminSignUpButton = new Button("sign up as admin");
        adminSignUpButton.setOnAction(e -> Admin.signUp(this,window));
        Button signInButton = new Button("sign in");
        signInButton.setOnAction(e-> signInMenu(this,window));
        Button searchDoctorButton = new Button("search for doctor");
        searchDoctorButton.setOnAction(e -> searchDoctor(this, window));
        Button exitButton = new Button("Exit");
        exitButton.setOnAction(e-> window.close());
        layout2.setPadding(new Insets(10, 10, 10, 10));
        layout2.setVgap(5);
        layout2.setHgap(5);
        layout2.setAlignment(Pos.CENTER);
        layout2.add(welcomeMsg, 0,0);
        layout2.add(signInButton, 1,1);
        layout2.add(signUpButton, 1,2);
        layout2.add(adminSignUpButton,1,3);
        layout2.add(searchDoctorButton, 1,4);
        layout2.add(exitButton,1,5);

        Scene scene = new Scene(layout2);
        welcomeMsg.setStyle("-fx-font-size: 24px;");
           scene.getStylesheets().addAll(App.getClass);
      
        window.setScene(scene);
        window.show();
    }
    public void signInMenu(Hospital hospital, Stage window){
        GridPane layout2 = new GridPane();
        layout2.setAlignment(Pos.CENTER);
        Label welcomeLabel = new Label("sign IN");
        Label nameIDLabel = new Label("name/random ID : ");
        Label passLabel = new Label("password : ");
        Label choiceLabel = new Label("what do you want to sign in with? ");
        TextField nameIDField = new TextField();
        TextField passField = new TextField();
        Button adminSignIn = new Button("admin sign in");
        Button patientSignIn = new Button("patient sign in");
        Button doctorSignIn = new Button("doctor sign in");
        Button nurseSignIn = new Button("nurse sign in");
        Button staffSignIn = new Button("staff sign in");
        ToggleGroup tg = new ToggleGroup();
        RadioButton IDSignIn = new RadioButton("random ID");
        IDSignIn.setSelected(true);
        RadioButton nameSignIn = new RadioButton("Last name"); 
        adminSignIn.setOnAction(e ->{
            boolean checker = false;
            try{
            if(nameSignIn.isSelected()){
                for(Admin admin : this.getAdmins()){
                    if(admin.checkSignIn(nameIDField.getText(), passField.getText())){
                        admin.menu(hospital, window);
                        checker = true;
                        break;
                    }
                }
            }else{
                for(Admin admin : this.getAdmins()){
                    if(admin.checkSignIn(Long.parseLong(nameIDField.getText()), passField.getText())){
                        admin.menu(hospital, window);
                        checker = true;
                        break;
                    }
                }
            }
            if (!checker){
                throw new Exception();
            }
        }catch(Exception c){
            AlertBox.display("not found","no user with this ID and password has been found");
        }
        });
        patientSignIn.setOnAction(e ->{
            try{
                boolean checker = false;
            if(nameSignIn.isSelected()){
                for(Patient patient : this.getPatients()){
                    if(patient.checkSignIn(nameIDField.getText(), passField.getText())){
                        patient.menu(hospital, window);
                        checker = true;
                        break;
                    }
                }
            }else{
                for(Patient patient : this.getPatients()){
                    if(patient.checkSignIn(Long.parseLong(nameIDField.getText()), passField.getText())){
                        patient.menu(hospital, window);
                        checker = true;
                        break;
                    }
                }
            }
                if (!checker){
                    throw new Exception();
                }
        }catch(Exception c){
                AlertBox.display("not found","no user with this ID and password has been found");
        }
        });
        doctorSignIn.setOnAction(e ->{
            try{
                boolean checker = false;
            if(nameSignIn.isSelected()){
                for(Doctor doctor : this.getDoctors()){
                    if(doctor.checkSignIn(nameIDField.getText(), passField.getText())){
                        doctor.menu(hospital, window);
                        checker = true;
                        break;
                    }
                }
            }else{
                for(Doctor doctor : this.getDoctors()){
                    if(doctor.checkSignIn(Long.parseLong(nameIDField.getText()), passField.getText())){
                        doctor.menu(hospital, window);
                        checker = true;
                        break;
                    }
                }
            }
                if (!checker){
                    throw new Exception();
                }
        }catch(Exception c){
                AlertBox.display("not found","no user with this ID and password has been found");
        }
        });
        nurseSignIn.setOnAction(e ->{
            try{
                boolean checker = false;

            if(nameSignIn.isSelected()){
                for(Nurse nurse : this.getNurses()){
                    if(nurse.checkSignIn(nameIDField.getText(), passField.getText())){
                        nurse.menu(hospital, window);
                        checker = true;
                        break;
                    }
                }
            }else{
                for(Nurse nurse : this.getNurses()){
                    if(nurse.checkSignIn(Long.parseLong(nameIDField.getText()), passField.getText())){
                        nurse.menu(hospital, window);
                        checker = true;
                        break;
                    }
                }
            }
                if (!checker){
                    throw new Exception();
                }
        }catch(Exception c){
                AlertBox.display("not found","no user with this ID and password has been found");
        }
        });
        staffSignIn.setOnAction(e ->{
            try{
                boolean checker = false;
            if(nameSignIn.isSelected()){
                for(Staff staff : this.getStaff()){
                    if(staff.checkSignIn(nameIDField.getText(), passField.getText())){
                        staff.menu(hospital, window);
                        checker = true;
                        break;
                    }
                }
            }else{
                for(Staff staff : this.getStaff()){
                    if(staff.checkSignIn(Long.parseLong(nameIDField.getText()), passField.getText())){
                        staff.menu(hospital, window);
                        checker = true;
                        break;
                    }
                }
            }
                if (!checker){
                    throw new Exception();
                }
        }catch(Exception c){
                AlertBox.display("not found","no user with this ID and password has been found");
        }
        });
         Button backButton = new Button("back");
        backButton.setOnAction(e -> this.menu(window));
        IDSignIn.setToggleGroup(tg);
        nameSignIn.setToggleGroup(tg);
        layout2.add(welcomeLabel, 0, 0);
        layout2.add(nameIDLabel, 0, 1);
        layout2.add(nameIDField, 1, 1);
        layout2.add(passLabel, 0, 2);
        layout2.add(passField, 1, 2);
        layout2.add(choiceLabel, 0, 3);
        layout2.add(IDSignIn, 1, 3);
        layout2.add(nameSignIn, 2, 3);
        layout2.add(patientSignIn,0,4);
        layout2.add(doctorSignIn,1,4);
        layout2.add(nurseSignIn,2,4);
        layout2.add(staffSignIn,0,5);
        layout2.add(adminSignIn,1,5);
        layout2.add(backButton, 0, 6);
                
                Scene scene= new Scene(layout2);
welcomeLabel.setStyle("-fx-font-size: 24px;");
           scene.getStylesheets().addAll(App.getClass);
        window.setScene(scene);
    }
    public Doctor searchDoctor(long search){
        for(Doctor doctor: this.doctors){
            if (doctor.getID()== search){
                System.out.println(doctor.getFirstName()+doctor.getLastName()+" : "+doctor.getProfession());
                return doctor;
            }
        }
        return null;
    }
    public ObservableList<Doctor> searchDoctorBYname(String search){
        ObservableList<Doctor> doctors= FXCollections.observableArrayList();
        for(Doctor doctor: this.doctors){
            if ((doctor.getFirstName()+ " " + doctor.getLastName()).contains(search)){
               doctors.add(doctor);
            }
        }
        return doctors;
    }

    public ObservableList<Doctor> searchDoctorByProfession(String search){
        ObservableList<Doctor> doctors= FXCollections.observableArrayList();
        for(Doctor doctor: this.doctors){
            if (doctor.getProfession().contains( search)){
                doctors.add(doctor);

            }
        }
        return doctors;
    }
    public Patient searchPatient(long search){
        for(Patient patient: this.patients){
            if (patient.getID()== search){
                System.out.println(patient.getFirstName()+patient.getLastName());
                return patient;
            }
        }
        return null;
    }
    
    
}
