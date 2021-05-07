package database.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.sql.Timestamp;

@DatabaseTable(tableName = "trips")
public class Trip implements BaseModel {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(canBeNull = false)
    private String from;

    @DatabaseField(canBeNull = false)
    private String destination;

    @DatabaseField(canBeNull = false)
    private Timestamp startTime;

    @DatabaseField(canBeNull = false)
    private Timestamp returnTime;

    @DatabaseField(canBeNull = false, foreign = true)
    private Vehicle vehicle;

    @DatabaseField(canBeNull = false, foreign = true)
    private Driver driver;

    @DatabaseField(canBeNull = false)
    private int passengersAmount;
}
