package commands;

import models.Row;
import java.util.ArrayList;
import java.util.List;

public class CommandInvoker {

    private List<DatabaseCommand> history = new ArrayList<>();


    public List<Row> executeCommand(DatabaseCommand command) {

        List<Row> result = command.execute();


        history.add(command);


        return result;
    }

    public List<DatabaseCommand> getHistory() {
        return history;
    }
}