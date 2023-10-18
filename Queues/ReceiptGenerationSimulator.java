// import java.util.Scanner;

// public class ReceiptGenerationSimulator {
    
//     static Scanner scan = new Scanner(System.in);
//     public static void main(String[] args) {
        
//         ReceiptQueue receiptQueue = new ReceiptQueue();
    
//         receiptQueue.addItem(new ReceiptGenerator("Pizza", 200.45, 2));
//         receiptQueue.addItem(new ReceiptGenerator("Burger", 100.0, 3));
//         receiptQueue.addItem(new ReceiptGenerator("Soda", 2.5, 5));
    
    
//         System.out.println("Welcome to the Store Queue Simulator!"); 
        
//         while (true) {
//             System.out.println("\n(1) View All Available Items Stored");
//             System.out.println("(2) Add to your Cart");
//             // System.out.println("(3) View your Cart");
//             System.out.println("(3) Checkout");
    
//             String input = scan.nextLine();
    
//             if (input.equals("1")) {
//                 receiptQueue.printAvailableItems();
//             } 
    
//           else if (input.equals("2")) {
//              addItemsToCart(receiptQueue);
    
//           }
                    
            
//             else if (input.equals("3")) {
//                 if (receiptQueue.queue.isEmpty()) {
//                     System.out.println("Your cart is empty. Please add items to your cart first.");
//                 } else {
//                     receiptQueue.processPurchase();
//                     System.out.print("Press Enter to serve another customer...");
//                     scan.nextLine(); // Wait for Enter key press
//                 }
//             } 
            
//             else {
//                 System.out.println("Invalid input. Please enter a valid option.");
//             }
    
//         }
//     }
// }
