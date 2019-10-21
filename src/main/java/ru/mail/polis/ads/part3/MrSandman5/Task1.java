package part3;

//https://www.e-olymp.com/ru/submissions/5911081

import java.io.*;
import java.util.StringTokenizer;

public class Task1 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] array = new int[100001];
        for (int i = 0; i < n; i++) {
            array[i] = in.nextInt();
        }
        sort(array, 0, n - 1);
        for (int i = 0; i < n; i++) {
            out.print(array[i] + " ");
        }
        out.println();
    }

    private static void merge(int[] arr, int l, int m, int r)
    {
        int n1 = m - l + 1;
        int n2 = r - m;

        int[] left = new int [n1];
        int[] right = new int [n2];

        System.arraycopy(arr, l, left, 0, n1);
        for (int j = 0; j < n2; j++)
            right[j] = arr[m + 1 + j];

        int i = 0, j = 0;

        int k = l;
        while (i < n1 && j < n2)
        {
            if (left[i] <= right[j])
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

    private static void sort(int[] arr, int l, int r)
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

