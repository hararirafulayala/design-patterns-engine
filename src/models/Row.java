package models;

import java.util.HashMap;
import java.util.Map;

public class Row {

    private Map<String, Object> values;


    public Row() {
        this.values = new HashMap<>();
    }


    public void addValue(String columnName, Object value) {
        this.values.put(columnName, value);
    }


    public Object getValue(String columnName) {
        return this.values.get(columnName);
    }

    public Map<String, Object> getValues() {
        return values;
    }
}