package database.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "drivers")
public class Driver implements BaseModel {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(canBeNull = false)
    private String firstName;

    @DatabaseField(canBeNull = false)
    private String lastName;

    @DatabaseField(canBeNull = false, foreign = true)
    private DriveLicense driveLicenses;

    @DatabaseField(foreign = true)
    private AdditionalPermission additionalPermissions;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public DriveLicense getDriveLicenses() {
        return driveLicenses;
    }

    public void setDriveLicenses(DriveLicense driveLicenses) {
        this.driveLicenses = driveLicenses;
    }

    public AdditionalPermission getAdditionalPermissions() {
        return additionalPermissions;
    }

    public void setAdditionalPermissions(AdditionalPermission additionalPermissions) {
        this.additionalPermissions = additionalPermissions;
    }

}
