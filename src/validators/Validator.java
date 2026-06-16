package validators;

import models.Column;
import models.Row;
import models.Schema;

public class Validator {
    // אנחנו משתמשים בממשק האסטרטגיה (Strategy Pattern)
    private ValidationStrategy strategy = new TypeValidationStrategy();

    public boolean validate(Row row, Schema schema) {

        for (Column column : schema.getColumns()) {
            Object value = row.getValue(column.getName());


            if (!strategy.isValid(value, column)) {
                System.out.println("Validation failed for column: " + column.getName());
                return false;
            }
        }
        return true;
    }
}