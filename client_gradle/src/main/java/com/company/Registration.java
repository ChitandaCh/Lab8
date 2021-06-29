package com.company;

import com.company.Commands.Empty;
import com.company.Helpes.Parser;
import com.company.Models.Responce;
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
import java.util.ConcurrentModificationException;

public class Registration {
    public void start(Stage stage) {
        stage.setTitle("Reg");
        stage.setWidth(300);
        stage.setHeight(300);


        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(20,20,20,20));


        Text scenetitle  = new Text("Reg");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        gridPane.add(scenetitle,0,0,2,1);

        Label user_name = new Label("User Name");
        gridPane.add(user_name,0,1);

        TextField usertextField = new TextField();
        gridPane.add(usertextField,1,1);

        Label pass_word = new Label("Pass Word");
        gridPane.add(pass_word,0,2);

        PasswordField pwBox  = new PasswordField();
        gridPane.add(pwBox,1,2);

        Button btn = new Button("Reg");
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.BOTTOM_RIGHT);
        hBox.getChildren().add(btn);
        gridPane.add(hBox,1,4);

        Text actiontarget  = new Text();
        gridPane.add(actiontarget,1,6);

        btn.setOnAction(e -> {
            if(user_name.getText().replace(" " , "").equals("") || pwBox.getText().replace(" ", "").equals("")){
                actiontarget.setText("неверно введены поля");
            }
            else {
                Empty empty = new Empty();
                empty.password = user_name.getText();
                empty.login = pwBox.getText();
                empty.setName("register");
                try {
                    Main.sender.Send(empty);

                    Responce responce = Main.sender.Recieve();
                    actiontarget.setText(responce.responces.toString());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                try {
                    stage.close();
                    new Main().start(new Stage());
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });


        Scene scene = new Scene(gridPane, 700, 700);
        stage.setScene(scene) ;
        stage.show();
    }
}
