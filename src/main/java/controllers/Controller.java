package controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.ResourceBundle;

public class Controller {

    public String addVehicleFXMLPath = "/fxmls/AddVehicle.fxml";

    public MainController mainController;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

}
