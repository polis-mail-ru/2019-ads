package part3;

import java.io.*;
import java.util.StringTokenizer;

// https://www.e-olymp.com/ru/submissions/5910781

public class Task5 {

    private static class Key implements Comparable<Key> {
        int i, j, k;

        Key(int i, int j, int k) {
            this.i = i;
            this.j = j;
            this.k = k;
        }

        @Override
        public int compareTo(Key o) {
            int c = this.i - o.i;
            return c == 0 ? this.k - o.k : c;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        Key[] numbers = new Key[100001];
        for (int i = 0; i < n; i++) {
            numbers[i] = new Key(in.nextInt(), in.nextInt(), i);
        }
        sort(numbers, 0, n - 1);
        for(int i = 0; i < n; i++) out.println(numbers[i].i + " " + numbers[i].j);
    }

    private static void merge(Key[] arr, int l, int m, int r)
    {
        int n1 = m - l + 1;
        int n2 = r - m;

        Key[] left = new Key [n1];
        Key[] right = new Key [n2];

        System.arraycopy(arr, l, left, 0, n1);
        for (int j = 0; j < n2; j++)
            right[j] = arr[m + 1 + j];

        int i = 0, j = 0;

        int k = l;
        while (i < n1 && j < n2)
        {
            if (left[i].compareTo(right[j]) <= 0)
            {
                arr[k] = left[i];
                i++;
            }
            else
            {
                arr[k] = right[j];
                j++;
            }
            k++;
        }

        while (i < n1)
        {
            arr[k] = left[i];
            i++;
            k++;
        }

            while (j < n2)
            {
                arr[k] = right[j];
                j++;
                k++;
        }
    }

    private static void sort(Key[] arr, int l, int r)
    {
        if (l < r)
        {
            int m = (l + r) / 2;

            sort(arr, l, m);
            sort(arr , m + 1, r);
            merge(arr, l, m, r);
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

        long nextLong(){ return Long.parseLong(next());}
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
