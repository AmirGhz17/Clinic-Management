package com.example;

import java.sql.Timestamp;
import java.util.Random;
public class Reserve extends Ticket {

    private static long IDCreator=1;
    public static long getIDCreator() {
        return IDCreator;
    }
    public static void setIDCreator(long iDCreator) {
        IDCreator = iDCreator;
    }
    public Reserve(Patient patient, Doctor doctor, Timestamp timestamp) {
        super(patient, doctor, timestamp);
        this.setID(IDCreator);
        IDCreator++;
    }
    public Reserve(long ID, long randomID, Patient patient, Doctor doctor, Timestamp timestamp) {
        super(ID, randomID, patient, doctor, timestamp);
    }
    public void randomGenerator(Hospital hospital) {
        boolean checker = true;
        while(checker){
            this.setRandomID(new Random().nextLong());
            checker = false;
               if(this.getRandomID()<0){
            this.setRandomID(this.getRandomID()* -1);
        }
            for(Reserve i: hospital.getReserves()){
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
}
