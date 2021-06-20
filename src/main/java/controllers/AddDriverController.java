package controllers;

import database.dao.DriverDao;
import database.models.Driver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;


public class AddDriverController extends Controller{

    @FXML
    private ImageView picture;

    @FXML
    private Button setPictureBtn;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField driveLicField;

    @FXML
    private TextField addsPerm;

    @FXML
    private Button cancelBtn;

    @FXML
    private Button saveBtn;

    @FXML
    void cancel(ActionEvent event) {

    }

    @FXML
    void save(ActionEvent event) {
        Driver driver = new Driver();
        driver.setFirstName(firstNameField.getText());
        driver.setLastName(lastNameField.getText());
        driver.setDriveLicenses(driveLicField.getText());
        driver.setAdditionalPermissions(addsPerm.getText());

        DriverDao driverDao = new DriverDao();
        try {
            driverDao.createOrUpdate(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void setPicture(ActionEvent event) {

    }

}
