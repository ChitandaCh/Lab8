package com.company;

import com.company.Commands.Empty;
import com.company.Helpes.Parser;
import com.company.Models.Responce;
import com.company.Models.Ticket;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Border;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class MainWindow {

    public void start(Stage primaryStage) throws IOException {

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        primaryStage.setWidth(1700);
        primaryStage.setHeight(600);


        Label label = new Label();
        label.setText("login: " + Main.login);
        label.setPrefWidth(100);
        label.setPrefHeight(30);
        gridPane.add(label, 2, 2);

        Button Register = new Button("reg");
        Register.setPrefHeight(30);
        Register.setPrefWidth(100);
        Register.setOnAction(event -> {
            primaryStage.close();
            new Registration().start(new Stage());
        });
        gridPane.add(Register,2,3);


        Button Login = new Button("log");
        Login.setOnAction(event -> {
            primaryStage.close();
            new Main().start(new Stage());
        });
        Login.setPrefHeight(30);
        Login.setPrefWidth(100);
        gridPane.add(Login,1,3);

        ArrayList<Ticket> tickets = new ArrayList<>();

        Empty empty = new Empty();
        empty.setName("show");
        empty.password = Main.password;
        empty.login = Main.login;
        System.out.println(empty.login);
        Main.sender.Send(empty);
        Responce p = Main.sender.Recieve();
        if(p.responces.get(0).getClass() != String.class){
            for (Object o : p.responces){
                tickets.add((Ticket)o);
            }
        }

        ObservableList<Ticket> l = FXCollections.observableArrayList(tickets);
        TableView<Ticket> table = new TableView<>(l);
        table.setPrefWidth(gridPane.getPrefWidth());

        TableColumn<Ticket, String> id = new TableColumn<>("id");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        table.getColumns().add(id);

        TableColumn<Ticket, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        table.getColumns().add(nameColumn);

        TableColumn<Ticket, String> creationDate = new TableColumn<>("creationDate");
        creationDate.setCellValueFactory(new PropertyValueFactory<>("creationDate"));
        table.getColumns().add(creationDate);


        table.setPrefWidth(primaryStage.getWidth());
        gridPane.add(table,1,1);


        Canvas canvas = new Canvas();
        canvas.setWidth(600);
        canvas.setHeight(400);
        GraphicsContext context = canvas.getGraphicsContext2D();

        context.beginPath();
        context.rect(0, 0, canvas.getWidth(), canvas.getHeight());
        context.moveTo(20, canvas.getHeight() / 2);
        context.lineTo(canvas.getWidth() - 20, canvas.getHeight() /2 );
        context.moveTo(canvas.getWidth() / 2, 20);
        context.lineTo(canvas.getWidth() / 2, canvas.getHeight() - 20);
        context.setStroke(Color.RED);

        for (Ticket ticket : tickets){
            context.moveTo(canvas.getWidth() / 2, canvas.getHeight() / 2 - (ticket.getId() * 10));
            context.lineTo(canvas.getWidth() / 2 + (ticket.getPrice() * 30), canvas.getHeight() / 2 );

            context.moveTo(canvas.getWidth() / 2 + (ticket.getPrice() * 30), canvas.getHeight() / 2 );
            context.lineTo(canvas.getWidth() / 2, canvas.getHeight() / 2 + (ticket.getCoordinates().getY() * 4));


            context.moveTo(canvas.getWidth() / 2, canvas.getHeight() / 2 + (ticket.getCoordinates().getX() * 4));
            context.lineTo(canvas.getWidth() / 2 - (ticket.getDiscount() * 100), canvas.getHeight() / 2);

            context.moveTo(canvas.getWidth() / 2, canvas.getHeight() / 2 - (ticket.getId() * 10));
            context.lineTo(canvas.getWidth() / 2 - (ticket.getDiscount() * 100), canvas.getHeight() / 2);

        }

        context.stroke();

        gridPane.add(canvas, 2, 1);

        Button comm = new Button("commands");
        comm.setOnAction(event -> {
            try {
                new Comms().start(new Stage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        comm.setPrefWidth(100);
        comm.setPrefHeight(30);

        gridPane.add(comm, 1,2);

        primaryStage.setScene(new Scene(gridPane));
        primaryStage.show();
    }
}
