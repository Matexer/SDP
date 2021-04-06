package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.sql.*;


public class Main extends Application {

    @Override
    public void start(Stage stage) {
        var scene = new Scene(new StackPane(), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws SQLException {
        runDB();
        launch();
    }

    static void runDB() throws SQLException {
        java.sql.Connection connection = DriverManager.getConnection("jdbc:h2:./test_db", "sa", "");
    }
}
