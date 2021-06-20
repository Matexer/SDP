package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

public class Controller {

    public MainController mainController;

    public ObservableList<String> licenseTypes = FXCollections.observableArrayList();;

    public ObservableList<String> vehicleTypes = FXCollections.observableArrayList();;

    public ResourceBundle bundle;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void showAddVehicleWindow() {
        showWindow("/fxmls/AddVehicle.fxml", "Dodawanie nowego pojazdu");
    }

    public void showAddDriverWindow() {
        showWindow("/fxmls/AddDriver.fxml", "Dodawanie nowego kierowcy");
    }

    public void showAddTripWindow() {
        showWindow("/fxmls/AddTrip.fxml", "Dodawanie nowego przejazdu");
    }

    private void showWindow(String fxmlPath, String title) {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(fxmlPath));
        loader.setResources(ResourceBundle.getBundle("bundles.labels"));
        Parent window = null;
        try {
            window = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(window);
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    public void initialize() {
        vehicleTypes.addAll("osobowy", "bus");
        licenseTypes.addAll("A", "B", "C", "D", "E");
        bundle = ResourceBundle.getBundle("bundles.labels");
    }

}
