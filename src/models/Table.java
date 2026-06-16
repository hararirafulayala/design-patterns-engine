package models;

import enums.DataType;
import validators.Validator;
import java.util.ArrayList;
import java.util.List;
import conditions.Condition;
import iterators.*;

public class Table implements Cloneable {
    private String name;
    private Schema schema;
    private List<Row> rows;
    private Validator validator = new Validator();

    private Table(String name) {
        this.name = name;
        this.schema = new Schema();
        this.rows = new ArrayList<>();
    }


    @Override
    public Table clone() {
        try {
            Table cloned = (Table) super.clone();
            cloned.rows = new ArrayList<>();
            for (Row r : this.rows) {
                Row clonedRow = new Row();
                r.getValues().forEach((key, value) -> clonedRow.addValue(key, value));
                cloned.rows.add(clonedRow);
            }
            return cloned;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }


    public static class Builder {
        private Table table;
        public Builder(String tableName) { this.table = new Table(tableName); }

        public Builder addColumn(String columnName, DataType type) {
            this.table.schema.addColumn(new Column(columnName, type));
            return this; //
        }

        public Table build() { return this.table; }
    }



    public void addRow(Row row) {
        if (this.validator.validate(row, this.schema)) {
            this.rows.add(row);
            System.out.println("[LOG]: New row inserted into " + name); //
        }
    }

    public void update(Condition condition, String columnName, Object newValue) {
        int affected = 0;
        for (Row row : rows) {
            if (condition.evaluate(row)) {
                row.getValues().put(columnName, newValue);
                affected++;
            }
        }
        System.out.println("[LOG]: Update in " + name + ". Rows affected: " + affected); //
    }

    public void delete(Condition condition) {
        int initialSize = rows.size();
        rows.removeIf(condition::evaluate);
        System.out.println("[LOG]: Delete in " + name + ". Rows removed: " + (initialSize - rows.size())); //
    }

    public List<Row> select(Condition condition) {
        List<Row> result = new ArrayList<>();
        for (Row row : rows) {
            if (condition.evaluate(row)) result.add(row);
        }
        return result;
    }

    public RowIterator getInsertionOrderIterator() { return new InsertionOrderIterator(this.rows); }
    public RowIterator getSortedIterator(String columnName) { return new SortedIterator(this.rows, columnName); }
    public RowIterator getFilteredIterator(Condition condition) { return new FilteredIterator(this.rows, condition); }

    public String getName() { return name; }
    public List<Row> getRows() { return rows; }
}