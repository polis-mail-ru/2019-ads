import java.io.*;
import java.util.*;


public class problem4261 {
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

    private static int merge(int[] Array, int[] left, int[] right) {
        int i = 0, j = 0, count = 0;
        while (i < left.length || j < right.length) {
            if (i == left.length) {
                Array[i + j] = right[j];
                j++;
            } else if (j == right.length) {
                Array[i + j] = left[i];
                i++;
            } else if (left[i] <= right[j]) {
                Array[i + j] = left[i];
                i++;
            } else {
                Array[i + j] = right[j];
                count += left.length - i;
                j++;
            }
        }
        return count;
    }

    private static int solve(int[] Array) {
        if (Array.length < 2)
            return 0;

        int m = (Array.length + 1) / 2;
        int left[] = Arrays.copyOfRange(Array, 0, m);
        int right[] = Arrays.copyOfRange(Array, m, Array.length);

        return solve(left) + solve(right) + merge(Array, left, right);
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        int n = in.nextInt();
        int[] Array = new int[n];
        for (int i = 0; i < n; ++i) {
            Array[i] = in.nextInt();
        }
        System.out.println(solve(Array));
    }
}