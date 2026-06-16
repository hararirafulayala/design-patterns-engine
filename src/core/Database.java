package core;

import models.Table;
import models.Row;
import conditions.Condition;
import commands.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Database {
    private static Database instance;
    private String name;
    private Map<String, Table> tables;

    private CommandInvoker invoker = new CommandInvoker();

    private Database() {
        this.name = "MyMainDatabase";
        this.tables = new HashMap<>();
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public List<Row> query(String tableName, Condition condition) {
        Table table = tables.get(tableName);
        if (table == null) return null;
        return table.select(condition);
    }


    public List<Row> insert(String tableName, Row row) {
        Table table = tables.get(tableName);
        if (table != null) {

            return invoker.executeCommand(new InsertCommand(table, row));
        }
        return null;
    }



    public List<Row> createTable(Table table) {
        if (table != null) {
            tables.put(table.getName(), table);
            System.out.println("[LOG]: Table created: " + table.getName()); // חלק 6
        }
        return new ArrayList<>();
    }

    public List<Row> removeTable(String tableName) {
        Table removed = tables.remove(tableName);
        if (removed != null) {
            System.out.println("[LOG]: Table removed: " + tableName);
        }
        return new ArrayList<>();
    }

    public Table getTable(String tableName) { return tables.get(tableName); }
    public String getName() { return name; }
}