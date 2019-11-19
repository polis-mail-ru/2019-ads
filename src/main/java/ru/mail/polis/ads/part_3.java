import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class part_3 {

    private static void solve(FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] d = new int[n];
        int[] array = new int[n];
        int max = 0;
        for (int i = 0; i < n; i++) {
            array[i] = in.nextInt();
        }
        d[0] = 1;
        for (int i = 1; i < n; i++) {
            max = 0;
            for (int j = 0; j < i; j++) {
                if (array[j] != 0 &&
                        d[j] > max &&
                        array[i] % array[j] == 0) {
                    max = d[j];
                }
            }
            d[i] = max + 1;
        }
        out.print(max(d, n));
    }

    private static int max(int[] array, int n) {
        int max = array[0];
        for (int i = 1; i < n; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
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
        FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
