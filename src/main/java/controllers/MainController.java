package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ResourceBundle;

public class MainController {

    @FXML
    private VehiclesListController vehiclesListController;

    @FXML
    private void initialize() {
        vehiclesListController.setMainController(this);
    }

    public void changePane(String fxmlPAth, VBox parent){
        parent.getChildren().clear();
        parent.getChildren().add(loadElement(fxmlPAth));
    }

    private Parent loadElement(String fxmlPAth) {
        ResourceBundle bundle = ResourceBundle.getBundle("bundles.labels");
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(fxmlPAth));
        loader.setResources(bundle);
        Parent element = null;
        try {
            element = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return element;
    }

}
