import java.io.*;
import java.util.StringTokenizer;

public class Part4_1 {
//https://www.e-olymp.com/ru/submissions/5971513
    private static void solve(final Part4_1.FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        boolean b = true;
        a[0] = in.nextInt();
        for (int i = 1; i < n; i++) {
            a[i] = in.nextInt();
            if (a[(i - 1) / 2] > a[i]) {
                b = false;
                break;
            }
        }
        if (b) out.println("YES");
        else out.println("NO");
        out.flush();
    }

    public static void main(final String[] arg) {
        final Part4_1.FastScanner in = new Part4_1.FastScanner(System.in);
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
