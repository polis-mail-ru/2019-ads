package ru.mail.polis.ads.part3.KoDim97;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Random;

/**
 * Problem solution template.
 */
public final class Task4827 {
    private Task4827() {
        // Should not be instantiated
    }
    public static String kth(String[] array, int l, int r, int k) {
        int i = l;
        int j = r;
        final Random random = new Random();
        String x = array[l + random.nextInt(r - l + 1)];
        while (i <= j) {
            while (new BigInteger(array[i]).compareTo(new BigInteger(x)) == 1) {
                i++;
            }
            while (new BigInteger(array[j]).compareTo(new BigInteger(x)) == -1) {
                j--;
            }
            if (i <= j) {
                String temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
                }
        }
        if (l <= k && k <= j) {
            return kth(array, l, j, k);
        }
        if (i <= k && k <= r) {
            return kth(array, i, r, k);
        }
        return array[k];
    }
    private static void solve(final FastScanner in, final PrintWriter out) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try{
            int k = Integer.parseInt(br.readLine());
            String[] s = br.readLine().split(" ");
            out.print(kth(s, 0, s.length - 1, k - 1));
        }
        catch (IOException ex){

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
