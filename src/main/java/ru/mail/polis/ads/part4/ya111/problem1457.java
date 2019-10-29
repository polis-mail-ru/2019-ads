import java.io.*;
import java.util.*;


public class Main {
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
        int n = in.nextInt();
        int m = in.nextInt();
        int i;
        int max = 0;
        for (i = 0; i < n; ++i) {
            int value = in.nextInt();
            if ((value < max ) && (value + max > m)) {
                break;
            }
            if (value > max)
                max = value;
        }
        if (i < n)
            System.out.println("No");
        else
            System.out.println("Yes");
    }
}