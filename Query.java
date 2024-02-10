public class Query {

    static String insertData = "INSERT INTO students (name, age, address, marks) VALUES (?,?,?,?)"; // Create data query

    static String readData = "SELECT * FROM students"; // Read data query

    // Update data queries
    static String updateName = "UPDATE students SET name = ? WHERE id = ?";

    static String updateAge = "UPDATE students SET age = ? WHERE id = ?";

    static String updateAddress = "UPDATE students SET address = ? WHERE id = ?";

    static String updateMarks = "UPDATE students SET marks = ? WHERE id = ?";

    // Delete data queries
    static String removeRow = "DELETE FROM students WHERE id = ?"; // Delete specific row.

    static String deleteAllRows = "DELETE FROM students";

    static String dropTable = "DROP TABLE students";

}
