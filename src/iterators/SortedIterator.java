package iterators;

import models.Row;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortedIterator implements RowIterator {
    private List<Row> sortedRows;
    private int position = 0;

    public SortedIterator(List<Row> rows, String columnName) {
        this.sortedRows = new ArrayList<>(rows);
        this.sortedRows.sort(Comparator.comparing(row -> {
            Object value = row.getValue(columnName);
            return (value != null) ? value.toString() : "";
        }));
    }

    @Override
    public boolean hasNext() {
        return position < sortedRows.size();
    }

    @Override
    public Row next() {
        return sortedRows.get(position++);
    }
}