package validators;

import enums.DataType;
import models.Column;

public class TypeValidationStrategy implements ValidationStrategy {
    @Override
    public boolean isValid(Object value, Column column) {
        if (value == null) return false;

        DataType type = column.getDataType();
        switch (type) {
            case INT:    return value instanceof Integer;
            case STRING: return value instanceof String;
            case BOOL:   return value instanceof Boolean;
            default:     return false;
        }
    }
}