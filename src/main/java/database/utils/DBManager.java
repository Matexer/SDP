package database.utils;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import database.models.*;

import java.io.IOException;
import java.sql.SQLException;

public abstract class DBManager {

    private static ConnectionSource connectionSource;
    private static final Logger logger = LoggerFactory.getLogger(DBManager.class);

    public static void createConnection(String databaseUrl) {
        try {
            connectionSource = new JdbcConnectionSource(databaseUrl);
        }
        catch (SQLException exception) {
            logger.error(exception.getMessage());
        }
    }

    public static ConnectionSource getConnection() {
        return connectionSource;
    }

    public static void createTablesIfNotExist() {
        Class[] clsList = {Driver.class, Trip.class, Vehicle.class, DriveLicense.class,
            AdditionalPermission.class};
        for (Class tableClass: clsList) {
            try {
                TableUtils.createTableIfNotExists(connectionSource, tableClass);
            }
            catch (SQLException exception) {
                logger.error(exception.getMessage());
            }
        }
    }

    public static void closeConnection() {
        if (connectionSource != null) {
            try {
                connectionSource.close();
            } catch (IOException exception) {
                logger.warn(exception.getMessage());
            }
        }
    }
}
