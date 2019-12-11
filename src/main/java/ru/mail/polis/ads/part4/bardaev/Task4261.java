package ru.mail.polis.ads.part4.bardaev;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Task4261 {
    private Task4261() {}

    static int count = 0;

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        long[] arr = new long[n];

        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        Merge_Sort(arr);

        out.println(count);
        out.flush();
    }

    public static long[] Merge_Sort(long[] arr)
    {
        if (arr.length == 1)
            return arr;
        int mid_point = arr.length / 2;


        long[] left = Arrays.copyOfRange(arr, 0, mid_point);
        long[] right = Arrays.copyOfRange(arr, mid_point, arr.length);



        arr = Merge(Merge_Sort(left), Merge_Sort(right));
        return arr;
    }

    public static long[] Merge(long[] mass1, long[] mass2)
    {

        int a = 0, b = 0;
        long[] merged = new long[mass1.length + mass2.length];
        for (int i = 0; i < mass1.length + mass2.length; i++)
        {
            if (b < mass2.length && a < mass1.length)
                if (mass1[a] > mass2[b])
                {
                    merged[i] = mass2[b++];
                    count = count + (mass1.length - a);
                }
                else //if int go for
                {
                    merged[i] = mass1[a++];
                }
            else
            if (b < mass2.length)
            {
                merged[i] = mass2[b++];
            }
            else
            {
                merged[i] = mass1[a++];
            }
        }
        return merged;
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
