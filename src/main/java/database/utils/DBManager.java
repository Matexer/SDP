package database.utils;

import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import database.models.*;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Klasa odpowiedzialna za komunikację z baza danych.
 */
public abstract class DBManager {

    private static ConnectionSource connectionSource;
    private static final Logger logger = LoggerFactory.getLogger(DBManager.class);

    /**
     * Utworzenie połączenia z baza danych
     * @param databaseUrl ściażka do bazy danych
     */
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

    /**
     * Metoda tworząca tabele w bazie danych w przypadku ich nieistnienia,
     * czyli podczas generowania nowej bazy danych.
     */
    public static void createTablesIfNotExist() {
        Class[] clsList = {Driver.class, Trip.class, Vehicle.class};
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
