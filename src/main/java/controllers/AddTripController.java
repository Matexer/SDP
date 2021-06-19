package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class AddTripController{

    @FXML
    private TextField fromField;

    @FXML
    private TextField departureTime;

    @FXML
    private DatePicker departureDate;

    @FXML
    private TextField passAmountField;

    @FXML
    private ComboBox<?> vehicleCbox;

    @FXML
    private ComboBox<?> driverCbox;

    @FXML
    private DatePicker returnDate;

    @FXML
    private TextField returnTime;

    @FXML
    private TextField toField;

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

}
