import java.util.Scanner;

public class PharmacyApp {
    private static final Scanner scan = new Scanner(System.in);
    private ReceiptQueue receiptQueue = new ReceiptQueue();

    public void start() {
        System.out.println("Welcome to the WinCo Pharmacy Drug Store!");

        while (true) {
            System.out.println("\n(1) View All Available Items Stored");
            System.out.println("(2) Add to your Cart");
            System.out.println("(3) View your Cart");
            System.out.println("(4) Checkout");
            System.out.println("(5) Exit the Program");

            String input = scan.nextLine();

            if (input.equals("1")) {
                receiptQueue.printAvailableItems();
            } 
            
            else if (input.equals("2")) {

                receiptQueue.addItemsToCart(receiptQueue); // Pass the ReceiptQueue instance as an argument
            } 
            
            else if (input.equals("3")) {
                receiptQueue.printCart();
            } 
            
            else if (input.equals("4")) {
                
                if (receiptQueue.purchases.isEmpty()) {
                    System.out.println("Your cart is empty. Please add items to your cart first.");
                    System.out.println("There are no purchases to generate.");
                } 
                
                else {
                    receiptQueue.processPurchase();
                    scan.nextLine(); // Wait for Enter key press
                }
            }

            else if (input.equals("5")){

                System.out.println("Thank you for purchasing at WinCo Pharmacy, Please come again!");
                System.exit(0);
                scan.close();
            }
            
            else {
                System.out.println("Invalid input. Please enter a number from 1 to 5 only.");
            }
        }
    }



    public static void main(String[] args) {
        PharmacyApp pharmacyApp = new PharmacyApp();
        pharmacyApp.start();
    }
}
