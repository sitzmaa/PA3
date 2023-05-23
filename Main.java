// Alex Sitzman
public class Main {
    public static void main(String[] args) {
        if(args.length<2) {
            System.err.println("usage: total <coin values>");
            return;
        }
        
        int total = Integer.parseInt(args[0]);
        int largest = 0;
        for (int i = 1; i < args.length; i++) {
            int holder = Integer.parseInt(args[i]);
            if (largest < holder) {
                largest = holder;
            }
        }
        int[] coinValues = new int[largest+1];
        for (int i = 0; i < largest; i++) {
            coinValues[i] = 0;
        }
        for (int i = 1; i < args.length; i++) {
            int value = Integer.parseInt(args[i]);
            coinValues[value] = value;
        }
        CoinParser cParser = new CoinParser(coinValues);
        cParser.initialize(total);
    }
}

