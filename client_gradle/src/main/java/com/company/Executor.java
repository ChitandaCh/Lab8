package com.company;

import com.company.Commands.Empty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Executor {

    public void start(Stage stage, String m){
        stage.setTitle("Login");
        stage.setWidth(300);
        stage.setHeight(300);


        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(20);

        TextField textField = new TextField();
        textField.setPrefWidth(400);
        textField.setPrefHeight(40);

        Button button = new Button("start");
        button.setPrefWidth(300);
        button.setPrefHeight(50);

        Label text = new Label();
        text.setPrefWidth(100);
        text.setPrefHeight(30);


        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Empty empty = new Empty();
                empty.setName(m);
                empty.login = Main.login;
                empty.password = Main.password;
                empty.args = Arrays.asList(textField.getText().split(","));
                try {
                    Main.sender.Send(empty);
                    text.setText(Main.sender.Recieve().responces.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        gridPane.add(textField, 0, 0);
        gridPane.add(button, 0, 1);
        gridPane.add(text, 0 ,2);

        Scene scene = new Scene(gridPane, 700, 700);
        stage.setScene(scene) ;
        stage.show();
    }
}
