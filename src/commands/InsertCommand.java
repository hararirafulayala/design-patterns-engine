package commands;

import models.Row;
import models.Table;
import java.util.ArrayList;
import java.util.List;

public class InsertCommand implements DatabaseCommand {
    private Table table;
    private Row row;

    public InsertCommand(Table table, Row row) {
        this.table = table;
        this.row = row;
    }

    @Override
    public List<Row> execute() {


        table.addRow(row);


        List<Row> result = new ArrayList<>();
        result.add(row);

        return result;
    }
}