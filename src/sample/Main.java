package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main extends Application {

    Integer contactCount = 0;


    @Override
    public void start(Stage primaryStage) throws Exception{
        VBox parent = new VBox();
        parent.setAlignment(Pos.CENTER);

        connectDB();

        Label congradulations = new Label(contactCount + " Contacts have been added to the database.");
        parent.getChildren().addAll(congradulations);

        primaryStage.setTitle("CSVConsume ");
        primaryStage.setScene(new Scene(parent, 400, 275));
        primaryStage.show();
    }

    public void connectDB() {
        ConnectSQLite connectSQLite = new ConnectSQLite();
        try {
            ResultSet resultSet = connectSQLite.createDatabase();

            while (resultSet.next()) {
                contactCount++;
            }

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}
