package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;

public class TripsListController {

    @FXML
    private TextField fromField;

    @FXML
    private TextField toField;

    @FXML
    private DatePicker departureDate;

    @FXML
    private DatePicker returnDate;

    @FXML
    private ComboBox<?> driverCbox;

    @FXML
    private ComboBox<?> vehicleCbox;

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

    @FXML
    void add(ActionEvent event) {

    }

    @FXML
    void edit(ActionEvent event) {

    }

    @FXML
    void remove(ActionEvent event) {

    }

}
