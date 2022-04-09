package megamek.server;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class DBManagerTest {
    @BeforeEach
    public void initializeDB() {
        DBManager.initiateDB();
    }

    @Test
    @Order(1)
    public void testAddOrUpdateRow_IncrementValue() {
        DBManager.addOrUpdateRow("Test", 1.0);
    }

    @Test
    @Order(2)
    public void testGetFromTable_GetFirstValue() {
        Assertions.assertEquals(1.0, DBManager.getFromTable("Test"));
    }

    @Test
    @Order(1)
    public void testAddOrUpdateRow_DecrementValue() {
        DBManager.addOrUpdateRow("Test", -1.0);
    }

    @Test
    @Order(2)
    public void testGetFromTable_GetSecondValue() {
        Assertions.assertEquals(0.0, DBManager.getFromTable("Test"));
    }
}
