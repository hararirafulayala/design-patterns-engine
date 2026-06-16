package iterators;

import models.Row;
import java.util.List;

public class InsertionOrderIterator implements RowIterator {
    private List<Row> rows;
    private int position = 0;

    public InsertionOrderIterator(List<Row> rows) {
        this.rows = rows;
    }

    @Override
    public boolean hasNext() {
        return position < rows.size();
    }

    @Override
    public Row next() {
        return rows.get(position++);
    }
}