package ru.mail.polis.ads.part3.maksimshengeliia;

import java.io.*;
import java.math.BigInteger;
import java.util.*;

/*
*   https://www.e-olymp.com/ru/submissions/5908050
* */
public class Task4 {
    private Task4() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        String s = in.next();
        String[] strings = s.split("\\s+");
        int length = strings.length;
        BigInteger[] array = new BigInteger[length];
        for (int i = 0; i < length; i++) {
            array[i] = new BigInteger(strings[i]);
        }
        BigInteger j = kPorStat(array, n - 1);
        out.println(j);

    }

    private static int partition(BigInteger[] array, int l, int r) {
        BigInteger p = array[l];
        int j = l;
        for (int i = l + 1; i < r; ++i) {
            if (array[i].compareTo(p) > 0) {
                j++;
                swap(array, i, j);
            }
        }
        swap(array, l, j);
        return j;
    }

    private static void swap(BigInteger[] array, int i, int j) {
        BigInteger temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static BigInteger kPorStat(BigInteger[] array, int k) {
        int l = 0;
        int r = array.length;
        while (true) {
            int mid = partition(array, l, r);
            if (mid == k) {
                return array[mid];
            }
            else if (k < mid) {
                r = mid;
            }
            else {
                l = mid + 1;
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
                    tokenizer = new StringTokenizer(reader.readLine(), "\n");
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
