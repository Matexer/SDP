package controllers;

import database.dao.DriverDao;
import database.dao.TripDao;
import database.dao.VehicleDao;
import database.models.Driver;
import database.models.Trip;
import database.models.Vehicle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Kontroler odpowiedzialny za obsługę interakcji użytkownika z oknem dodawania nowego przejazdu.
 */
public class AddTripController extends Controller {

    private final List<Vehicle> vehicles = new ArrayList<>();
    private final List<Driver> drivers = new ArrayList<>();

    private final ObservableList<String> availableVehiclesNames = FXCollections.observableArrayList();
    private final ObservableList<String> availableDriversNames = FXCollections.observableArrayList();

    @FXML
    private TextField fromField;

    @FXML
    private DatePicker departureDate;

    @FXML
    private TextField passAmountField;

    @FXML
    private ComboBox<String> vehicleCbox;

    @FXML
    private ComboBox<String> driverCbox;

    @FXML
    private DatePicker returnDate;

    @FXML
    private TextField toField;

    @FXML
    private Button cancelBtn;

    @FXML
    private Button saveBtn;

    @FXML
    void cancel(ActionEvent event) {

    }

    /**
     * Metoda wykonywana automatycznie zaraz po konstruktorze. Służy inicjalizacji danych.
     */
    @FXML
    public void initialize() {
        super.initialize();
        passAmountField.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (!isNowFocused) {
                loadAvailableVehicles();
            }
        });

        vehicleCbox.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (!isNowFocused) {
                loadAvailableDrivers();
            }
        });

    }

    /**
     * Metoda odpowiadająca za zapis wprowadzonych danych w bazie.
     *
     * @param event - obiekt klasy ActionEvent zawierający informacje o źródle wywołania metody.
     */
    @FXML
    void save(ActionEvent event) {
        Trip trip = new Trip();
        trip.setFrom(fromField.getText());
        trip.setDepartureDate(Date.valueOf(departureDate.getValue()));
        trip.setPassengersAmount(Integer.parseInt(passAmountField.getCharacters().toString()));
        trip.setVehicle(vehicleCbox.getValue());
        trip.setDriver(driverCbox.getValue());
        trip.setDestination(toField.getText());
        trip.setReturnDate(Date.valueOf(returnDate.getValue()));

        TripDao tripDao = new TripDao();
        try {
            tripDao.createOrUpdate(trip);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda odpowiedzialna za załadowanie listy wszystkich dostępnych dla przejazdu pojazdów.
     */
    private void loadAvailableVehicles() {
        VehicleDao vehicleDao = new VehicleDao();
        vehicles.clear();
        List<Vehicle> allVehicles = null;
        try {
            allVehicles = vehicleDao.queryForAll(Vehicle.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String passengers = passAmountField.getCharacters().toString();

        int passengersNum;
        try {
            passengersNum = Integer.parseInt(passengers);
        } catch (Exception e) {
            passengersNum = -1;
        }

        if (allVehicles == null) {
            return;
        }

        for (Vehicle vehicle : allVehicles) {
            addVehicleIfPossible(vehicle, passengersNum);
        }

        availableVehiclesNames.clear();
        for (Vehicle vehicle : vehicles) {
            availableVehiclesNames.add(vehicle.getName() + " " + vehicle.getRegistrationNumber());
        }

        vehicleCbox.setItems(availableVehiclesNames);

    }

    /**
     * Metoda odpowiedzialna za dodanie do listy pojazdu jesli spełnia określone kryteria.
     *
     * @param vehicle       - sprawdzany pojazd
     * @param passengersNum - wymagana minimalna liczba pasażerów
     */
    private void addVehicleIfPossible(Vehicle vehicle, int passengersNum) {
        if (passengersNum > -1) {
            if (!(vehicle.getPassengersCapacity() >= passengersNum)) {
                return;
            }
        }
        vehicles.add(vehicle);
    }

    /**
     * Metoda odpowiedzialna za załadowanie listy wszystkich dostępnych dla przejazdu kierowców.
     */
    private void loadAvailableDrivers() {
        DriverDao driverDao = new DriverDao();
        drivers.clear();
        List<Driver> allDrivers = null;
        try {
            allDrivers = driverDao.queryForAll(Driver.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (allDrivers == null) {
            return;
        }

        String vehicleName = vehicleCbox.getValue();
        int i = availableVehiclesNames.indexOf(vehicleName);
        Vehicle vehicle = vehicles.get(i);
        String requiredLicense = vehicle.getRequiredDriveLicense();

        for (Driver driver : allDrivers) {
            addDriverIfPossible(driver, requiredLicense);
        }

        availableDriversNames.clear();
        for (Driver driver : drivers) {
            availableDriversNames.add(driver.getFirstName() + " " + driver.getLastName());
        }

        driverCbox.setItems(availableDriversNames);

    }

    /**
     * Metoda odpowiedzialna za dodanie do listy kierowcy jeśli spełnia określone kryteria.
     *
     * @param driver          - sprawdzany kierowca
     * @param requiredLicense - wymagana kategoria prawa jazdy
     */
    private void addDriverIfPossible(Driver driver, String requiredLicense) {
        if (!(driver.getDriveLicenses().contains(requiredLicense))) {
            return;
        }
        drivers.add(driver);
    }
}
