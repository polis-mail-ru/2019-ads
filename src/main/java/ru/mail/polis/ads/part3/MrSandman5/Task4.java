package part3;

import java.io.*;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Task4 {

    private static void solve(final FastScanner in, final PrintWriter out) {
        int k = in.nextInt();
        if (k < 1) {
            out.println(0);
            return;
        }
        int n = 0;
        BigInteger[] array = new BigInteger[100001];
        String tmp = in.next();
        array[n] = BigInteger.valueOf(Long.parseLong(tmp));
        while (in.tokenizer.hasMoreTokens()){
            n++;
            tmp = in.tokenizer.nextToken();
            array[n] = BigInteger.valueOf(Long.parseLong(tmp));
        }
        if (k > n) {
            out.println(0);
            return;
        }
        BigInteger[] new_array = new BigInteger[n];
        System.arraycopy(array, 0, new_array, 0, n);
        int left = 0, right = n - 1;
        while (true){
            int mid = partition(new_array, left, right);

            if (mid == k) {
                out.println(new_array[n - mid + 1]);
                return;
            }
            else if (k < mid) right = mid;
            else left = mid + 1;
        }
    }

    private static int partition(BigInteger[] array, int left, int right){
        BigInteger x = array[left];
        int i = left;
        int j = right;
        while (i <= j){
            while (array[i].compareTo(x) < 0) i++;
            while (array[j].compareTo(x) > 0) j--;
            if (i >= j) break;
            BigInteger tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
            i++;
            j--;
        }
        return j;
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

        long nextLong(){ return Long.parseLong(next());}
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}