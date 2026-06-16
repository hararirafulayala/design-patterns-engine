import core.Database;
import models.Table;
import models.Row;
import enums.DataType;
import conditions.*;
import iterators.RowIterator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 1. Singleton - קבלת מופע יחיד של בסיס הנתונים
        Database db = Database.getInstance();
        System.out.println("=== Starting Full System Demo ===");

        // 2. Fluent API & Builder - יצירת טבלה עם עמודות מסוגים שונים
        System.out.println("\n[1] Creating table 'Employees' using Fluent API Builder...");
        Table employees = new Table.Builder("Employees")
                .addColumn("ID", DataType.INT)
                .addColumn("Name", DataType.STRING)
                .addColumn("Salary", DataType.INT)
                .addColumn("IsManager", DataType.BOOL)
                .build();
        db.createTable(employees); //   הדפסת לוג יצירה

        // 3. Client API & InsertCommand - הכנסת נתונים למערכת
        System.out.println("\n[2] Inserting data using Client API (InsertCommand)...");

        Row r1 = new Row();
        r1.addValue("ID", 101); r1.addValue("Name", "Alice"); r1.addValue("Salary", 5000); r1.addValue("IsManager", true);
        db.insert("Employees", r1);

        Row r2 = new Row();
        r2.addValue("ID", 102); r2.addValue("Name", "Bob"); r2.addValue("Salary", 3000); r2.addValue("IsManager", false);
        db.insert("Employees", r2);

        Row r3 = new Row();
        r3.addValue("ID", 103); r3.addValue("Name", "Charlie"); r3.addValue("Salary", 7000); r3.addValue("IsManager", false);
        db.insert("Employees", r3);

        // 4. Prototype Pattern - שכפול טבלה (Deep Copy)
        System.out.println("\n[3] Cloning table (Deep Copy) - changes to original won't affect clone...");
        Table backup = employees.clone();

        // 5. Conditions - שימוש בתנאים שונים (Equals, GreaterThan)
        System.out.println("\n[4] Querying data: Employees with Salary > 4000...");
        List<Row> highSalary = db.query("Employees", new GreaterThanCondition("Salary", 4000));
        highSalary.forEach(r -> System.out.println("Found: " + r.getValue("Name")));

        // 6. UpdateCommand - עדכון נתונים לפי תנאי
        System.out.println("\n[5] Updating data: Set Bob as Manager...");
        employees.update(new EqualsCondition("Name", "Bob"), "IsManager", true);

        // 7. Iterators - שימוש ב-3 אסטרטגיות איטרציה
        System.out.println("\n[6] Testing Strategy Iterators:");

        System.out.println("A. Sorted by Salary (High to Low):");
        RowIterator sortedIt = employees.getSortedIterator("Salary");
        while(sortedIt.hasNext()) System.out.println("- " + sortedIt.next().getValue("Name"));

        System.out.println("B. Filtered (Only Managers):");
        RowIterator filteredIt = employees.getFilteredIterator(new EqualsCondition("IsManager", true));
        while(filteredIt.hasNext()) System.out.println("- " + filteredIt.next().getValue("Name"));

        // 8. DeleteCommand מחיקת נתונים
        System.out.println("\n[7] Deleting employee with ID 101...");
        employees.delete(new EqualsCondition("ID", 101));

        // 9. Verification of Deep Copy - בדיקה שהגיבוי לא השתנה)
        System.out.println("\n[8] Verifying Deep Copy: Checking if Alice still exists in backup...");
        boolean aliceExistsInBackup = !backup.select(new EqualsCondition("Name", "Alice")).isEmpty();
        System.out.println("Alice in backup? " + aliceExistsInBackup + " (Should be true)");

        // 10. Table Removal - הסרת טבלה מהבסיס נתונים
        System.out.println("\n[9] Removing table from Database...");
        db.removeTable("Employees");

        System.out.println("\n=== Demo Finished Successfully ===");
    }
}