package com.example;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {
    public static void display(String title , String message) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(250);
        window.setMinHeight(300);
        Label label = new Label(message);
        Button closeButton = new Button("close");
        closeButton.setOnAction(e -> window.close());
        VBox layout = new VBox(label,closeButton); 
        layout.setAlignment(Pos.CENTER);
        window.setScene(new Scene(layout));
        window.showAndWait();
    }
}
