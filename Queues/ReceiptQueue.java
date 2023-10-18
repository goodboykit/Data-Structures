import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.Date;
import java.util.Deque;
import java.util.Scanner;

public class ReceiptQueue {

    public Deque <Purchase> purchases = new ArrayDeque<>();

    private final static Scanner scan = new Scanner(System.in);

    private static Purchase[] hygieneItems = {
        new Purchase("Toothpaste", 4.99, 10),
        new Purchase("Shampoo", 7.49, 8),
        new Purchase("Soap", 1.99, 15)
    };
    
    private static Purchase[] dietaryTabsItems = {
        new Purchase("Vitamin C", 9.99, 7),
        new Purchase("Fish Oil", 12.49, 5),
        new Purchase("Multivitamins", 8.99, 9)
    };
    
    private static Purchase[] dietarySupplementsItems = {
        new Purchase("Protein Powder", 29.99, 3),
        new Purchase("Creatine", 19.99, 4),
        new Purchase("BCAA", 17.49, 6)
    };
    
    // Combine the arrays into the availableItems array
    private static Purchase[] availableItems = concatArrays(hygieneItems, dietaryTabsItems, dietarySupplementsItems);
    
    private static Purchase[] concatArrays(Purchase[]... arrays) {
        int totalLength = 0;
        for (Purchase[] array : arrays) {
            totalLength += array.length;
        }
    
        Purchase[] result = new Purchase[totalLength];
        int offset = 0;
        for (Purchase[] array : arrays) {
            System.arraycopy(array, 0, result, offset, array.length);
            offset += array.length;
        }
    
        return result;
    }


    //-----IF THEY ADD TO CART--------------
    public void addPurchase(Purchase item) {

        // Update the quantity of the selected item in the cart
        for (Purchase cartItem : purchases) {

            if (cartItem.getItemName().equals(item.getItemName())) {
                cartItem.setQuantity(cartItem.getQuantity() + item.getQuantity());
                return;  // Item found and updated, exit the method
            }
        }
        // If the item is not in the cart, add it to the cart
        purchases.add(item);
    }


    // PROCESSING THE PURCHASE AND IF THEY WANT TO ADD 
    public void processPurchase() {

    if (purchases.isEmpty()) {
        System.out.println("\nSelect the item to add to your cart by entering its number.");
        printAvailableItems();
        return;
    }

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

    Date date = new Date();
    String formattedDate = dateFormat.format(date);
    String formattedTime = timeFormat.format(date);

    int customerNumber = 1; 

    while (!purchases.isEmpty()) {

        Purchase item = purchases.poll();
        String receipt = generateReceipt(formattedDate, formattedTime, customerNumber, item);
        System.out.println(receipt);

        customerNumber++;

        if (!purchases.isEmpty()) {
            System.out.print("\nPress Enter to show the next receipt...");
            scan.nextLine();
        }
    }
        purchases.clear(); // Clear the cart
        System.out.print("\nPress Enter to go back to the main menu and purchase again");
    }

    //------FORMATTING THE RECEIPT---

    private String generateReceipt(String formattedDate, String formattedTime, int customerNumber, Purchase item) {

        String receipt = "------------------------------------------------\n" +
        "            WinCo Pharmacy Drug Store\n" +
        "        Bellingham Washington Arizona State\n" +
        "              98875 WA United States\n" +
        "                 (360) 756-7924\n" +
        "\n" +
        "            " + formattedDate + " " + formattedTime + "\n" +
        "            Customer Number:  " + String.format("%02d", customerNumber) + "\n" +
        "\n" +
        " QTY               ITEM            PRICE\n" +
        "------------------------------------------------\n";

    receipt += String.format(" %-14d  %-15s  ₱%-15.2f%n", item.getQuantity(), item.getItemName(), item.getTotalPrice());

    double subtotal = item.getTotalPrice();
    double taxRate = 0.10; // Example tax rate (10%)
    double tax = subtotal * taxRate;
    double total = subtotal + tax;

    receipt += "------------------------------------------------\n" +
        "Subtotal:        ₱" + String.format("%.2f", subtotal) + "\n" +
        "Deducted Tax:    ₱" + String.format("%.2f", tax) + "\n" +
        "\n" +
        "Total Amount:    ₱" + String.format("%.2f", total) + "\n" +
        "------------------------------------------------\n";

    return receipt;
}

   
    //-------PRINTING WHAT THEY HAVE ADDED TO CART---------------

    public void printCart() {

        System.out.println("\nCurrent Cart:" + "\n");
        System.out.printf("%-4s %-20s %-8s %-10s\n", "QTY", "ITEM", "PRICE", "TOTAL");
        System.out.println("--------------------------------------------");
    
        double totalAmount = 0.0;
    
        for (Purchase item : purchases) {
            
            double itemTotal = item.getTotalPrice();
            totalAmount += itemTotal;
    
            System.out.printf("%-4d %-20s ₱%-8.2f ₱%-10.2f\n", item.getQuantity(), item.getItemName(), item.getPrice(), itemTotal);
        }
    
        System.out.println("--------------------------------------------");
        System.out.printf("Total Amount: ₱%.2f%n", totalAmount);
    }
    
    

    //-------PRINTING ALL AVAILABLE ITEMS IN THE LIST---------------
    public void printAvailableItems() {
        System.out.println("\nAll Available Items Stored:\n");
    
        printItemsInSection("HYGIENE", hygieneItems);
        printItemsInSection("DIETARY TABS", dietaryTabsItems);
        printItemsInSection("Dietary Supplements", dietarySupplementsItems);
    }
    
    private void printItemsInSection(String sectionName, Purchase[] sectionItems) {
        System.out.println(sectionName + "\n");
        System.out.printf("%-4s %-20s %-8s %-10s\n", "No.", "ITEM", "PRICE", "QUANTITY");
        System.out.println("-------------------------------------------------");
    
        int itemNumber = 1;
        for (int i = 0; i < sectionItems.length; i++) {
            Purchase item = sectionItems[i];
            if (item.getQuantity() > 0) {
                System.out.printf("%-4d %-20s ₱%-8.2f %d\n", itemNumber, item.getItemName(), item.getPrice(), item.getQuantity());
                itemNumber++;
            }
        }
    
        System.out.println();
    }
    
    
    
    public static void addItemsToCart(ReceiptQueue receiptQueue) {
        String addItem = "yes";
    
        do {
            if (receiptQueue.purchases.isEmpty()) {
                System.out.println("\nYour cart is empty. Please add items to your cart first.");
            }
    
            receiptQueue.printAvailableItems();
    
            System.out.print("\nEnter the name of the item you want to purchase: ");
            String itemName = scan.nextLine();
            
            Purchase selectedItem = null;
            
            for (Purchase item : availableItems) {

                if (item.getItemName().equalsIgnoreCase(itemName)) {
                    selectedItem = item;
                    break;
                }
            }
            
            if (selectedItem == null) {
                System.out.println("\nInvalid item name. Please enter a valid item name.");
                continue;  // Go back to selecting another item
            }

            System.out.println("\nYou have selected " + selectedItem.getItemName() + " with ₱" + selectedItem.getPrice() + " for its price.");
            
            System.out.print("\nEnter the quantity you want to purchase: ");
            int purchaseQuantity = Integer.parseInt(scan.nextLine());
    
            if (purchaseQuantity > 0 && purchaseQuantity <= selectedItem.getQuantity()) {

                selectedItem.setQuantity(selectedItem.getQuantity() - purchaseQuantity);
    
                Purchase purchaseItem = new Purchase(selectedItem.getItemName(), selectedItem.getPrice(), purchaseQuantity);
                
                receiptQueue.addPurchase(purchaseItem);
    
                if (selectedItem.getQuantity() ==0) {
                    Purchase[] newArray = new Purchase[availableItems.length - 1];
                    int j = 0;
                    for (int i = 0; i < availableItems.length; i++) {
                        if (!availableItems[i].getItemName().equalsIgnoreCase(itemName)) {
                            newArray[j] = availableItems[i];
                            j++;
                        }
                    }
                    availableItems = newArray;
                }
            } 
            
            else {
                System.out.println("\nInvalid quantity. Please enter a valid quantity.");
                continue;  // Go back to selecting another item
            }
            
            receiptQueue.printCart();
    
            do {
                System.out.print("\nDo you want to add another item? (yes/no): ");
                addItem = scan.nextLine().toLowerCase();
            } 
            
            while (!addItem.equals("yes") && !addItem.equals("no"));
        } 
        
        while (addItem.equals("yes"));
    }
    
}
    

