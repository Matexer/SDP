package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;


public class VehiclesListController extends Controller{

    @FXML
    private VBox vehiclesListVBox;

    @FXML
    private TextField nameField;

    @FXML
    private ComboBox<?> typeCbox;

    @FXML
    private ComboBox<?> licenseCbox;

    @FXML
    private TextField passAmountField;

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
        mainController.changePane(addVehicleFXMLPath, vehiclesListVBox);
    }

    @FXML
    void edit(ActionEvent event) {

    }

    @FXML
    void remove(ActionEvent event) {

    }

}
