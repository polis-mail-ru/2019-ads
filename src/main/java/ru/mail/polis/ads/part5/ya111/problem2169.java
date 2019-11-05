import java.io.*;
import java.util.*;


public class problem2169 {
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

    public static void swap (int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static boolean nextSet (int[] a, int n) {
        int j = n - 2;
        while (j != -1 && a[j] >= a[j + 1]) j--;
        if (j == -1)
            return false;
        int k = n - 1;
        while (a[j] >= a[k]) k--;
        swap(a, j, k);
        int l = j + 1, r = n - 1;
        while (l < r)
            swap(a, l++, r--);
        return true;
    }


    public static void problem2169(String[] args) throws IOException {
        final InputStreamReader input = new InputStreamReader(System.in);
        final BufferedReader in = new BufferedReader(input);
        int n = Integer.parseInt(in.readLine());
        int[] a = new int[n];
        for (int i = 0; i < n; ++i) {
            a[i] = i + 1;
        }
        try (PrintWriter out = new PrintWriter(System.out)) {
            for (int i = 0; i < n; ++i)
                out.print(a[i]+ " ");

            while (nextSet(a, n)) {
                out.println();
                for (int i = 0; i < n; ++i)
                    out.print(a[i]+ " ");
            }
        }






    }
}