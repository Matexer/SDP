package controllers;

import database.dao.DriverDao;
import database.models.Driver;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Kontroler odpowiedzialny za obsługę interakcji użytkownika z oknem listy kierowców.
 */
public class DriversListController extends Controller {

    List<Driver> drivers = new ArrayList<>();

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private ComboBox<String> licenseCbox;

    @FXML
    private TextField addsPermissionsField;

    @FXML
    private VBox driversContainer;

    @FXML
    private ScrollPane listPane;

    @FXML
    private Button addBtn;

    @FXML
    private Button editBtn;

    @FXML
    private Button removeBtn;

    /**
     * Metoda odpowiedzialna za wyświetlenie przefiltorwanej listy kierowców.
     *
     * @param event obiekt klasy ActionEvent zawierający informacje o źródle wywołania metody.
     */
    @FXML
    void search(ActionEvent event) {
        loadDrivers();
        loadVisibleList();
    }

    /**
     * Metoda obsługująca naciśnięcie przycisku Dodaj. Wyświetla okno dodawania kierowcy.
     *
     * @param event obiekt klasy ActionEvent zawierający informacje o źródle wywołania metody.
     */
    @FXML
    void add(ActionEvent event) {
        showAddDriverWindow();
    }

    /**
     * Metoda obsługująca naciśnięcie przycisku Edytuj.
     *
     * @param event obiekt klasy ActionEvent zawierający informacje o źródle wywołania metody.
     */
    @FXML
    void edit(ActionEvent event) {

    }

    /**
     * Metoda obsługująca naciśnięcie przycisku Usuń.
     *
     * @param event obiekt klasy ActionEvent zawierający informacje o źródle wywołania metody.
     */
    @FXML
    void remove(ActionEvent event) {

    }

    /**
     * Metoda wykonywana automatycznie zaraz po konstruktorze. Służy inicjalizacji danych.
     */
    public void initialize() {
        super.initialize();
        licenseCbox.setItems(licenseTypes);
    }

    /**
     * Metoda odpowiedzialna za załadowanie przefiltrowanej listy kierwców.
     */
    public void loadDrivers() {
        DriverDao driverDao = new DriverDao();
        drivers.clear();
        List<Driver> addDrivers = null;
        try {
            addDrivers = driverDao.queryForAll(Driver.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String firstName = firstNameField.getCharacters().toString();
        String lastName = lastNameField.getCharacters().toString();
        String license = licenseCbox.getValue();
        String addsPerm = addsPermissionsField.getCharacters().toString();

        if (addDrivers == null) {
            return;
        }

        for (Driver driver : addDrivers) {
            addIfInCategory(driver, firstName, lastName, license, addsPerm);
        }
    }

    /**
     * Metoda odpowiedzialna za dodanie do listy kierowcy jeśli spełnia określone kryteria.
     *
     * @param driver    - sprawdzany obiekt klasy Driver
     * @param firstName - imię kierowcy
     * @param lastName  - nazwisko kierowcy
     * @param license   - wymagane uprawnienia do kierowania pojazdami
     * @param addsPerm  - wymagane uprawnienia dodatkowe
     */
    private void addIfInCategory(Driver driver, String firstName,
                                 String lastName, String license, String addsPerm) {
        if (firstName.length() > 0) {
            if (!(driver.getFirstName().contains(firstName))) {
                return;
            }
        }
        if (lastName.length() > 0) {
            if (!(driver.getLastName().contains(lastName))) {
                return;
            }
        }
        if (addsPerm.length() > 0) {
            if (!(driver.getAdditionalPermissions().contains(addsPerm))) {
                return;
            }
        }
        if (license != null) {
            if (!(driver.getDriveLicenses().contains(license))) {
                return;
            }
        }
        drivers.add(driver);
    }

    /**
     * Metoda odpowiedzialna za wyświetlenie listy przefiltrowanych kierowców.
     */
    public void loadVisibleList() {
        driversContainer.getChildren().clear();
        for (Driver driver : drivers) {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxmls/DriverListField.fxml"));
            loader.setResources(bundle);
            GridPane driverField = null;
            try {
                driverField = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            assert driverField != null;
            ObservableList<Node> children = driverField.getChildren();

            for (Node child : children) {
                String id = child.getId();
                String label;

                if (id != null) {
                    switch (id) {
                        case "name":
                            label = driver.getFirstName() + " " + driver.getLastName();
                            ((Label) child).setText(label);
                            break;
                        case "licenses":
                            label = "Kat. " + driver.getDriveLicenses();
                            ((Label) child).setText(label);
                            break;
                        case "addsPerm":
                            label = "Dod. uprawnienia: " + driver.getAdditionalPermissions();
                            ((Label) child).setText(label);
                            break;
                    }
                }
            }
            driversContainer.getChildren().add(driverField);
        }
    }

}
