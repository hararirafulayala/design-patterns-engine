package models;

import java.util.ArrayList; // מייבאים כלי שמאפשר ליצור רשימה
import java.util.List;      // מייבאים הגדרה כללית של רשימה

public class Schema {

    private List<Column> columns;


    public Schema() {
        this.columns = new ArrayList<>();
    }


    public void addColumn(Column column) {
        this.columns.add(column);
    }


    public List<Column> getColumns() {
        return columns;
    }
}