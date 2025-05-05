import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManager manager = new StudentManager();
        

        while (true) {
            System.out.println("\n===== Student Info Manager =====");
            System.out.println("1. Add Student (Look Above for Results)");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student by ID");
            
            // JSON Added
            System.out.println("4. Save to JSON");
            System.out.println("5. Load from JSON");
            
            
            // MongoDB Added
            System.out.println("6. Save to MongoDB");
            System.out.println("7. Load from MongoDB");
            
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter Course: ");
                    String course = scanner.nextLine();

                    Student newStudent = new Student(id, name, email, course);
                    manager.addStudent(newStudent);
                    break;

                case "2":
                    manager.viewAllStudents();
                    break;

                case "3":
                    System.out.print("Enter ID to search: ");
                    String searchId = scanner.nextLine();
                    Student found = manager.searchStudentById(searchId);
                    if (found != null) {
                        System.out.println("Student found:");
                        System.out.println(found);
                    } else {
                        System.out.println("No student found with ID " + searchId);
                    }
                    break;

                case "4":
                    FileHandler.saveToFile(manager.getAllStudents());
                    break;

                case "5":
                    manager.setStudents(FileHandler.loadFromFile());
                    break;

                    
                case "6":
                    MongoHandler.saveToMongo(manager.getAllStudents());
                    break;

                case "7":
                    manager.setStudents(MongoHandler.loadFromMongo());
                    break;

                case "8":
                    System.out.println("Exiting program. Goodbye!");
                    scanner.close();
                    return;


                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
