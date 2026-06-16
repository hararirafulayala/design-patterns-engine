package conditions;

import models.Row;

public class LessThanCondition implements Condition {
    private String columnName;
    private int maxValue;

    public LessThanCondition(String columnName, int maxValue) {
        this.columnName = columnName;
        this.maxValue = maxValue;
    }

    @Override
    public boolean evaluate(Row row) {
        Object value = row.getValue(columnName);
        if (value instanceof Integer) {
            return (int) value < maxValue;
        }
        return false;
    }
}