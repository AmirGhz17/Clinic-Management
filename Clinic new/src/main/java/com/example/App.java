package com.example;

import javafx.application.Application;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {
    static String getClass;
    public static void main(String[] args) {
        launch();
    }
    @Override
    public void start(Stage window) throws Exception {

        Hospital hospital=new Hospital("Amir",352);
        getClass= getClass().getResource("menu.css").toExternalForm();
        DataCenter dataCenter=new DataCenter();
        dataCenter.loadAll(hospital);
        DatePicker dp = new DatePicker();
        
        hospital.menu(window);

    }
}
