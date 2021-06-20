package main;

import database.utils.DBManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;


/**
 * Główna klasa programu. Służy do wygenerowania GUI oraz aktywacji kontrolerów.
 */
public class Main extends Application {

    private String appTitle;
    private ResourceBundle bundle;

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Metoda wykonywana automatycznie zaraz po konstruktorze. Służy inicjalizacji danych.
     */
    @Override
    public void init() {
        bundle = ResourceBundle.getBundle("bundles.labels");
        appTitle = bundle.getString("application.name") + " v" + bundle.getString("application.version");
        DBManager.createConnection("jdbc:h2:./SDK_DB");
        DBManager.createTablesIfNotExist();
    }

    /**
     * Metoda odpowiedzialna za załadownie plików FXML
     * @param primaryStage - wygenerowany przez klasę obiekt root dla elementów GUI
     * @throws IOException - wyjątek zwracany w przypadku niemożliwości załadowania pliku FXML
     */
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

    /**
     * Meotda wywoływana po zamknięciu GUI. W tym przypadku odpowiada za upenienie się o zamknięciu połączenia z bazą danych.
     */
    @Override
    public void stop() {
        DBManager.closeConnection();
    }

}
