package controllers;

import database.dao.DriverDao;
import database.dao.VehicleDao;
import database.models.Driver;
import database.models.Vehicle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class TripsListController extends Controller{

    List<Vehicle> allVehicles = new ArrayList<>();
    List<Driver> allDrivers = new ArrayList<>();

    @FXML
    private TextField fromField;

    @FXML
    private TextField toField;

    @FXML
    private DatePicker departureDate;

    @FXML
    private DatePicker returnDate;

    @FXML
    private ComboBox<String> driverCbox;

    @FXML
    private ComboBox<String> vehicleCbox;

    @FXML
    private TextField departureTime;

    @FXML
    private TextField returnTime;

    @FXML
    private ScrollPane listPane;

    @FXML
    private Button addBtn;

    @FXML
    private Button editBtn;

    @FXML
    private Button removeBtn;

    public void initialize() {
        allDrivers = loadAllDrivers();
        allVehicles = loadAllVehicles();
        setCboxes();
    }

    @FXML
    void search(ActionEvent event){
    }

    @FXML
    void add(ActionEvent event) {
        showAddTripWindow();
    }

    @FXML
    void edit(ActionEvent event) {

    }

    @FXML
    void remove(ActionEvent event) {

    }

    private void setCboxes() {
        ObservableList<String> vehicleNames = FXCollections.observableArrayList();
        for (Vehicle vehicle: allVehicles) {
            vehicleNames.add(vehicle.getName() + " " + vehicle.getRegistrationNumber());
        }
        vehicleCbox.setItems(vehicleNames);

        ObservableList<String> driverNames = FXCollections.observableArrayList();
        for (Driver driver: allDrivers) {
            driverNames.add(driver.getFirstName() + " " + driver.getLastName());
        }
        driverCbox.setItems(driverNames);
    }


    private List<Vehicle> loadAllVehicles() {
        VehicleDao vehicleDao = new VehicleDao();
        try {
            return vehicleDao.queryForAll(Vehicle.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<Driver> loadAllDrivers() {
        DriverDao driverDao = new DriverDao();
        try {
            return driverDao.queryForAll(Driver.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<Driver> loadFilteredDrivers() {
        return null;
    }

}
