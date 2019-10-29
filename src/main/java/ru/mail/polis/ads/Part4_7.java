import java.io.*;
import java.util.StringTokenizer;
//https://www.e-olymp.com/ru/submissions/5971711
public class Part4_7 {

    private static void solve(final Part4_7.FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; ++i) {
            a[i] = in.nextInt();
        }
        int l = 0;
        int r = a[n - 1] - a[0];
        if (k == 2) {
            out.println(r);
        } else {
            while (l < r) {
                int last = 0;
                int cows = k - 1;
                int s = (l + r) / 2;
                for (int i = 1; i < n; ++i) {
                    if (s <= a[i] - a[last]) {
                        last = i;
                        cows--;
                    }
                }
                if (cows > 0) r = s;
                else l = s + 1;
            }
            out.println(l - 1);
        }
    }

    public static void main(final String[] arg) {
        final Part4_7.FastScanner in = new Part4_7.FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
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
}