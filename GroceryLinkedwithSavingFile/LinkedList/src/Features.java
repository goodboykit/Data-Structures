import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Scanner;


public class Features {

    final String SavingItems = "SavedtoFile.txt";
    String filePath = "SavedtoFile.txt"; // Specify the path to your text file
    ShoppingListItem head;

    Features() {

        // Clear the existing shopping list
        head = null;
        head = loadItemsFromFile(SavingItems);
    }

    //---------TO FUNCTIONALLY LOAD ITEMS FROM THE FILE------------
        private ShoppingListItem loadItemsFromFile(String filePath) {
        ShoppingListItem loadedList = null;
    
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            String itemName = null;
            String itemDescription = ""; // Define itemDescription here without initialization
            double itemPrice = 0.0; // Initialize itemPrice to 0.0
            String purchaseDesc = "Not Purchased"; // Initialize purchaseDesc
    
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Item Name: ")) {
                    itemName = line.substring("Item Name: ".length());
    
                    // Read the item description
                    itemDescription = reader.readLine().substring("Item Description: ".length());
    
                    // Read the line containing the price
                    String priceLine = reader.readLine();
                    try {
                        // Extract the price value and parse it as a double
                        itemPrice = Double.parseDouble(priceLine.split(" ")[3]);
                    } catch (NumberFormatException e) {
                        // Handle invalid price format here, e.g., log an error
                        System.err.println("Invalid price format: " + priceLine);
                        continue; // Skip this item
                    }
    
                    // Read the line containing purchaseDesc
                    purchaseDesc = reader.readLine().substring("Purchase Description: ".length());
    
                    // Create a new ShoppingListItem instance with the collected properties
                    ShoppingListItem newItem = new ShoppingListItem(itemName, itemDescription, itemPrice, purchaseDesc);
    
                    if (loadedList == null) {
                        loadedList = newItem;
                    } else {
                        ShoppingListItem current = loadedList;
                        while (current.next != null) {
                            current = current.next;
                        }
                        current.next = newItem;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    
        return loadedList;
    }
    

    //---------------SAVING TO FILE-----------------
    void saveAccountDetails() {
        try (PrintWriter clearWriter = new PrintWriter(new FileWriter(SavingItems, false))) {
            clearWriter.print(""); // Clear the file content
        } catch (IOException e) {
            System.out.println("Error clearing the file: " + e.getMessage());
            return; // Abort the operation if clearing the file fails
        }
    
        try (PrintWriter writer = new PrintWriter(new FileWriter(SavingItems, true))) {
            ShoppingListItem current = head;
    
            while (current != null) {
                writer.write("Item Name: " + current.itemName + "\n");
                writer.write("Item Description: " + current.itemDescription + "\n");
                writer.write("It's Price: ₱ " + current.itemPrice + "\n");
                writer.write("Purchase Description: " + current.purchaseDesc + "\n");
                writer.write("---------------------------------------------------\n\n");
                current = current.next;
            }
    
            System.out.println("Shopping list has been saved to " + SavingItems);
        } catch (IOException e) {
            System.out.println("Error saving purchased items: " + e.getMessage());
        }
    }
    
    
    
  void openTextFile() {

    String filePath = "/Users/goodboykit/LinkedList/src/SavedtoFile.txt";
    ProcessBuilder processBuilder = new ProcessBuilder("open", filePath);

        try {
            Process process = processBuilder.start();
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("File opened successfully.");
            } else {
                System.out.println("Error opening the file.");
         }
        } catch (IOException | InterruptedException e) {
         System.out.println("Error opening the file: " + e.getMessage());
        }
    }

    // -------------DISPLAYING ITEMS/ FROM FILE AND CONSOLE---------------------
    // void displayItemsFromFile(String SavingItems) {

    //     try (BufferedReader reader = new BufferedReader(new FileReader(SavingItems))) {
    //         String line;

    //         while ((line = reader.readLine()) != null) {
    //             System.out.println(line); // Print each line to the console
    //         }
    //     } catch (IOException e) {
    //         System.out.println("Error reading the file: " + e.getMessage());
    //     }
    // }


    // void displaySavedItemsAsTable(String filePath) {
    //     try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
    //         String line;
    //         DecimalFormat df = new DecimalFormat("0.00");
    
    //         // Define a table format
    //         String format = "| %-20s | %-30s | %-10s | %-30s |%n";
    //         System.out.format("+----------------------+--------------------------------+------------+--------------------------------+\n");
    //         System.out.format("| Item Name            | Item Description               | Price      | Purchase Description           |\n");
    //         System.out.format("+----------------------+--------------------------------+------------+--------------------------------+\n");
    
    //         String itemName = "";
    //         String itemDescription = "";
    //         double itemPrice = 0.0;
    //         String purchaseDesc = "";
    
    //         while ((line = reader.readLine()) != null) {
    //             if (line.startsWith("Item Name: ")) {
    //                 itemName = line.substring("Item Name: ".length());
    
    //                 // Read the item description
    //                 itemDescription = reader.readLine().substring("Item Description: ".length());
    
    //                 // Read the line containing the price
    //                 String priceLine = reader.readLine();
    //                 try {
    //                     // Extract the price value and parse it as a double
    //                     itemPrice = Double.parseDouble(priceLine.split(" ")[3]);
    
    //                     // Read the line containing purchaseDesc
    //                     purchaseDesc = reader.readLine().substring("Purchase Description: ".length());
    
    //                     System.out.printf(format, itemName, itemDescription, "₱ " + df.format(itemPrice), purchaseDesc);
    //                 } catch (NumberFormatException e) {
    //                     // Handle invalid price format here, e.g., log an error
    //                     System.err.println("Invalid price format: " + priceLine);
    //                     continue; // Skip this item
    //                 }
    //             }
    //         }
    
    //         System.out.format("+----------------------+--------------------------------+------------+--------------------------------+\n");
    //     } catch (IOException e) {
    //         System.out.println("Error reading the file: " + e.getMessage());
    //     }
    // }
    

    void displaySavedItemsAsTable(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            DecimalFormat df = new DecimalFormat("0.00");
    
            // Define a table format
            String format = "| [%-2d] %-15s | %-30s | %-10s | %-30s |%n";
            System.out.format("+----------------------+--------------------------------+------------+--------------------------------+\n");
            System.out.format("| [IN] ITEM NAME       | [ID] ITEM DESCRIPTION          | [P] PRICE  | [PD] PURCHASE DESCRIPTION      |\n");
            System.out.format("+----------------------+--------------------------------+------------+--------------------------------+\n");
    
            int itemId = 1;
    
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Item Name: ")) {
                    String itemName = line.substring("Item Name: ".length());
    
                    // Read the item description
                    String itemDescription = reader.readLine().substring("Item Description: ".length());
    
                    // Read the line containing the price
                    String priceLine = reader.readLine();
                    try {
                        // Extract the price value and parse it as a double
                        double itemPrice = Double.parseDouble(priceLine.split(" ")[3]);
    
                        // Read the line containing purchaseDesc
                        String purchaseDesc = reader.readLine().substring("Purchase Description: ".length());
    
                        System.out.printf(format, itemId, itemName, itemDescription, "₱ " + df.format(itemPrice), purchaseDesc);
                        itemId++;
    
                        // Add a separator line after each item
                        System.out.format("+----------------------+--------------------------------+------------+--------------------------------+\n");
                    } catch (NumberFormatException e) {
                        // Handle invalid price format here, e.g., log an error
                        System.err.println("Invalid price format: " + priceLine);
                        continue; // Skip this item
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }
    
    
    
    
  //-----------------------REMOVING THE ITEMS----------------------

  void remove(String removeItem) {
    if (head == null) {
        System.out.println("Your shopping list is empty");
        return;
    }

    removeItem = removeItem.trim(); // Remove leading/trailing spaces

    ShoppingListItem current = head;
    ShoppingListItem previous = null;
    boolean found = false;

    while (current != null) {
            if (current.itemName.equalsIgnoreCase(removeItem)) {
                if (previous != null) {
                previous.next = current.next;
             } 
                else {
                head = current.next;
                }

                System.out.println(removeItem + " has been removed from the shopping list.");
                found = true;
                break;
         }
            previous = current;
            current = current.next;
        }

        if (!found) {
            System.out.println(removeItem + " is not found in the shopping list.");
        }
    }


    // ------------------INSERTING ITEMS--------------------------
    void insertAtEnd(String itemName, String itemDescription, double itemPrice) {

        String purchaseDesc = "Not Purchased"; // Set the initial purchase description

        ShoppingListItem newItem = new ShoppingListItem(itemName, itemDescription, itemPrice, purchaseDesc);

        if (head == null) {
            head = newItem;

        } else {
            ShoppingListItem current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newItem;
        }

    }
    
    
   //----------------MARKED AS PURCHASED-------------------

   void markItemAsPurchased(String markedItem) {

    if (head == null) {
        System.out.println("Your shopping list is empty.");
        return;
    }

    markedItem = markedItem.trim().toLowerCase(); // Trim and convert to lowercase

    ShoppingListItem current = head;
    boolean found = false;

    while (current != null) {
        if (current.itemName.equalsIgnoreCase(markedItem)) { // Use equalsIgnoreCase
            if (!current.purchased) {
                current.purchaseDesc = "Purchased Successfully"; 
                current.purchased = true; 
                System.out.println(markedItem + " has been marked as purchased.");
            } 
            
            else {
                System.out.println(markedItem + " is already marked as purchased.");
                if (current.purchaseDesc.equals("Not Purchased")) {
                    current.purchaseDesc = "Purchased Successfully"; // Update the purchase description
                }
            }
            found = true;
            break;
        }
        current = current.next;
    }

        if (!found) {
            System.out.println(markedItem + " not found in the shopping list.");
        }
}


    //------------MODIFY THE ITEM SPECIFIC --------------
    void modifyItem(String itemNameToModify) {
        Scanner scan = new Scanner(System.in);
    
        if (head == null) {
            System.out.println("Your shopping list is empty.");
            return;
        }
    
        itemNameToModify = itemNameToModify.trim().toLowerCase(); // Trim and convert to lowercase
    
        ShoppingListItem current = head;
        boolean found = false;
    
        while (current != null) {
            if (current.itemName.equalsIgnoreCase(itemNameToModify)) { // Use equalsIgnoreCase
    
                System.out.println("Found item: " + current.itemName);
                System.out.println("Current description: " + current.itemDescription);
                System.out.println("Current price: ₱ " + current.itemPrice);
                System.out.println("Current purchase description: " + current.purchaseDesc);
    
                System.out.println("\nChoose what you want to modify: ");
                System.out.println("\n[1] Modify item name");
                System.out.println("[2] Modify item description");
                System.out.println("[3] Modify item price");
                System.out.println("[4] Revert purchase description");
                System.out.println("[5] Cancel");
    
                System.out.print("\nEnter your choice: ");
                String choice = scan.nextLine();
    
                switch (choice) {
                    case "1":
                        System.out.print("Enter new item name: ");
                        String newItemName = scan.nextLine();
                        current.itemName = newItemName;
                        System.out.println("Item name updated successfully.");
                        break;
    
                    case "2":
                        System.out.print("Enter new item description: ");
                        String newItemDescription = scan.nextLine();
                        current.itemDescription = newItemDescription;
                        System.out.println("Item description updated successfully.");
                        break;
    
                    case "3":
                        System.out.print("Enter new item price: ");
                        String newItemPriceInput = scan.nextLine();
    
                        try {
                            double newItemPrice = Double.parseDouble(newItemPriceInput);
                            current.itemPrice = newItemPrice;
                            System.out.println("Item price updated successfully.");
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid price format. Item price remains unchanged.");
                        }
                        break;
    
                    case "4":
                        String originalPurchaseDesc = "Purchased Successfully";
                        current.purchaseDesc = originalPurchaseDesc;
                        System.out.println("Purchase description reverted.");
                        System.out.println("Updated purchase description: " + current.purchaseDesc); // Display the updated purchase description
                        break;
    
                    case "5":
                        System.out.println("Modification canceled.");
                        break;
    
                    default:
                        System.out.println("Invalid choice. No modifications made.");
                        break;
                }
    
                found = true;
                break;
            }
            current = current.next;
        }
    
        if (!found) {
            System.out.println(itemNameToModify + " not found in the shopping list.");
        }
    }
    

    


}


    

