package validators;

import models.Column;

public interface ValidationStrategy {

    boolean isValid(Object value, Column column);
}