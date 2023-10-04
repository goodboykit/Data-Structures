public class Problem2 {
        public static void main(String[] args) {
            
        int[] numbers = {12, 5, 8, 3, 10}; // Define an integer array
        int smallest = numbers[0]; // Initialize smallest with the first element

        // Loop through the array to find the smallest element (complete the code)
        for (int i = 1; i < numbers.length; i++) {


            if (smallest > numbers[i]){
            // Check if the current element is smaller than 'smallest'

                // If yes, update 'smallest' with the current element // Your code here
                smallest = numbers[i];
            }
        }

        // Print the smallest element
        System.out.println("The smallest element in the array is: " + smallest);

        } 
    }

