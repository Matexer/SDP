package controllers;

import database.dao.VehicleDao;
import database.models.Vehicle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class VehiclesListController extends Controller{

    List<Vehicle> vehicles = new ArrayList<>();

    @FXML
    private TextField nameField;

    @FXML
    private ComboBox<String> typeCbox;

    @FXML
    private ComboBox<String> licenseCbox;

    @FXML
    private TextField passAmountField;

    @FXML
    private VBox vehiclesContainer;

    @FXML
    private Button addBtn;

    @FXML
    private Button editBtn;

    @FXML
    private Button removeBtn;

    @FXML
    private Button searchBtn;

    @FXML
    void search(ActionEvent event) throws IOException {
        loadVehicles();
        loadVisibleList();
    }

    @FXML
    void add(ActionEvent event) {
        showAddVehicleWindow();
    }

    @FXML
    void edit(ActionEvent event) {

    }

    @FXML
    void remove(ActionEvent event) {

    }

    public void initialize() {
        super.initialize();
        typeCbox.setItems(vehicleTypes);
        licenseCbox.setItems(licenseTypes);
    }

    public void loadVehicles() {
        VehicleDao vehicleDao = new VehicleDao();
        vehicles.clear();
        List<Vehicle> allVehicles = null;
        try {
            allVehicles = vehicleDao.queryForAll(Vehicle.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String name = nameField.getCharacters().toString();
        String type = typeCbox.getValue();
        String license = licenseCbox.getValue();
        String passengers = passAmountField.getCharacters().toString();

        int passengersNum;
        try {
            passengersNum = Integer.parseInt(passengers);
        }
        catch (Exception e) {
            passengersNum = -1;
        }

        if (allVehicles == null) {
            return;
        }

        for (Vehicle vehicle: allVehicles) {
            addIfInCategory(vehicle, name, type, license, passengersNum);
        }
    }

    private void addIfInCategory(Vehicle vehicle, String name, String type,
                                 String license, int passengersNum) {
        if (name.length() > 0) {
            if (!(vehicle.getName().equals(name))) {
                return;
            }
        }
        if (type != null) {
            if (!(vehicle.getVehicleType().equals(type))) {
                return;
            }
        }
        if (license != null) {
            if (!(vehicle.getRequiredDriveLicense().equals(license))) {
                return;
            }
        }
        if (passengersNum > -1) {
            if (!(vehicle.getPassengersCapacity() >= passengersNum)) {
                return;
            }
        }
        vehicles.add(vehicle);
    }

    private void loadVisibleList() throws IOException {
        vehiclesContainer.getChildren().clear();
        for (Vehicle vehicle: vehicles) {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxmls/VehicleListField.fxml"));
            loader.setResources(bundle);
            GridPane vehicleField = loader.load();

            ObservableList<Node> children = vehicleField.getChildren();
            for (Node child : children) {
                String id = child.getId();
                String label;
                String label2;

                if (id != null) {
                    switch (id) {
                        case "vehicleName":
                            label = vehicle.getName();
                            ((Label) child).setText(label);
                            break;
                        case "passengers":
                            label2 = "L. pasażerów: " + vehicle.getPassengersCapacity();
                            ((Label) child).setText(label2);
                            break;
                        case "license":
                            label = "Kat. " + vehicle.getRequiredDriveLicense();
                            ((Label) child).setText(label);
                            break;
                        case "regNum":
                            ((Label) child).setText(vehicle.getRegistrationNumber());
                            break;
                        case "insuranceDate":
                            label = "Ubez. ważne do " + vehicle.getInsuranceDate().toString();
                            ((Label) child).setText(label);
                            break;
                        case "techRevDate":
                            label = "Przegląd ważny do " + vehicle.getTechnicalReviewDate().toString();
                            ((Label) child).setText(label);
                            break;
                    }
                }
            }
            vehiclesContainer.getChildren().add(vehicleField);
        }

        }
}
