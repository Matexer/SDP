package database.models;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Collection;

@DatabaseTable(tableName = "driveLicenses")
public class DriveLicense implements BaseModel{

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(canBeNull = false)
    private String license;

    @ForeignCollectionField
    ForeignCollection<DriveLicense> includedLicenses;

    public Collection<DriveLicense> getIncludedLicenses() {
        return includedLicenses;
    }

    public void setIncludedLicenses(ForeignCollection<DriveLicense> includedLicenses) {
        this.includedLicenses = includedLicenses;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

}
