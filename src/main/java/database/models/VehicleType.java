package database.models;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "vehicleTypes")
public class VehicleType implements BaseModel{

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(canBeNull = false)
    private String type;

    @ForeignCollectionField
    ForeignCollection<VehicleType> includedTypes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ForeignCollection<VehicleType> getIncludedTypes() {
        return includedTypes;
    }

    public void setIncludedTypes(ForeignCollection<VehicleType> includedTypes) {
        this.includedTypes = includedTypes;
    }
}
