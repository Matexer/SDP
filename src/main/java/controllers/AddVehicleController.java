package controllers;

import database.dao.VehicleDao;
import database.models.Vehicle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.sql.Date;

/**
 * Kontroler odpowiedzialny za obsługę interakcji użytkownika z oknem dodawania nowego pojazdu.
 */
public class AddVehicleController extends Controller {

    @FXML
    private TextField nameField;

    @FXML
    private ComboBox<String> typeCbox;

    @FXML
    private ComboBox<String> licenseCbox;

    @FXML
    private TextField passNum;

    @FXML
    private DatePicker insuranceDate;

    @FXML
    private DatePicker techReviewDate;

    @FXML
    private TextField registrationNumField;

    @FXML
    private Button cancelBtn;

    @FXML
    private Button saveBtn;

    /**
     * Metoda wykonywana po naciśnięciu przycisku Anuluj.
     *
     * @param event - obiekt klasy ActionEvent zawierający informacje o źródle wywołania metody.
     */
    @FXML
    void cancel(ActionEvent event) {

    }

    /**
     * Metoda odpowiadająca za zapis wprowadzonych danych w bazie.
     *
     * @param event - obiekt klasy ActionEvent zawierający informacje o źródle wywołania metody.
     */
    @FXML
    void save(ActionEvent event) {
        Vehicle vehicle = new Vehicle();
        vehicle.setName(nameField.getCharacters().toString());
        vehicle.setVehicleType(typeCbox.getValue());
        vehicle.setRegistrationNumber(registrationNumField.getCharacters().toString());
        vehicle.setRequiredDriveLicense(licenseCbox.getValue());
        vehicle.setPassengersCapacity(Integer.parseInt((passNum.getCharacters().toString())));
        vehicle.setInsuranceDate(Date.valueOf(insuranceDate.getValue()));
        vehicle.setTechnicalReviewDate(Date.valueOf(techReviewDate.getValue()));

        VehicleDao vehicleDao = new VehicleDao();
        try {
            vehicleDao.createOrUpdate(vehicle);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda wykonywana automatycznie zaraz po konstruktorze. Służy inicjalizacji danych.
     */
    public void initialize() {
        super.initialize();
        typeCbox.setItems(vehicleTypes);
        licenseCbox.setItems(licenseTypes);
    }

}