package controllers;

import javafx.fxml.FXML;

/**
 * Kontroler głównego okna programu.
 */
public class MainController {

    @FXML
    private VehiclesListController vehiclesListController;

    @FXML
    private void initialize() {
        vehiclesListController.setMainController(this);
    }

}
