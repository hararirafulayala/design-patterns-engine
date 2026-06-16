package commands;

import conditions.Condition;
import models.Row;
import models.Table;
import java.util.List;

public class UpdateCommand implements DatabaseCommand {
    private Table table;
    private String columnName;
    private Object newValue;
    private Condition condition;

    public UpdateCommand(Table table, String columnName, Object newValue, Condition condition) {
        this.table = table;
        this.columnName = columnName;
        this.newValue = newValue;
        this.condition = condition;
    }

    @Override
    public List<Row> execute() {

        List<Row> affectedRows = table.select(condition);


        table.update(condition, columnName, newValue);


        return affectedRows;
    }
}