package com.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DataCenter {
    static Connection connection;
    static Statement statement;
    static ResultSet resultSet;
    DataCenter(){
        try{
        String url = "jdbc:mysql://localhost:3306/clinic";
        connection = DriverManager.getConnection(url, "root", "1382");
        statement = connection.createStatement();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public static void loadAll(Hospital hospital){
        try {
            resultSet = statement.executeQuery("select * from doctor");
            // Doctor(long ID, long randomID, String firstName, String lastName, String address, String birthDate,
            // long personalID, long salary, boolean nightShift, boolean organce, String password, String profession)
            while(resultSet.next()){
                System.out.println("check");
                hospital.getDoctors().add(new Doctor(resultSet.getLong(1),resultSet.getLong(2),resultSet.getString(3),
                resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getLong(7),
                resultSet.getLong(8),resultSet.getBoolean(9),resultSet.getBoolean(10),
                resultSet.getString(11),resultSet.getString(12)));
            }
            Doctor.setIDCreator(1+hospital.getDoctors().get(hospital.getDoctors().size()-1).getID());
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            resultSet = statement.executeQuery("select * from patient");
            // Doctor(long ID, long randomID, String firstName, String lastName, String address, String birthDate,
            //int id,int randomid,String firstName, String lastName, String address, String birthDate, long personalID,String password
            // long personalID, long salary, boolean nightShift, boolean organce, String password, String profession)
            while(resultSet.next()){
                hospital.getPatients().add(new Patient(resultSet.getLong(1),resultSet.getLong(2),resultSet.getString(3),
                resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getLong(7),
                resultSet.getString(8)));
            }
            Patient.setIDCreator(1+hospital.getPatients().get(hospital.getPatients().size()-1).getID());
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            resultSet = statement.executeQuery("select * from admin");
            // Doctor(long ID, long randomID, String firstName, String lastName, String address, String birthDate,
            //int id,int randomid,String firstName, String lastName, String address, String birthDate, long personalID,String password
            // long personalID, long salary, boolean nightShift, boolean organce, String password, String profession)
            while(resultSet.next()){
                hospital.getAdmins().add(new Admin(resultSet.getLong(1),resultSet.getLong(2),resultSet.getString(3),
                        resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getLong(7),
                        resultSet.getString(8)));
                Admin.setAdminPassword(resultSet.getString(9));
            }
            Admin.setIDCreator(1+hospital.getAdmins().get(hospital.getAdmins().size()-1).getID());
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            resultSet = statement.executeQuery("select * from Nurse");
            // Doctor(long ID, long randomID, String firstName, String lastName, String address, String birthDate,
            //int id,int randomid,String firstName, String lastName, String address, String birthDate, long personalID,String password
            // long personalID, long salary, boolean nightShift, boolean organce, String password, String profession)
            while(resultSet.next()){
                System.out.println("check");
                hospital.getNurses().add(new Nurse(resultSet.getLong(1),resultSet.getLong(2),resultSet.getString(3),
                        resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getLong(7),
                        resultSet.getLong(8),resultSet.getBoolean(9),resultSet.getBoolean(10),
                        resultSet.getString(11)));
            }
            Nurse.setIDCreator(1+hospital.getNurses().get(hospital.getNurses().size()-1).getID());

        } catch (Exception e) {
            System.out.println(e);
        }


    try {
        resultSet = statement.executeQuery("select * from staff");
        // Doctor(long ID, long randomID, String firstName, String lastName, String address, String birthDate,
        //int id,int randomid,String firstName, String lastName, String address, String birthDate, long personalID,String password
        // long personalID, long salary, boolean nightShift, boolean organce, String password, String profession)
        while(resultSet.next()){
            System.out.println("check");
            hospital.getStaff().add(new Staff(resultSet.getLong(1),resultSet.getLong(2),resultSet.getString(3),
                    resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getLong(7),
                    resultSet.getLong(8),resultSet.getBoolean(9),resultSet.getBoolean(10),
                    resultSet.getString(11)));
        }
        Staff.setIDCreator(1+hospital.getStaff().get(hospital.getStaff().size()-1).getID());

    } catch (Exception e) {
        System.out.println(e);
    }



        try {
            resultSet = statement.executeQuery("select * from visit");
            // Doctor(long ID, long randomID, String firstName, String lastName, String address, String birthDate,
            //int id,int randomid,String firstName, String lastName, String address, String birthDate, long personalID,String password
            // long personalID, long salary, boolean nightShift, boolean organce, String password, String profession)
            while(resultSet.next()){
                System.out.println("check");
                Patient patient= null;
                Doctor doctor = null;
                for(Doctor i : hospital.getDoctors()){
                    if(i.getID()== resultSet.getLong(4)){
                        doctor = i;
                    }
                }
                for(Patient i : hospital.getPatients()){
                    if(i.getID()== resultSet.getLong(3)){
                        patient = i;
                    }
                }
                hospital.getVisits().add(new Visit(resultSet.getLong(1),resultSet.getLong(2),patient,
                        doctor,resultSet.getTimestamp(5),resultSet.getString(6),resultSet.getString(7),
                        resultSet.getLong(8),resultSet.getString(9)));
            }
            Visit.setIDCreator(1+hospital.getVisits().get(hospital.getVisits().size()-1).getID());

        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            resultSet = statement.executeQuery("select * from reserve");
            // Doctor(long ID, long randomID, String firstName, String lastName, String address, String birthDate,
            //int id,int randomid,String firstName, String lastName, String address, String birthDate, long personalID,String password
            // long personalID, long salary, boolean nightShift, boolean organce, String password, String profession)
            while(resultSet.next()){
                System.out.println("check");
                Patient patient= null;
                Doctor doctor = null;
                for(Doctor i : hospital.getDoctors()){
                    if(i.getID()== resultSet.getLong(4)){
                        doctor = i;
                    }
                }
                for(Patient i : hospital.getPatients()){
                    if(i.getID()== resultSet.getLong(3)){
                        patient = i;
                    }
                }
                hospital.getReserves().add(new Reserve(resultSet.getLong(1),resultSet.getLong(2),patient,
                        doctor,resultSet.getTimestamp(5)));
            }
            Reserve.setIDCreator(1+hospital.getReserves().get(hospital.getReserves().size()-1).getID());

        } catch (Exception e) {
            System.out.println(e);
        }
}




    public static void saveDoctor(Hospital hospital){
        try{
        PreparedStatement ps = connection.prepareStatement("insert into doctor (ID, randomID, firstName, lastName, address, birthDate,"+
        "personalID, salary, nightShift, organce, password, profession)" + "values (?,?,?,?,?,?,?,?,?,?,?,?)");
        for(Doctor i : hospital.getDoctors()){
            ps.setLong(1, i.getID());
            ps.setLong(2, i.getRandomID());
            ps.setString(3, i.getFirstName());
            ps.setString(4, i.getLastName());
            ps.setString(5, i.getAddress());
            ps.setString(6, i.getBirthDate());
            ps.setLong(7, i.getPersonalID());
            ps.setLong(8, i.getSalary());
            ps.setBoolean(9, i.isNightShift());
            ps.setBoolean(10, i.isOrgance());
            ps.setString(11, i.getPassword());
            ps.setString(12, i.getProfession());
            ps.executeUpdate();
        }
    }catch(Exception e){
        System.out.println(e);
    }
    }
    public static void saveDoctor(Hospital hospital,Doctor doctor){
        hospital.getDoctors().add(doctor);
        doctor.randomGenerator(hospital);
        try{
        PreparedStatement ps = connection.prepareStatement("insert into doctor (ID, randomID, firstName, lastName, address, birthDate,"+
        "personalID, salary, nightShift, organce, password, profession)" + "values (?,?,?,?,?,?,?,?,?,?,?,?)");
            hospital.getDoctors().add(doctor);
            doctor.randomGenerator(hospital);
            ps.setLong(1, doctor.getID());

            ps.setLong(2, doctor.getRandomID());
            ps.setString(3, doctor.getFirstName());
            ps.setString(4, doctor.getLastName());
            ps.setString(5, doctor.getAddress());
            ps.setString(6, doctor.getBirthDate());
            ps.setLong(7, doctor.getPersonalID());
            ps.setLong(8, doctor.getSalary());
            ps.setBoolean(9, doctor.isNightShift());
            ps.setBoolean(10, doctor.isOrgance());
            ps.setString(11, doctor.getPassword());
            ps.setString(12, doctor.getProfession());
            ps.executeUpdate();
    }catch(Exception e){
        System.out.println(e);
    }
    }
    public static void savePatient(Hospital hospital){

        try{
            PreparedStatement ps = connection.prepareStatement("insert into patient (ID, randomID, firstName, lastName, address, birthDate,"+
            " personalID, password)" + "values (?,?,?,?,?,?,?,?)");
            for(Patient i : hospital.getPatients()){
                ps.setLong(1, i.getID());
                ps.setLong(2, i.getRandomID());
                ps.setString(3, i.getFirstName());
                ps.setString(4, i.getLastName());
                ps.setString(5, i.getAddress());
                ps.setString(6, i.getBirthDate());
                ps.setLong(7, i.getPersonalID());
                ps.setString(8, i.getPassword());
                ps.executeUpdate();
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public static void savePatient(Hospital hospital,Patient patient){
        hospital.getPatients().add(patient);
        patient.randomGenerator(hospital);
        try{
            PreparedStatement ps = connection.prepareStatement("insert into patient (ID, randomID, firstName, lastName, address, birthDate,"+
            " personalID, password)" + "values (?,?,?,?,?,?,?,?)");
                ps.setLong(1, patient.getID());
                ps.setLong(2, patient.getRandomID());
                ps.setString(3, patient.getFirstName());
                ps.setString(4, patient.getLastName());
                ps.setString(5, patient.getAddress());
                ps.setString(6, patient.getBirthDate());
                ps.setLong(7, patient.getPersonalID());
                ps.setString(8, patient.getPassword());
                ps.executeUpdate();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public static void saveNurse(Hospital hospital,Nurse nurse){
        hospital.getNurses().add( nurse);
        nurse.randomGenerator(hospital);
        try{
            PreparedStatement ps = connection.prepareStatement("insert into nurse (ID, randomID, firstName, lastName, address, birthDate,"+
            " personalID,salary,nightshift,organce, password)" + "values (?,?,?,?,?,?,?,?,?,?,?)");
                ps.setLong(1, nurse.getID());
                ps.setLong(2, nurse.getRandomID());
                ps.setString(3, nurse.getFirstName());
                ps.setString(4, nurse.getLastName());
                ps.setString(5, nurse.getAddress());
                ps.setString(6, nurse.getBirthDate());
                ps.setLong(7, nurse.getPersonalID());
                ps.setLong(8, nurse.getSalary());
                ps.setBoolean(9, nurse.isNightShift());
                ps.setBoolean(10, nurse.isOrgance());
                ps.setString(11, nurse.getPassword());
                ps.executeUpdate();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public static void saveStaff(Hospital hospital,Staff staff){
        hospital.getStaff().add( staff);
        staff.randomGenerator(hospital);
        try{
            PreparedStatement ps = connection.prepareStatement("insert into staff (ID, randomID, firstName, lastName, address, birthDate,"+
            " personalID,salary,nightshift,organce, password)" + "values (?,?,?,?,?,?,?,?,?,?,?)");
                ps.setLong(1, staff.getID());
                ps.setLong(2, staff.getRandomID());
                ps.setString(3, staff.getFirstName());
                ps.setString(4, staff.getLastName());
                ps.setString(5, staff.getAddress());
                ps.setString(6, staff.getBirthDate());
                ps.setLong(7, staff.getPersonalID());
                ps.setLong(8, staff.getSalary());
                ps.setBoolean(9, staff.isNightShift());
                ps.setBoolean(10, staff.isOrgance());
                ps.setString(11, staff.getPassword());
                ps.executeUpdate();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public static void saveAdmin(Hospital hospital,Admin admin){
        hospital.getAdmins().add(admin);
        admin.randomGenerator(hospital);
        try{
            PreparedStatement ps = connection.prepareStatement("insert into admin (ID, randomID, firstName, lastName, address, birthDate,"+
            " personalID, password,adminPassword)" + "values (?,?,?,?,?,?,?,?,?)");
                ps.setLong(1, admin.getID());
                ps.setLong(2, admin.getRandomID());
                ps.setString(3, admin.getFirstName());
                ps.setString(4, admin.getLastName());
                ps.setString(5, admin.getAddress());
                ps.setString(6, admin.getBirthDate());
                ps.setLong(7, admin.getPersonalID());
                ps.setString(8, admin.getPassword());
                ps.setString(9, Admin.getAdminPassword());
                ps.executeUpdate();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public static void saveVisit(Hospital hospital,Visit visit){
            hospital.getVisits().add(visit);
            visit.randomGenerator(hospital);
        try{
            PreparedStatement ps = connection.prepareStatement("insert into visit (ID, randomID, patientID, doctorID, timestamp, drugprescription,orders,fee,diagnose)"+
                    "values (?,?,?,?,?,?,?,?,?)");

            ps.setLong(1, visit.getID());
            ps.setLong(2, visit.getRandomID());
            ps.setLong(3, visit.getPatient().getID());
            ps.setLong(4, visit.getDoctor().getID());
            ps.setTimestamp(5, visit.getTimestamp());
            ps.setString(6, visit.getDrugPrescription());
            ps.setString(7, visit.getOrders());
            ps.setLong(8, visit.getFee());
            ps.setString(9, visit.getDiagnose());
            ps.executeUpdate();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public static void saveReserve(Reserve reserve , Hospital hospital){
        hospital.getReserves().add(reserve);
        reserve.randomGenerator(hospital);
        try{
            PreparedStatement ps = connection.prepareStatement("insert into reserve (ID, randomID, patientID, doctorID, timestamp)"+
                    " values (?,?,?,?,?)");

            ps.setLong(1, reserve.getID());
            ps.setLong(2, reserve.getRandomID());
            ps.setLong(3, reserve.getPatient().getID());
            ps.setLong(4, reserve.getDoctor().getID());
            ps.setTimestamp(5, reserve.getTimestamp());
            ps.executeUpdate();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public static void updateInfo(String changeClass, String element , long ID, String newValue){
        try {
            PreparedStatement ps = connection.prepareStatement("update "+changeClass+" set "+element+" = ? where ID = ?");
            ps.setString(1, newValue);
            ps.setLong(2, ID);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void updateInfo(String changeClass, String element , long ID, Boolean newValue){
        try {
            PreparedStatement ps = connection.prepareStatement("update "+changeClass+" set "+element+" = ? where ID = ?");
            ps.setBoolean(1, newValue);
            ps.setLong(2, ID);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void updateInfo(String changeClass, String element , long ID, long newValue){
        try {
            PreparedStatement ps = connection.prepareStatement("update "+changeClass+" set "+element+" = ? where ID = ?");
            ps.setLong(1, newValue);
            ps.setLong(2, ID);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void remove(String removeClass, long ID){
        try {
            PreparedStatement ps = connection.prepareStatement("delete from "+removeClass+" where ID = ?");  
            ps.setLong(1, ID);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
