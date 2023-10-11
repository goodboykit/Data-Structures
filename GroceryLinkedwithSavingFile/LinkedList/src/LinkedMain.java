import java.util.Scanner;

    public class LinkedMain {
     
    public static void startProgram() {   

    Features myGrocery = new Features();
    Scanner scan = new Scanner(System.in); 

    final String myFile = "SavedtoFile.txt";

     // Load saved items from file when the program starts
     myGrocery.displaySavedItemsAsTable(myFile);


    while (true) {

      System.out.println("\n" + "-------- WELCOME TO YOUR SHOPPING LIST --------" + "\n");
      
      System.out.println("[1] INSERT ITEM LIST");
      System.out.println("[2] REMOVE ITEM LIST");
      System.out.println("[3] MARK IF PURCHASED");
      System.out.println("[4] DISPLAY SHOPPING LIST");
      System.out.println("[5] MODIFY SPECIFIC ITEM");
      System.out.println("[6] EXIT");


      System.out.print("\n" + "YOUR CHOICE: ");

      String input = scan.nextLine();
            
      if (!input.matches("[1-6]")) {
        System.out.println("Invalid choice. Please select a valid option (1-6).");
        continue; // Skip the current iteration and prompt again
    }

    int num = Integer.parseInt(input);

      switch(num){

        case 1: 

        System.out.print("Enter Grocery Name: ");
        String groceryName = scan.nextLine();
                
        System.out.print("Enter Item Description: ");
        String groceryDesc = scan.nextLine();
        
        System.out.print("Enter Item's Price: ₱ ");
        double price = Double.parseDouble(scan.nextLine());
        
        myGrocery.insertAtEnd(groceryName, groceryDesc, price);
        myGrocery.saveAccountDetails();
        break;


        case 2:

        System.out.println("What item do you want to remove:");
        String removeItem = scan.nextLine(); // Capture the item name for removal
        myGrocery.remove(removeItem);
        myGrocery.saveAccountDetails();
        break;

        case 3: 

        System.out.println("Enter the item name to mark as purchased: ");
        String itemPurchased = scan.nextLine();

        myGrocery.markItemAsPurchased(itemPurchased);
        myGrocery.saveAccountDetails(); // Save after marking as purchased
        break;

        case 4: 
        System.out.println("");
        myGrocery.displaySavedItemsAsTable(myFile);
        break;

        case 5:
        System.out.print("Enter the name of the item you want to modify: ");
        String itemToModify = scan.nextLine();
        myGrocery.modifyItem(itemToModify);
        myGrocery.saveAccountDetails(); // Save changes to the file
        break;

        
        case 6:
        System.exit(0); // Exit the program
        break;

        default:
        System.out.println("Invalid choice. Please select a valid option (1-6).");
        break;

      }

      // Add this prompt before looping again
      System.out.println("Press Enter to continue...");
      scan.nextLine(); // Wait for user to press Enter

      }


    }

  }

