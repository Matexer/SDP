import database.utils.DBManager;
import org.junit.jupiter.api.Test;

public class TestDatabase {

    @Test
    public void connection() {
        DBManager.createConnection("jdbc:h2:./test_database");
    }

    @Test
    public void creatingTables() {
        DBManager.createTablesIfNotExist();
    }
}
