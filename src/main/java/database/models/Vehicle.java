package database.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.sql.Date;

@DatabaseTable(tableName = "vehicles")
public class Vehicle implements BaseModel {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(canBeNull = false)
    private String name;

    @DatabaseField(canBeNull = false)
    private String vehicleType;

    @DatabaseField(canBeNull = false)
    private String registrationNumber;

    @DatabaseField(canBeNull = false)
    private String requiredDriveLicense;

    @DatabaseField(canBeNull = false)
    private int passengersCapacity;

    @DatabaseField(canBeNull = false)
    private Date insuranceDate;

    @DatabaseField(canBeNull = false)
    private Date technicalReviewDate;

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getRequiredDriveLicense() {
        return requiredDriveLicense;
    }

    public void setRequiredDriveLicense(String requiredDriveLicense) {
        this.requiredDriveLicense = requiredDriveLicense;
    }

    public int getPassengersCapacity() {
        return passengersCapacity;
    }

    public void setPassengersCapacity(int passengersCapacity) {
        this.passengersCapacity = passengersCapacity;
    }

    public Date getInsuranceDate() {
        return insuranceDate;
    }

    public void setInsuranceDate(Date insuranceDate) {
        this.insuranceDate = insuranceDate;
    }

    public Date getTechnicalReviewDate() {
        return technicalReviewDate;
    }

    public void setTechnicalReviewDate(Date technicalReviewDate) {
        this.technicalReviewDate = technicalReviewDate;
    }
}
