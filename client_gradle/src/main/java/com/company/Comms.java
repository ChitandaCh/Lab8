package com.company;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Comms {

    public void start(Stage primaryStage) throws IOException {

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        primaryStage.setWidth(1000);
        primaryStage.setHeight(200);

        for (int i = 0; i < Main.commands.size(); i++){
            Button bu = new Button();
            bu.setPrefHeight(50);
            bu.setPrefWidth(100);
            bu.setVisible(false);
            gridPane.add(bu, i ,0 );

            Button button = new Button(Main.commands.get(i));
            button.setPrefWidth(70);
            button.setPrefHeight(20);

            int finalI = i;

            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    String m = Main.commands.get(finalI);

                    if(Main.commands.contains(m)){
                        new Executor().start(new Stage(), m);
                    }
                }
            });

            gridPane.add(button, i, 1);
        }

        primaryStage.setScene(new Scene(gridPane));
        primaryStage.show();
    }
}
