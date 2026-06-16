package iterators;

import models.Row;
import conditions.Condition; // שימוש בלוגיקה שכבר קיימת ב-conditions
import java.util.List;

public class FilteredIterator implements RowIterator {
    private List<Row> rows;
    private Condition condition;
    private int position = 0;

    public FilteredIterator(List<Row> rows, Condition condition) {
        this.rows = rows;
        this.condition = condition;
    }

    @Override
    public boolean hasNext() {
        while (position < rows.size()) {
            if (condition.evaluate(rows.get(position))) {
                return true;
            }
            position++;
        }
        return false;
    }

    @Override
    public Row next() {
        return rows.get(position++);
    }
}