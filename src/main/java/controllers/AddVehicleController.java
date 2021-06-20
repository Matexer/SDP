package controllers;

import database.dao.VehicleDao;
import database.models.Vehicle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.sql.Date;

public class AddVehicleController extends Controller{

    @FXML
    private ImageView picture;

    @FXML
    private Button setPictureBtn;

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

    @FXML
    void cancel(ActionEvent event) {

    }

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

    @FXML
    void setPicture(ActionEvent event) {

    }

    public void initialize() {
        super.initialize();
        typeCbox.setItems(vehicleTypes);
        licenseCbox.setItems(licenseTypes);
    }

}