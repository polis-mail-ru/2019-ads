import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class part_5 {

    private static boolean[] mask;
    private static int n;
    private static int[] array;

    private static void solve(FastScanner in, final PrintWriter out) {
        n = in.nextInt();
        mask = new boolean[n];
        array = new int[n];
        transposition(0, out);
    }

    private static void transposition(int count, final PrintWriter out) {
        if (count == n) {
            StringBuilder string = new StringBuilder();
            for (int i = 0; i < n; i++) {
                string.append(array[i] + " ");
            }
            out.println(string);
        }
        for (int i = 0; i < n; i++) {
            if (!mask[i]) {
                mask[i] = true;
                array[count] = i + 1;
                transposition(count + 1, out);
                mask[i] = false;
            }
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
        FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
