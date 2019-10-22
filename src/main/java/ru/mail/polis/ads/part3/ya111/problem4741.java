import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public final class problem4741 {

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


    public static void main(String[] args) {
        final FastScanner sc = new FastScanner(System.in);
        int n = sc.nextInt();
        int [] Array = new int[n];
        for (int i = 0; i < n; ++i) {
            Array[i] = sc.nextInt();
        }
        boolean isSorted = false;
        int counter = 0;
        while(!isSorted) {
            isSorted = true;
            for (int i = 0; i < n-1; i++) {
                if (Array[i]> Array[i + 1]) {
                    isSorted = false;
                    counter++;

                    int buf = Array[i];
                    Array[i] = Array[i + 1];
                    Array[i + 1] = buf;
                }
            }
        }

        System.out.print(counter);


    }
}