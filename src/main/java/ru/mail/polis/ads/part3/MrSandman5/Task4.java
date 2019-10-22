package part3;

//https://www.e-olymp.com/ru/submissions/5923782

import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Task4 {

    private static void solve(final FastScanner in, final PrintWriter out) {
        int k = in.nextInt();
        int n = 0;
        ArrayList<BigInteger> array = new ArrayList<>(100001);
        String tmp = in.next();
        array.add(new BigInteger(tmp));
        while (in.tokenizer.hasMoreTokens()){
            array.add(new BigInteger(in.tokenizer.nextToken()));
        }
        k = array.size() - k;
        out.println(quickSelect(array, k).toString());
    }

    private static BigInteger quickSelect(ArrayList<BigInteger> arr, int k){
        if (arr.size()==1){
            return arr.get(0);
        }
        int pivot= (int) (Math.random() * arr.size());
        ArrayList<BigInteger> lows = new ArrayList<>();
        ArrayList<BigInteger> highs = new ArrayList<>();
        ArrayList<BigInteger> pivots = new ArrayList<>();

        for (BigInteger num : arr) {
            if (0 > num.compareTo(arr.get(pivot))) {
                lows.add(num);
            } else if (0 < num.compareTo(arr.get(pivot))) {
                highs.add(num);
            } else {
                pivots.add(num);
            }
        }

        if (k < lows.size()){
            return quickSelect(lows, k);
        } else if (k < lows.size() + pivots.size()){
            return pivots.get(0);
        } else {
            return quickSelect(highs,k - lows.size() - pivots.size());
        }
    }

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
        }

        String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}