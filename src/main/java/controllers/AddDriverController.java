package controllers;

import database.dao.DriverDao;
import database.models.Driver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


/**
 * Kontroler odpowiedzialny za obsługę interakcji użytkownika z oknem dodawania nowego kierowcy.
 */
public class AddDriverController extends Controller{

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField driveLicField;

    @FXML
    private TextField addsPerm;

    @FXML
    private Button cancelBtn;

    @FXML
    private Button saveBtn;

    @FXML

    /**
     * Metoda wykonywana po naciśnięciu przycisku Anuluj.
     * @param event - obiekt klasy ActionEvent zawierający informacje o źródle wywołania metody.
     */
    void cancel(ActionEvent event) {

    }

    /**
     * Metoda odpowiadająca za zapis wprowadzonych danych w bazie.
     * @param event - obiekt klasy ActionEvent zawierający informacje o źródle wywołania metody.
     */
    @FXML
    void save(ActionEvent event) {
        Driver driver = new Driver();
        driver.setFirstName(firstNameField.getText());
        driver.setLastName(lastNameField.getText());
        driver.setDriveLicenses(driveLicField.getText());
        driver.setAdditionalPermissions(addsPerm.getText());

        DriverDao driverDao = new DriverDao();
        try {
            driverDao.createOrUpdate(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
