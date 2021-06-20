package controllers;

import database.dao.DriverDao;
import database.dao.VehicleDao;
import database.models.Driver;
import database.models.Vehicle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DriversListController extends Controller{

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

    @FXML
    void search(ActionEvent event) throws IOException {
        loadDrivers();
        loadVisibleList();
    }

    @FXML
    void add(ActionEvent event) {
        showAddDriverWindow();
    }

    @FXML
    void edit(ActionEvent event) {

    }

    @FXML
    void remove(ActionEvent event) {

    }

    public void initialize() {
        super.initialize();
        licenseCbox.setItems(licenseTypes);
    }

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

        for (Driver driver: addDrivers) {
            addIfInCategory(driver, firstName, lastName, license, addsPerm);
        }
    }

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

    public void loadVisibleList() throws IOException {
        driversContainer.getChildren().clear();
        for (Driver driver: drivers) {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxmls/DriverListField.fxml"));
            loader.setResources(bundle);
            GridPane driverField = loader.load();

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
