// Alex Sitzman
import java.util.ArrayList;
public class CoinParser {
    private int[] optimals; // optimal number of coins
    private int[] coinValues; // stored values of all coins, values that are not present are stored as 0
    private final int MAX = Integer.MAX_VALUE/2; // max integer value
    private ArrayList<ArrayList<Integer>> configurations; // an arraylist that stores the optimal configurations
    
    // Constructor
    /*
     * Takes in an array of integers padded with 0s
     * non zero indeces indicate the existence of a coin
     */
    public CoinParser(int[] coinValues) {
        this.coinValues = coinValues;
        this.configurations = new ArrayList<ArrayList<Integer>>();
    }

    // Driver function
    /*
     * take in a total to reach
     * set optimals to max value and add configurations
     * call evaluate
     * print out result and optimal configuration
     */
    public void initialize(int total) {
        optimals = new int[total+1];
        for (int i = 0; i <= total; i++) {
            optimals[i] = Integer.MAX_VALUE; 
            configurations.add(new ArrayList<Integer>());
        }
        System.out.printf("Number of coins: %d\nCoins: ",evaluate(total));
        for (int i = 0; i < configurations.get(total).size(); i++) {
            System.out.printf("%d ", configurations.get(total).get(i));
        }
        System.out.println();
    }

    // Working Function
    /*
     * if an optimal value for a value has already been found, return it
     * if the value is equal to a single coin return 1 and edit optimal configuration accordingly
     * else recursively call on lower totals to find comprising configurations
     */
    public int evaluate(int i) {
        if (optimals[i] != Integer.MAX_VALUE) {
            return optimals[i];
        }
        if(i < coinValues.length) {
            if(coinValues[i] != 0) {
                configurations.get(i).add(coinValues[i]);
                optimals[i] = 1;
                return 1;
            }
        }
        int q = MAX; // main comparitor
        int holder = 0; // holder the value of j for reconstruction
        int q2 = 0; // hold the value of q to check for change
        /*
         * if a coin value is present then q is compared to 1 + total minus coin value
         * else check various sub problems
         */
        for (int j = 1; j < i; j++) {
            if (j < coinValues.length) {
                if(coinValues[j] != 0) {

                    q2 = q;
                    q  = Integer.min(q, 1 + evaluate(i-j));
                    if (q != q2) {
                        holder = j;
                    }
                }
                else {
                    q2 = q;
                    q = Integer.min(q, evaluate(i-j) + evaluate(j));
                    if (q != q2) {
                        holder = j;
                    }
                }
            }
            else {
                q2 = q;
                q = Integer.min(q, evaluate(i-j) + evaluate(j));
                if (q != q2) {
                    holder = j;
                }
            }
        }
        // holder will store where change occurs
        // therefore the the optimal configuration will consit of the configuration of i-holder and the value at holder
        if (holder != 0 && holder < coinValues.length) {
            configurations.get(i).clear();
            combine(configurations.get(i), configurations.get(i-holder));
            configurations.get(i).add(coinValues[holder]);
        } else if (holder != 0) {
            configurations.get(i).clear();
            combine(configurations.get(i), configurations.get(i-holder));
            combine(configurations.get(i), configurations.get(holder));
        }
        // save the optimal value and return
        optimals[i] = q;
        
        return q;
    }

    // helper function to combine combinations
    private void combine(ArrayList<Integer> to, ArrayList<Integer> from) {
        for (int i = 0; i < from.size(); i++) {
            to.add(from.get(i));
        }
    }

}
