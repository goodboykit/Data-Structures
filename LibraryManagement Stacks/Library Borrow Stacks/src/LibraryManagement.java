import java.util.Scanner;
import java.util.Stack;

public class LibraryManagement {
    
    private static Stack <Library> StackBook = new Stack<>();
    private static Stack<Library> borrowBooks = new Stack<>();


    private final static Scanner scan = new Scanner(System.in);
    
    public LibraryManagement (){

        StackBook.push(new Library("To Kill a Mockingbird","Harper Lee"));
        StackBook.push(new Library("1984","George Orwell"));
        StackBook.push(new Library("The Vinci Code", "Dan Brown"));

    }


    public void borrowBook(Stack <Library> borrowBooks)  {
        
        Catalog();

        if (StackBook.isEmpty()) {
            System.out.println("No books available for borrowing.");
            return;
        }

        System.out.print("\nEnter the index of the book to borrow: ");
        int bookIndex = scan.nextInt()- 1;

        if (bookIndex >= 0 && bookIndex < StackBook.size()) {
            Library lib = StackBook.get(bookIndex);
            System.out.println("You are about to borrow: " + lib.title + " by " + lib.author);
            System.out.print("Confirm borrowing (YES/NO): ");

            String confirmation = scan.next();

            if (confirmation.equalsIgnoreCase("yes")) {
                lib = StackBook.remove(bookIndex);
                borrowBooks.push(lib);
                System.out.println("You have borrowed " + lib.title + " by " + lib.author);
            } 
            
            else {
                System.out.println("Borrowing canceled.");
            }

        } else {
                System.out.println("Invalid index. Please enter a valid index.");
            }


    }
    

    
    public void returnBook(Stack<Library> borrowBooks) {

        displayBorrowedBooks(borrowBooks);
     
        if (StackBook.isEmpty()){
            System.out.println("You haven't borrowed any books.");
            return;
        }

        System.out.print("Enter the index of the book to return: ");
        int titleReturn = scan.nextInt() -1;

        if (titleReturn >= 0 && titleReturn < borrowBooks.size()) {

            Library returnedBook = borrowBooks.remove(titleReturn);
            StackBook.push(returnedBook);
            System.out.println("You have returned " + returnedBook.title + " by " + returnedBook.author + " successfully.");
            System.out.println("Thank you for using our Library Book System!\n");

        } else {
            System.out.println("Invalid index. Please enter a valid index.");
        }
    }

    public void addBook(String title, String author) {
        Library lib = new Library(title, author);
        StackBook.push(lib);
        System.out.println("Added: " + lib.title + " by " + lib.author + " to the catalog.\n");
    }

    

    public void displayBorrowedBooks(Stack<Library> borrowBooks) {

        if (!borrowBooks.isEmpty()) {

            System.out.println("Your Borrowed Books: ");
            System.out.println("+------+--------------------------------+----------------------+");
            System.out.println("| No.  |            Title               |        Author        |");
            System.out.println("+------+--------------------------------+----------------------+");
    
            for (int i = 0; i < borrowBooks.size(); i++) {
                Library lib = borrowBooks.elementAt(i);
                System.out.format("| %-4d | %-30s | %-20s |%n", i + 1, lib.title, lib.author);
            }
    
            System.out.println("+------+--------------------------------+----------------------+");
        } else {
            System.out.println("You haven't borrowed books");
        }
    }

    public void Catalog() {
        if (!StackBook.isEmpty()) {
            System.out.println("Available Books Stored: ");
            System.out.println("+------+--------------------------------+----------------------+");
            System.out.println("| No.  |            Title               |        Author        |");
            System.out.println("+------+--------------------------------+----------------------+");
            
            for (int i = 0; i < StackBook.size(); i++) {
                Library book = StackBook.elementAt(i);
                System.out.format("| %-4d | %-30s | %-20s |%n", i + 1, book.title, book.author);
            }
            
            System.out.println("+------+--------------------------------+----------------------+");
            System.out.print("\nPress Enter to continue...");
            scan.nextLine(); // Consume the Enter key press
        } else {
            System.out.println("No Books Available");
        }
    }



     public static void main(String[] args) {

        LibraryManagement library = new LibraryManagement();
        
         while (true){

            System.out.println("----------------------------------------------");
            System.out.println("    No         MENU OPTIONS                  ");
            System.out.println("----------------------------------------------");
            System.out.println("   [1]     Add Book/s to Catalog             ");
            System.out.println("   [2]     Borrow Book/s                     ");
            System.out.println("   [3]     Return the Book/s you borrowed    ");
            System.out.println("   [4]     Display Borrowed Book/s           ");
            System.out.println("   [5]     View All Available Book/s         ");
            System.out.println("   [6]     Exit the Program                  ");
            System.out.println("---------------------------------------------");
            System.out.print("Your Choice: ");
            

            int num = scan.nextInt();
            scan.nextLine(); // Consume the newline character


            switch (num){
            
            case 1:
            System.out.println("\nEnter the title of the book: ");
            String title = scan.nextLine(); 
            System.out.println("\nEnter the author of the book: ");
            String author = scan.nextLine(); 
            library.addBook(title, author);
            break;
            case 2: 
//.
            library.borrowBook(borrowBooks);
            scan.nextLine(); // Consume the newline character
            break;

            case 3: 
//.
            library.returnBook(borrowBooks);
            break;

            case 4:
            //case 4:
            library.displayBorrowedBooks(borrowBooks);
            break;

            case 5:
            library.Catalog();
            break;
            
            case 6:
            System.out.println("Exiting the Library System. Goodbye!");
            System.exit(0);
            break; 

            default:
            System.out.println("Invalid choice. Please enter a valid option.");
            break;

            }

        }
    }
}





