package database.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.sql.Date;

@DatabaseTable(tableName = "trips")
public class Trip implements BaseModel {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(canBeNull = false)
    private String from;

    @DatabaseField(canBeNull = false)
    private String destination;

    @DatabaseField(canBeNull = false)
    private Date departureDate;

    @DatabaseField(canBeNull = false)
    private Date returnDate;

    @DatabaseField(canBeNull = false)
    private String vehicle;

    @DatabaseField(canBeNull = false)
    private String driver;

    @DatabaseField(canBeNull = false)
    private int passengersAmount;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public int getPassengersAmount() {
        return passengersAmount;
    }

    public void setPassengersAmount(int passengersAmount) {
        this.passengersAmount = passengersAmount;
    }

    public boolean isValid() {
        if (this.getFrom().length() < 1) {
            return false;
        }
        if (this.getDestination().length() < 1) {
            return false;
        }
        return true;
    }
}
