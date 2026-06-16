package commands;

import conditions.Condition;
import models.Row;
import models.Table;
import java.util.List;

public class DeleteCommand implements DatabaseCommand {
    private Table table;
    private Condition condition;

    public DeleteCommand(Table table, Condition condition) {
        this.table = table;
        this.condition = condition;
    }

    @Override
    public List<Row> execute() {

        List<Row> rowsToDelete = table.select(condition);


        table.delete(condition);


        return rowsToDelete;
    }
}