package ru.mail.polis.ads.part3.KoDim97;

import javafx.util.Pair;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task4037 {
    private Task4037() {
        // Should not be instantiated
    }
    static void Merge(Pair<Integer, Integer>[] arr, int l, int m, int r)
    {
        int i, j, k;
        int n1 = m - l + 1;
        int n2 = r - m;

        Pair<Integer, Integer>[] L = new Pair[n1];
        Pair<Integer, Integer>[] R = new Pair[n2];

        for (i = 0; i < n1; i++)
            L[i] = arr[l + i];
        for (j = 0; j < n2; j++)
            R[j] = arr[m + 1+ j];
        i = 0;
        j = 0;
        k = l;
        while (i < n1 && j < n2)
        {
            if (L[i].getKey() <= R[j].getKey())
            {
                arr[k] = L[i];
                i++;
            }
            else
            {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
        while (i < n1)
        {
            arr[k] = L[i];
            i++;
            k++;
        }
        while (j < n2)
        {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    static void MergeSort(Pair<Integer, Integer>[] arr, int n)
    {
        int curr_size;
        int left_start;
        for (curr_size = 1; curr_size <= n-1;
             curr_size = 2*curr_size)
        {
            for (left_start = 0; left_start < n-1;
                 left_start += 2*curr_size)
            {
                int mid = Math.min(left_start + curr_size - 1, n-1);

                int right_end = Math.min(left_start
                        + 2*curr_size - 1, n-1);
                Merge(arr, left_start, mid, right_end);
            }
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        Pair<Integer, Integer>[] arr = new Pair[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new Pair<Integer, Integer>(in.nextInt(), in.nextInt());
        }
        MergeSort(arr, n);
        for (Pair<Integer, Integer> i :
                arr) {
            out.print(i.getKey());
            out.print(' ');
            out.println(i.getValue());
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
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
