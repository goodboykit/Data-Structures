import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner (System.in);
        int choice;

        while (true) {

        System.out.println("-------SELECT AN OPTION-----------------");
        System.out.println("1. Compute Student's Grade");
        System.out.println("2. Compute for Monthly Sales Figure");
        System.out.println("----------------------------------------");
        System.out.print("Enter your choice: ");

        choice = scan.nextInt();

        if (choice == 1) {

            double[] grades = new double[10];

            System.out.println("");

            for(int i =0; i<grades.length; i++){
                System.out.print("Enter grade " + (i + 1) + ": ");
                grades[i] = scan.nextDouble();
            }

            Datas GradeCalcu = new CalcuData(grades);       
            Arrays.sort(grades);

            double av = GradeCalcu.Average(grades);
            double max = GradeCalcu.MaximumValue(grades);
            double min = GradeCalcu.MinimumValue(grades);
        
            System.out.println("------------------------------------");
            System.out.println("The Average Grade is: " + av);
            System.out.println("The Maximum value of Grade is: " + max);
            System.out.println("The Minimum Value of Grade is: " + min);
            System.out.println("------------------------------------");

            break; // Exit the loop after valid input
        }

        if (choice == 2){

            double[] sales = new double [6];
            System.out.println("");

            for(int i =0; i<sales.length; i++){
                System.out.print("Enter monthly sale figure " + (i + 1) + ": ₱ ");
                sales[i] = scan.nextDouble();
            }

            Datas DataCalcu = new CalcuData(sales);
            Arrays.sort(sales);

            double av = DataCalcu.Average(sales);
            double max = DataCalcu.MaximumValue(sales);
            double min = DataCalcu.MinimumValue(sales);

            System.out.println("-------------------------------------------");
            System.out.println("The Average of Monthly Sales: ₱ " + av);
            System.out.println("The Maximum value of Monthly Sales: ₱ " + max);
            System.out.println("The Minimum Value of Monthly Sales : ₱ " + min);
            System.out.println("--------------------------------------------");

            break; // Exit the loop after valid input

         }

            else {
                 System.out.println("\nInvalid choice. Please select 1 or 2\n");
             }

        }
        scan.close();

    }
}
