import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class StudentsDAO {

    // Create
    public static void insertData(Students students) throws SQLException {
        String name, address;
        int age;
        double marks;

        try (Connection connection = Connectivity.connectDB();
                PreparedStatement preparedStatement = connection.prepareStatement(Query.insertData);
                Scanner scanner = new Scanner(System.in)) {

            try {
                connection.setAutoCommit(false); // Disable auto-commit

                while (true) {

                    System.out.print("Enter name: ");
                    name = scanner.nextLine();
                    students.setName(name);

                    System.out.print("Enter age: ");
                    age = scanner.nextInt();
                    students.setAge(age);
                    scanner.nextLine(); // Consume the newline character

                    System.out.print("Enter address: ");
                    address = scanner.nextLine();
                    students.setAddress(address);

                    System.out.print("Enter marks: ");
                    marks = scanner.nextDouble();
                    students.setMarks(marks);

                    preparedStatement.setString(1, name);
                    preparedStatement.setInt(2, age);
                    preparedStatement.setString(3, address);
                    preparedStatement.setDouble(4, marks);

                    int rowsAffected = preparedStatement.executeUpdate();
                    if (rowsAffected > 0) {
                        System.out.println("Data inserted successfully..");
                    } else {
                        System.out.println("Error...");
                    }

                    System.out.print("Do you want to enter more data: (Y/N)");
                    scanner.nextLine(); // Consume the newline character
                    String choice = scanner.nextLine().toUpperCase();

                    if (choice.equals("N")) {
                        System.out.println("Okay");
                        break;
                    }
                }

                connection.commit(); // Commit changes if all insertions were successful
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                connection.rollback(); // Rollback changes in case of errors
                throw e; // Rethrow to propagate the exception
            }
        }
    }

    // Read
    public static void printData() throws SQLException {

        try (Connection connection = Connectivity.connectDB();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(Query.readData)) {

            try {
                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    int age = resultSet.getInt("age");
                    String address = resultSet.getString("address");
                    double marks = resultSet.getDouble("marks");

                    System.out
                            .println("Name:" + name + "\nAge: " + age + "\nAddress: " + address + "\nMarks: " + marks);
                    System.out.println("------------------------------------");
                }
                resultSet.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                try {
                    statement.close();
                    connection.close();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    // Update
    public static void updateData() {
        try (Connection connection = Connectivity.connectDB();
                Scanner scanner = new Scanner(System.in)) {

            try {
                System.out.print("What would you like to update (Name, Age, Address, Marks): ");
                String choice = scanner.nextLine().toLowerCase();

                if (choice.equals("name")) {
                    updateName(connection, scanner);
                } else if (choice.equals("age")) {
                    updateAge(connection, scanner);
                } else if (choice.equals("address")) {
                    updateAddress(connection, scanner);
                } else if (choice.equals("marks")) {
                    updateMarks(connection, scanner);
                } else {
                    System.out.println("Invalid choice.");
                }
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to database: " + e.getMessage());
        }
    }

    private static void updateName(Connection connection, Scanner scanner) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(Query.updateName);

        System.out.print("Id: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume the newline

        System.out.print("Name: ");
        String name = scanner.nextLine();

        preparedStatement.setString(1, name); // Set the first placeholder (name)
        preparedStatement.setInt(2, id); // Set the second placeholder (id)

        int rowsAffected = preparedStatement.executeUpdate();
        System.out.println(rowsAffected + " row was updated.");
    }

    private static void updateAge(Connection connection, Scanner scanner) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(Query.updateAge);

        System.out.print("Id: ");
        int id = scanner.nextInt();

        System.out.print("Age: ");
        int age = scanner.nextInt();

        preparedStatement.setInt(1, age); // Set the first placeholder (age)
        preparedStatement.setInt(2, id); // Set the second placeholder (id)

        int rowsAffected = preparedStatement.executeUpdate();
        System.out.println(rowsAffected + " row was updated.");
    }

    private static void updateAddress(Connection connection, Scanner scanner) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(Query.updateAddress);

        System.out.print("Id: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume the newline

        System.out.print("Address: ");
        String address = scanner.nextLine();

        preparedStatement.setString(1, address); // Set the first placeholder (address)
        preparedStatement.setInt(2, id); // Set the second placeholder (id)

        int rowsAffected = preparedStatement.executeUpdate();
        System.out.println(rowsAffected + " row was updated.");
    }

    private static void updateMarks(Connection connection, Scanner scanner) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(Query.updateMarks);

        System.out.print("Id: ");
        int id = scanner.nextInt();

        System.out.print("Marks: ");
        double marks = scanner.nextDouble();

        preparedStatement.setDouble(1, marks); // Set the first placeholder (marks)
        preparedStatement.setInt(2, id); // Set the second placeholder (id)

        int rowsAffected = preparedStatement.executeUpdate();
        System.out.println(rowsAffected + " row was updated.");
    }

    // Delete
    public static void deleteData() throws SQLException {

        try (Connection connection = Connectivity.connectDB();
                Scanner scanner = new Scanner(System.in)) {

            System.out.print("What you want to delete? (Single Row(SR), All Rows(AR), Table(T)): ");
            String choice = scanner.nextLine().toLowerCase();

            if (choice.equals("sr")) {

                System.out.print("Enter the row ID: ");
                int id = scanner.nextInt();

                // Use a PreparedStatement to prevent SQL injection
                PreparedStatement statement = connection.prepareStatement(Query.removeRow);
                statement.setInt(1, id); // Set the ID parameter

                int rowsDeleted = statement.executeUpdate();

                if (rowsDeleted > 0) {
                    System.out.println("Row deleted successfully.");
                } else {
                    System.out.println("No row found with ID " + id);
                }
            } else if (choice.equals("ar")) {

                System.out.println("WARNING: This will delete ALL rows from the table. Are you sure? (y/n)");
                String confirmation = scanner.nextLine().toLowerCase();

                if (confirmation.equals("y")) {

                    PreparedStatement statement = connection.prepareStatement(Query.deleteAllRows);
                    statement.executeUpdate();
                    System.out.println("All rows deleted successfully.");

                } else {

                    System.out.println("Deletion cancelled.");
                }
            }

        }
    }

}