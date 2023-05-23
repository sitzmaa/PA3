// Alex Sitzman
import java.util.ArrayList;
public class CoinParser {
    private int[] optimals;
    private int[] coinValues;
    private final int MAX = Integer.MAX_VALUE;
    private ArrayList<ArrayList<Integer>> configurations;
    public CoinParser(int[] coinValues) {
        this.coinValues = coinValues;
        this.configurations = new ArrayList<ArrayList<Integer>>();
    }

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
        int q = MAX;
        int holder = 0;
        int q2 = 0;
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
        if (holder != 0) {
            configurations.get(i).clear();
            combine(configurations.get(i), configurations.get(i-holder));
            configurations.get(i).add(coinValues[holder]);
        }
        optimals[i] = q;
        return q;
    }

    private void combine(ArrayList<Integer> to, ArrayList<Integer> from) {
        for (int i = 0; i < from.size(); i++) {
            to.add(from.get(i));
        }
    }

}
