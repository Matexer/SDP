package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class AddVehicleController {

    @FXML
    private ImageView picture;

    @FXML
    private Button setPictureBtn;

    @FXML
    private TextField nameField;

    @FXML
    private ComboBox<?> typeCbox;

    @FXML
    private ComboBox<?> licenseCbox;

    @FXML
    private TextField passCbox;

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

    }

    @FXML
    void setPicture(ActionEvent event) {

    }

}
