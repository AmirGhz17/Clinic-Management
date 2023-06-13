package com.example;
import java.sql.Timestamp;

public abstract class Ticket {
    private Patient patient;
    private Doctor doctor;
    private Timestamp timestamp;
    private long ID;
    private long randomID;
    public long getID() {
        return ID;
    }
    public void setID(long iD) {
        ID = iD;
    }

    public Ticket(Patient patient, Doctor doctor, Timestamp timestamp) {
        this.patient = patient;
        this.doctor = doctor;
        this.timestamp = timestamp;

    }
    public Ticket(long ID,long randomID,Patient patient, Doctor doctor, Timestamp timestamp) {
        this.patient = patient;
        this.doctor = doctor;
        this.timestamp = timestamp;
        this.randomID= randomID;
        this.ID=ID;
    }
    public Patient getPatient() {
        return patient;
    }
    public Doctor getDoctor() {
        return doctor;
    }
    public Timestamp getTimestamp() {
        return timestamp;
    }


    public long getRandomID() {
        return randomID;
    }



    public void setRandomID(long privateID) {
        this.randomID = privateID;
    }
    public String getPatientName(){
        return this.getPatient().getFirstName()+" "+this.getPatient().getLastName();
    }
    public String getDoctorName(){
        return this.getDoctor().getFirstName()+" "+this.getDoctor().getLastName();
    }
}
