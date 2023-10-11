import java.util.Scanner;

public class MainMain {
    
    public static void main(String[] args) {
        startTypingAnimation();
    }
    
    public static void startTypingAnimation() {
        String welcomeText = "\nWelcome to your shopping program list... \nWhere the fun begins at listing!.........";

        
        // Typing animation with a slower delay
        for (int i = 0; i < welcomeText.length(); i++) {
            System.out.print(welcomeText.charAt(i));
            
            try {
                Thread.sleep(0); // Slower delay between each character
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        System.out.println("\n");
        
        Scanner scan = new Scanner(System.in);
        
        // Animation for options
        animateOptions("[1] Continue to the main program!", "[2] Exit");
        
        System.out.print("\nYour choice: ");
        
        int choice = scan.nextInt();
        
        if (choice == 1) {
            // Continue to your main program
            LinkedMain.startProgram();

        } else if (choice == 2) {
            System.exit(0); // Exit the program
            
        } else {
            System.out.println("Invalid choice. Exiting the program.");
            System.exit(0); // Exit the program due to invalid input
        }
        
        scan.close();
    }
    
    public static void animateOptions(String... options) {
        for (String option : options) {
            for (int i = 0; i < option.length(); i++) {
                System.out.print(option.charAt(i));
                try {
                    Thread.sleep(0); // Slower delay for option animation
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println();
        }
    }
}
