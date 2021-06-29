package com.company;

import com.company.Witers.Sender;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;

public class Main extends Application {
    public static ArrayList<String> commands = new ArrayList<>();
    public static Sender sender;
    public static String login = "";
    public static String password = "";

    public static void main(String[] args) throws IOException {
        commands.add("add");
        commands.add("show");
        commands.add("update_by_id");
        commands.add("clear");
        commands.add("Execute_script");
        commands.add("Group_counting_by_coordinates");
        commands.add("Head");
        commands.add("Help");
        commands.add("Info");
        commands.add("Print_field_descending_type");
        commands.add("Remove_all_by_venue");
        commands.add("Remove_by_id");
        commands.add("Remove_first");
        commands.add("Remove_lower");



        sender = new Sender(InetAddress.getByName("192.168.5.1"), 1111);


        Application.launch(Main.class);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Login");
        stage.setWidth(300);
        stage.setHeight(300);


        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(20,20,20,20));


        Text scenetitle  = new Text("Login");
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

        Button btn = new Button("Sign in");
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.BOTTOM_RIGHT);
        hBox.getChildren().add(btn);
        gridPane.add(hBox,1,4);

        Text actiontarget  = new Text();
        gridPane.add(actiontarget,1,6);

        btn.setOnAction(e -> {
            if(usertextField.getText().replace(" " , "").equals("") || pwBox.getText().replace(" ", "").equals("")){
                actiontarget.setText("неверно введены поля");
            }
            else {
                login = usertextField.getText();
                password = pwBox.getText();
                try {
                    stage.close();
                    new MainWindow().start(new Stage());
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
