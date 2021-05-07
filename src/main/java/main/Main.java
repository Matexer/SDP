package main;

import database.utils.DBManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;


public class Main extends Application {

    private String appTitle;
    private ResourceBundle bundle;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() {
        bundle = ResourceBundle.getBundle("bundles.labels");
        appTitle = bundle.getString("application.name") + " v" + bundle.getString("application.version");
        DBManager.createConnection("jdbc:h2:./database");
        DBManager.createTablesIfNotExist();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxmls/Main.fxml"));
        loader.setResources(bundle);
        TabPane tabPane = loader.load();
        Scene scene = new Scene(tabPane);
        primaryStage.setTitle(appTitle);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() {
        DBManager.closeConnection();
    }

}
