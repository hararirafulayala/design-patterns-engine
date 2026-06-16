package commands;

import models.Row;
import java.util.List;

public interface DatabaseCommand {

    List<Row> execute();
}