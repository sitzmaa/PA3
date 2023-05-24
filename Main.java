import java.util.Scanner;

// Alex Sitzman
public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int total = promptTotal(sc); // find the total value to add up to
        int largest = 0;
        String[] coins = promptUser(sc);
        sc.close();
        for (int i = 1; i < coins.length; i++) { // find the largest coin value
            int holder = Integer.parseInt(coins[i]);
            if (largest < holder) {
                largest = holder;
            }
        }
        int[] coinValues = new int[largest+1]; // create an array of size equal to the largest coin
        
        /*
         * Populate the array of coin values, coins not represented are given value 0
         */
        for (int i = 0; i < largest; i++) {
            coinValues[i] = 0;
        }
        for (int i = 1; i < coins.length; i++) {
            int value = Integer.parseInt(coins[i]);
            coinValues[value] = value;
        }

        // Call solver function
        CoinParser cParser = new CoinParser(coinValues);
        cParser.initialize(total);
    }

    public static String[] promptUser(Scanner sc) {
        System.out.print("Enter a coin values: ");
        String input = sc.nextLine();
        String[] args = input.split(" ");
        return args;
    }

    public static int promptTotal(Scanner sc) {
        System.out.print("Enter a value: ");
        int input = Integer.parseInt(sc.nextLine());
        return input;
    }
}

