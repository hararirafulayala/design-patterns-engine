package test; // וודאי שזה תואם לשם התיקייה שלך

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import core.Database;
import models.Table;
import models.Row;
import enums.DataType;
import java.util.List;

public class DatabaseTest {

    @Test
    public void testInsertAndQuery() {
        // 1. Singleton Database
        Database db = Database.getInstance();


        Table testTable = new Table.Builder("TestTable")
                .addColumn("ID", DataType.INT)
                .build();
        db.createTable(testTable);


        Row testRow = new Row();
        testRow.addValue("ID", 100);
        db.insert("TestTable", testRow);

        List<Row> rows = db.getTable("TestTable").getRows();
        assertEquals(1, rows.size(), "Table should have exactly 1 row");
        assertEquals(100, rows.get(0).getValue("ID"), "ID should be 100");
    }
}