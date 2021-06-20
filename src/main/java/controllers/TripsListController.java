package controllers;

import database.dao.DriverDao;
import database.dao.TripDao;
import database.dao.VehicleDao;
import database.models.Driver;
import database.models.Trip;
import database.models.Vehicle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TripsListController extends Controller{

    List<Vehicle> allVehicles = new ArrayList<>();
    List<Driver> allDrivers = new ArrayList<>();

    @FXML
    private TextField fromField;

    @FXML
    private TextField toField;

    @FXML
    private DatePicker departureDate;

    @FXML
    private DatePicker returnDate;

    @FXML
    private ComboBox<String> driverCbox;

    @FXML
    private ComboBox<String> vehicleCbox;

    @FXML
    private VBox tripsContainer;

    @FXML
    private ScrollPane listPane;

    @FXML
    private Button addBtn;

    @FXML
    private Button editBtn;

    @FXML
    private Button removeBtn;

    public void initialize() {
        allDrivers = loadAllDrivers();
        allVehicles = loadAllVehicles();
        setCboxes();
    }

    @FXML
    void search(ActionEvent event){
        List<Trip> filteredTrips = loadFilteredTrips();
        if (filteredTrips == null) {
            return;
        }

        showOnList(filteredTrips);
    }

    @FXML
    void add(ActionEvent event) {
        showAddTripWindow();
    }

    @FXML
    void edit(ActionEvent event) {

    }

    @FXML
    void remove(ActionEvent event) {

    }

    private void setCboxes() {
        ObservableList<String> vehicleNames = FXCollections.observableArrayList();
        for (Vehicle vehicle: allVehicles) {
            vehicleNames.add(vehicle.getName() + " " + vehicle.getRegistrationNumber());
        }
        vehicleCbox.setItems(vehicleNames);

        ObservableList<String> driverNames = FXCollections.observableArrayList();
        for (Driver driver: allDrivers) {
            driverNames.add(driver.getFirstName() + " " + driver.getLastName());
        }
        driverCbox.setItems(driverNames);
    }


    private List<Vehicle> loadAllVehicles() {
        VehicleDao vehicleDao = new VehicleDao();
        try {
            return vehicleDao.queryForAll(Vehicle.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<Driver> loadAllDrivers() {
        DriverDao driverDao = new DriverDao();
        try {
            return driverDao.queryForAll(Driver.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    private List<Trip> loadAllTrips() {
        TripDao tripDao = new TripDao();
        try {
            return tripDao.queryForAll(Trip.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<Trip> loadFilteredTrips() {
        List<Trip> allTrips = loadAllTrips();
        if (allTrips == null) {
            return null;
        }

        String from = fromField.getText();
        String to = toField.getText();
        String vehicleName = String.valueOf(vehicleCbox.getValue());
        String driverName = String.valueOf(driverCbox.getValue());

        Date departureDateValue;
        LocalDate date = departureDate.getValue();
        if (date != null) {
            departureDateValue = Date.valueOf(date);
        }
        else {
            departureDateValue = null;
        }

        Date returnDateValue;
        date = returnDate.getValue();
        if (date != null) {
            returnDateValue = Date.valueOf(date);
        }
        else {
            returnDateValue = null;
        }

        List<Trip> filteredTrips = new ArrayList<>();
        for (Trip trip: allTrips) {
            addIfInCategory(filteredTrips, trip, from, to, departureDateValue,
                    returnDateValue, vehicleName, driverName);
        }
        return filteredTrips;
    }

    private void addIfInCategory (List<Trip> filteredList, Trip trip,
                                  String from, String to, Date departureDate,
                                  Date returnDate, String vehicleName,
                                  String driverName
                                  ) {
        if (from.length() > 0) {
            if (!(trip.getFrom().contains(from))) {
                return;
            }
        }
        if (to.length() > 0) {
            if (!(trip.getFrom().contains(to))) {
                return;
            }
        }
        if (departureDate != null) {
            if (!(departureDate.equals(trip.getDepartureDate()))) {
                return;
            }
        }
        if (returnDate != null) {
            if (!(returnDate.equals(trip.getReturnDate()))) {
                return;
            }
        }
        if (!driverName.equals("null")) {
            if (!(trip.getDriver().equals(driverName))) {
                return;
            }
        }
        if (!vehicleName.equals("null")) {
            if (!(trip.getVehicle().equals(vehicleName))) {
                return;
            }
        }
        filteredList.add(trip);
    }

    private void showOnList(List<Trip> trips){
        tripsContainer.getChildren().clear();
        for (Trip trip: trips) {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxmls/TripListField.fxml"));
            loader.setResources(bundle);
            GridPane tripField = null;
            try {
                tripField = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            ObservableList<Node> children = tripField.getChildren();
            for (Node child : children) {
                String id = child.getId();

                if (id != null) {
                    switch (id) {
                        case "from":
                            ((Label) child).setText(trip.getFrom());
                            break;
                        case "driver":
                            ((Label) child).setText(trip.getDriver());
                            break;
                        case "vehicle":
                            ((Label) child).setText(trip.getVehicle());
                            break;
                        case "passNum":
                            ((Label) child).setText(String.valueOf(trip.getPassengersAmount()));
                            break;
                        case "to":
                            ((Label) child).setText(trip.getDestination());
                            break;
                        case "departureDate":
                            ((Label) child).setText(String.valueOf(trip.getDepartureDate()));
                            break;
                        case "returnDate":
                            ((Label) child).setText(String.valueOf(trip.getReturnDate()));
                            break;
                    }
                }
            }
            tripsContainer.getChildren().add(tripField);
        }
    }
}
