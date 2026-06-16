package conditions;

import models.Row;

public interface Condition {
    // הפונקציה מקבלת שורה ובודקת האם היא עומדת בתנאי
    boolean evaluate(Row row);
}