package database.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.List;

@DatabaseTable(tableName = "drivers")
public class Driver implements BaseModel {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(canBeNull = false)
    private String firstName;

    @DatabaseField(canBeNull = false)
    private String lastName;

    @DatabaseField(canBeNull = false)
    private String driveLicenses;

    @DatabaseField()
    private String additionalPermissions;

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

    public String getDriveLicenses() {
        return driveLicenses;
    }

    public void setDriveLicenses(String driveLicenses) {
        this.driveLicenses = driveLicenses;
    }

    public String getAdditionalPermissions() {
        return additionalPermissions;
    }

    public void setAdditionalPermissions(String additionalPermissions) {
        this.additionalPermissions = additionalPermissions;
    }
}
