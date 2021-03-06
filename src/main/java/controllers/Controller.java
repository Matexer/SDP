package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

/**
 * Klasa nadrzędna klas typu Controller. Odpowiadają one za obsługę interakcji użytkownika z GUI.
 */
public class Controller {

    public MainController mainController;

    public ObservableList<String> licenseTypes = FXCollections.observableArrayList();

    public ObservableList<String> vehicleTypes = FXCollections.observableArrayList();

    public ResourceBundle bundle;

    /**
     * Metoda służaca do zapisania referencji do siebie w innych klasach typu Controller.
     *
     * @param mainController
     */
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    /**
     * Metoda odpowiadająca za wyświetlenie okna dodawania nowego pojazdu.
     */
    public void showAddVehicleWindow() {
        showWindow("/fxmls/AddVehicle.fxml", "Dodawanie nowego pojazdu");
    }

    /**
     * Metoda odpowiadająca za wyświetlenie okna dodawania nowego kierowcy.
     */
    public void showAddDriverWindow() {
        showWindow("/fxmls/AddDriver.fxml", "Dodawanie nowego kierowcy");
    }

    /**
     * Metoda odpowiadająca za wyświetlenie okna dodawania nowego przejazdu.
     */
    public void showAddTripWindow() {
        showWindow("/fxmls/AddTrip.fxml", "Dodawanie nowego przejazdu");
    }

    /**
     * Metoda odpowiadająca za wyświetlenie okna wyhenerowanego na podstawie ścieżki do pliku FXML.
     *
     * @param fxmlPath - ściezka do pliku FXML
     * @param title    - tytuł wyświetlany w oknie
     */
    private void showWindow(String fxmlPath, String title) {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(fxmlPath));
        loader.setResources(ResourceBundle.getBundle("bundles.labels"));
        Parent window = null;
        try {
            window = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert window != null;
        Scene scene = new Scene(window);
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Metoda wykonywana automatycznie zaraz po konstruktorze. Służy inicjalizacji danych.
     */
    public void initialize() {
        vehicleTypes.addAll("osobowy", "bus", "inżynieryjny", "ciężarowy");
        licenseTypes.addAll("A", "A1","B", "B1", "C", "C1", "D", "D1", "E", "E1");
        bundle = ResourceBundle.getBundle("bundles.labels");
    }

    public void showSuccessfulSaveToDBAlert() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Dodano rekord do bazy danych.");
        alert.showAndWait();
    }

    public void showErrorSaveToDBAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Nie udało się zapisać rekordu w bazie danych.");
        alert.showAndWait();
    }

}
