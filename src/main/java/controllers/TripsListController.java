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

/**
 * Kontroler odpowiedzialny za obsługę interakcji użytkownika z oknem listy przejazdów.
 */
public class TripsListController extends Controller {

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

    /**
     * Metoda wykonywana automatycznie zaraz po konstruktorze. Służy inicjalizacji danych.
     */
    public void initialize() {
        allDrivers = loadAllDrivers();
        allVehicles = loadAllVehicles();
        setCboxes(allVehicles, allDrivers);
    }

    /**
     * Metoda odpowiedzialna za wyświetlenie przefiltrowanej listy przejazdów.
     *
     * @param event -  obiekt klasy ActionEvent zawierający informacje o źródle wywołania metody.
     */
    @FXML
    void search(ActionEvent event) {
        List<Trip> filteredTrips = loadFilteredTrips();
        if (filteredTrips == null) {
            return;
        }

        showOnList(filteredTrips);
    }

    /**
     * Metoda obsługująca naciśnięcie przycisku Dodaj. Wyświetla okno dodawania przejazdu.
     *
     * @param event obiekt klasy ActionEvent zawierający informacje o źródle wywołania metody.
     */
    @FXML
    void add(ActionEvent event) {
        showAddTripWindow();
    }

    /**
     * Metoda obsługująca naciśnięcie przycisku Edytuj.
     *
     * @param event obiekt klasy ActionEvent zawierający informacje o źródle wywołania metody.
     */
    @FXML
    void edit(ActionEvent event) {

    }

    /**
     * Metoda obsługująca naciśnięcie przycisku Usuń.
     *
     * @param event obiekt klasy ActionEvent zawierający informacje o źródle wywołania metody.
     */
    @FXML
    void remove(ActionEvent event) {

    }

    /**
     * Metoda odpowiedzialna za załadowanie treści Comboboxów.
     */
    private void setCboxes(List<Vehicle> allVehicles, List<Driver> allDrivers) {
        ObservableList<String> vehicleNames = FXCollections.observableArrayList();
        if (allVehicles != null) {
            for (Vehicle vehicle : allVehicles) {
                vehicleNames.add(vehicle.getName() + " " + vehicle.getRegistrationNumber());
            }
            vehicleCbox.setItems(vehicleNames);
        }

        if (allDrivers != null) {
            ObservableList<String> driverNames = FXCollections.observableArrayList();
            for (Driver driver : allDrivers) {
                driverNames.add(driver.getFirstName() + " " + driver.getLastName());
            }
            driverCbox.setItems(driverNames);
        }
    }


    /**
     * Metoda zwracająca wszystkie pojazdy z bazy danych.
     *
     * @return List<Vehicle> - lista pojazdów
     */
    private List<Vehicle> loadAllVehicles() {
        VehicleDao vehicleDao = new VehicleDao();
        try {
            return vehicleDao.queryForAll(Vehicle.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Metoda zwracająca wszystkich kierowców z bazy danych.
     *
     * @return List<Driver> - lista kierowców
     */
    private List<Driver> loadAllDrivers() {
        DriverDao driverDao = new DriverDao();
        try {
            return driverDao.queryForAll(Driver.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Metoda zwracająca wszystkie przejazdy z bazy danych.
     *
     * @return List<Trip> - lista przejazdów
     */
    private List<Trip> loadAllTrips() {
        TripDao tripDao = new TripDao();
        try {
            return tripDao.queryForAll(Trip.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Metoda zwracająca przefiltorwane przejazdy.
     *
     * @return List<Trip> - lista przejazdów
     */
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
        } else {
            departureDateValue = null;
        }

        Date returnDateValue;
        date = returnDate.getValue();
        if (date != null) {
            returnDateValue = Date.valueOf(date);
        } else {
            returnDateValue = null;
        }

        List<Trip> filteredTrips = new ArrayList<>();
        for (Trip trip : allTrips) {
            addIfInCategory(filteredTrips, trip, from, to, departureDateValue,
                    returnDateValue, vehicleName, driverName);
        }
        return filteredTrips;
    }

    /**
     * Metoda filtrująca przejazd. Uzupełnia listę przejazdów jeżeli przejazd spełnia określone parametry.
     *
     * @param filteredList  - lista przejazdów
     * @param trip          - sprawdzany przejazd
     * @param from          - miejsce wyjazdu
     * @param to            - miejsce docelowe
     * @param departureDate - data wyjazdu
     * @param returnDate    - data powrotu
     * @param vehicleName   - nazwa pojazdu (Nazwa własna + nr rej.)
     * @param driverName    - nazwa kierowcy (Imię i nazwisko)
     */
    private void addIfInCategory(List<Trip> filteredList, Trip trip,
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

    /**
     * Metoda odpowiadająca za pokazanie listy przefiltrowanych przejazdów.
     *
     * @param trips - lista przejazdów
     */
    private void showOnList(List<Trip> trips) {
        tripsContainer.getChildren().clear();
        for (Trip trip : trips) {
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
