package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;

public class DriversListController extends Controller{

    @FXML
    private Label firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private ComboBox<?> licenseCbox;

    @FXML
    private TextField addsPermissionsField;

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
        showAddDriverWindow();
    }

    @FXML
    void edit(ActionEvent event) {

    }

    @FXML
    void remove(ActionEvent event) {

    }

}
