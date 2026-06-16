package conditions;

import models.Row;

public class GreaterThanCondition implements Condition {
    private String columnName;
    private int minValue;

    public GreaterThanCondition(String columnName, int minValue) {
        this.columnName = columnName;
        this.minValue = minValue;
    }

    @Override
    public boolean evaluate(Row row) {
        Object value = row.getValue(columnName);
        if (value instanceof Integer) {
            return (int) value > minValue;
        }
        return false;
    }
}