import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public final class  problem1462 {

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
        while(!isSorted) {
            isSorted = true;
            for (int i = 0; i < n-1; i++) {
                if (Array[i] % 10 > Array[i + 1] % 10) {
                    isSorted = false;

                    int buf = Array[i];
                    Array[i] = Array[i + 1];
                    Array[i + 1] = buf;
                } else if (Array[i] % 10 == Array[i + 1] % 10) {
                    if (Array[i] > Array[i + 1]) {

                        isSorted = false;

                        int buf = Array[i];
                        Array[i] = Array[i + 1];
                        Array[i + 1] = buf;
                    }
                }
            }
        }

        for (int i = 0; i < n; ++i)
            System.out.print(Array[i]+ " ");


    }
}