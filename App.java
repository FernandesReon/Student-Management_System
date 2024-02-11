import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Students students = new Students("", 0, "", 0);

        System.out.println("Welcome to Student Management System.");
        System.out.print("Select Operation (insert, read, update, delete): ");
        
        try (Scanner scanner = new Scanner(System.in)) {
            try{
                String choice = scanner.nextLine().toLowerCase();
                switch (choice) {
                    case "insert":
                        StudentsDAO.insertData(students);
                        break;
                    case "read":
                        StudentsDAO.printData();
                        break;
                    case "update":
                        StudentsDAO.updateData();
                        break;
                    default:
                        StudentsDAO.deleteData();
                        break;
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}