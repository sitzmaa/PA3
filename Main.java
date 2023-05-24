// Alex Sitzman
public class Main {
    public static void main(String[] args) {
        if(args.length<2) {
            System.err.println("usage: total <coin values>");
            return;
        }
        
        int total = Integer.parseInt(args[0]); // find the total value to add up to
        int largest = 0;
        for (int i = 1; i < args.length; i++) { // find the largest coin value
            int holder = Integer.parseInt(args[i]);
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
        for (int i = 1; i < args.length; i++) {
            int value = Integer.parseInt(args[i]);
            coinValues[value] = value;
        }

        // Call solver function
        CoinParser cParser = new CoinParser(coinValues);
        cParser.initialize(total);
    }
}

