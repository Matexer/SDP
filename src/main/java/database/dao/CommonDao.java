package database.dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;

import database.utils.DBManager;
import database.models.BaseModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Klasa nadrzędna dla wszystkich klas Dao. odpowiada za tworzenie zapytań do bazy danych.
 */
public abstract class CommonDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonDao.class);
    protected final ConnectionSource connectionSource;

    public CommonDao() {
        this.connectionSource = DBManager.getConnection();
    }

    /**
     * Metoda tworząca lub aktualizująca w wypadku istnienia rekord w bazie danych.
     * @param baseModel - obiekt bazy danych.
     * @param <T>
     * @param <I>
     * @throws Exception
     */
    public <T extends BaseModel, I> void createOrUpdate(BaseModel baseModel) throws Exception {
        Dao<T, I> dao = getDao((Class<T>) baseModel.getClass());
        try {
            dao.createOrUpdate((T) baseModel);
        } catch (SQLException e) {
            LOGGER.warn(e.getCause().getMessage());
        } finally {
            this.closeDbConnection();
        }
    }

    public <T extends BaseModel, I> void refresh(BaseModel baseModel) throws Exception {
        try {
            Dao<T, I> dao = getDao((Class<T>) baseModel.getClass());
            dao.refresh((T) baseModel);
        } catch (SQLException e) {
            LOGGER.warn(e.getCause().getMessage());
        } finally {
            this.closeDbConnection();
        }
    }

    /**
     * Metoda usuwająca rekord z bazy danych.
     * @param baseModel - obiekt bazy danych.
     * @param <T>
     * @param <I>
     * @throws Exception
     */
    public <T extends BaseModel, I> void delete(BaseModel baseModel) throws Exception {
        try {
            Dao<T, I> dao = getDao((Class<T>) baseModel.getClass());
            dao.delete((T) baseModel);
        } catch (SQLException e) {
            LOGGER.warn(e.getCause().getMessage());
        } finally {
            this.closeDbConnection();
        }
    }

    public <T extends BaseModel, I> void deleteById(Class<T> cls, Integer id) throws Exception {
        try {
            Dao<T, I> dao = getDao(cls);
            dao.deleteById((I) id);
        } catch (SQLException e) {
            LOGGER.warn(e.getCause().getMessage());
        } finally {
            this.closeDbConnection();
        }
    }

    public <T extends BaseModel, I> T findById(Class<T> cls, Integer id) throws Exception {
        try {
            Dao<T, I> dao = getDao(cls);
            return dao.queryForId((I) id);
        } catch (SQLException e) {
            LOGGER.warn(e.getCause().getMessage());
            throw new Exception();
        } finally {
            this.closeDbConnection();
        }
    }

    /**
     * Metoda zwracająca całą zawartość tablicy.
     * @param cls - klasa obiektu tworzącego tablicę
     * @throws Exception
     */
    public <T extends BaseModel, I> List<T> queryForAll(Class<T> cls) throws Exception {
        try {
            Dao<T, I> dao = getDao(cls);
            return dao.queryForAll();
        } catch (SQLException e) {
            LOGGER.warn(e.getCause().getMessage());
            throw new Exception();
        } finally {
            this.closeDbConnection();
        }
    }


    public <T extends BaseModel, I> Dao<T, I> getDao(Class<T> cls) throws Exception {
        try {
            return DaoManager.createDao(connectionSource, cls);
        } catch (SQLException e) {
            LOGGER.warn(e.getCause().getMessage());
            throw new Exception();
        } finally {
            this.closeDbConnection();
        }
    }

    public <T extends BaseModel, I> QueryBuilder<T, I> getQueryBuilder(Class<T> cls) throws Exception {
        Dao<T, I> dao = getDao(cls);
        return dao.queryBuilder();
    }

    private void closeDbConnection() throws Exception {
        try {
            this.connectionSource.close();
        } catch (IOException e) {
            throw new Exception();
        }
    }
}