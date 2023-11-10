import java.util.HashMap;
import java.util.Scanner;

public class StudentDirectory {

    HashMap<String, Student> Hashtable = new HashMap<>();

    public void addStudent(String studentID, String name, String major) {

       
            Student student = new Student(name, major);
            Hashtable.put("2023-" + studentID, student);

            String format = "| %-15s | %-26s | %-21s |%n";
            System.out.format("+-----------------+----------------------------+-----------------------+%n");
            System.out.format("| ID              | Student's Name             | Subject Major         |%n");
            System.out.format("+-----------------+----------------------------+-----------------------+%n");
           
            System.out.format(format, "2023-" + studentID, name, major);

            System.out.format("+-----------------+----------------------------+-----------------------+%n");

    }

    public void searchStudent(String studentID){

        if (Hashtable.containsKey("2023-" + studentID)) {
            Student student = Hashtable.get("2023-" + studentID);

            String format = "| %-15s | %-26s | %-21s |%n";
            System.out.format("+-----------------+----------------------------+-----------------------+%n");
            System.out.format("| ID              | Student's Name             | Subject Major         |%n");
            System.out.format("+-----------------+----------------------------+-----------------------+%n");
            System.out.format(format, "2023-" + studentID, student.getName(), student.getMajor());
            System.out.format("+-----------------+----------------------------+-----------------------+%n");

        }
        else {
            System.out.println("Student with 2023-" +studentID+ " not found in Student's Records");
        }
    }

    public void deleteStudent(String studentID) {

        if (Hashtable.containsKey("2023-" + studentID)) {

            Student removedStudent = Hashtable.get("2023-" + studentID);
            Hashtable.remove("2023-" + studentID);

            System.out.println("\nStudent with ID 2023-" + studentID + " (" + removedStudent.getName() + ") deleted.");
            System.out.println("Major: " + removedStudent.getMajor());
        } 
        
        else {
            System.out.println("Student with ID 2023-" + studentID + " not found from Student's Records");
        }
    }
     

    public void displayAllStudents() {
        if (Hashtable.isEmpty()) {
            System.out.println("No student information available.");
        } 
        
        else {
            String format = "| %-15s | %-26s | %-21s |%n";
            System.out.format("+-----------------+----------------------------+-----------------------+%n");
            System.out.format("| ID              | Student's Name             | Subject Major         |%n");
            System.out.format("+-----------------+----------------------------+-----------------------+%n");
            for (String studentID : Hashtable.keySet()) {
                Student student = Hashtable.get(studentID);
                System.out.format(format, studentID, student.getName(), student.getMajor());
            }
    
            System.out.format("+-----------------+----------------------------+-----------------------+%n");
        }
    }
    

    
    public static void main(String[] args) {
        
        StudentDirectory directory = new StudentDirectory();
        Scanner scan = new Scanner(System.in);
       
        int choice;
        String studentID; // Declare studentID here


        do {
            System.out.println("\n       ABCD SCHOOL UNIVERSITY STUDENT'S RECORDS         ");
            System.out.println("------------------------------------------------------");
            System.out.println("1. Search Student");
            System.out.println("2. Add Student");
            System.out.println("3. Delete Record of Student");
            System.out.println("4. Display All Records");
            System.out.println("------------------------------------------------------");
            System.out.print("Your choice: ");

            choice = scan.nextInt();

            switch (choice) {
            
                case 1: 
                System.out.print("Enter the Student ID to search (6 digits): ");
                studentID = scan.next(); // Prefix the entered ID for search
                directory.searchStudent(studentID);
                break;

                case 2: 

                do {
                    System.out.print("Enter the ID to add (6 digits): ");
                    String inputID = scan.next(); // Input without the prefix
                    
                    if (inputID.length() != 6 || !inputID.matches("\\d+")) {
                        System.out.println("Invalid Student ID. Please enter a 6-digit number.");
                        continue; // Prompts the user again for input
                    }
                    
                    studentID = "2023-" + inputID; // Adding the prefix to form the ID
                
                    if (directory.Hashtable.containsKey(studentID)) {
                        System.out.println("A student ID " + studentID + " already exists.");
                        continue; // Prompts the user again for input
                    }
                    
                    scan.nextLine(); // Consume the newline character
                    
                    System.out.print("Enter Student's Name: ");
                    String name = scan.nextLine();
                    
                    System.out.print("Enter Subject Major: ");
                    String major = scan.nextLine();
                    
                    directory.addStudent(inputID, name, major); // Store without prefix
                    break; // Breaks the loop when valid input is received
                } 
                
                while (true);
                
                break;

                case 3:

                    System.out.print("Enter the Student ID to delete: ");
                    studentID = scan.next();
                    directory.deleteStudent(studentID);
                
                    break;

                case 4:

                    directory.displayAllStudents();
                    break;

                default:

                    System.out.println("Invalid choice");
                    break;
            }
        }
        while (choice != 5);
    }
}
    



