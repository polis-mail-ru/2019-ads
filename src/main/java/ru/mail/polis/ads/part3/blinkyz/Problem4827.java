package ru.mail.polis.ads.part3.blinkyz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class Problem4827 {
    private Problem4827() {
    }

    private static void swap(BigInteger[] a, int i, int j) {
        BigInteger temp = a[j];
        a[j] = a[i];
        a[i] = temp;
    }

    private static int partition_lomuto(BigInteger[] a, int l, int r) {
        BigInteger x = a[r];
        int i = l - 1;
        for (int j = l; j < r; j++) {
            if (a[j].compareTo(x) <= 0) {
                ++i;
                swap(a, i, j);
            }
        }
        swap(a, i + 1, r);
        return i + 1;
    }

    private static BigInteger findOrderStatistic(final BigInteger[] a, final int k, final int len) {
        final int kReversed = len - k;
        int left = 0, right = len - 1;
        int mid;

        while (true) {
            mid = partition_lomuto(a, left, right);

            if (kReversed == mid) {
                return a[mid];
            } else if (kReversed < mid) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
    }

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) throws IOException {
            reader = new BufferedReader(new InputStreamReader(in));
        }

        String next() throws NoSuchElementException {
            return tokenizer.nextToken();
        }

        int nextInt() throws NoSuchElementException {
            return Integer.parseInt(next());
        }

        BigInteger nextBigInt() throws NoSuchElementException {
            return new BigInteger(next());
        }

        void tokenizeNextLine() throws IOException {
            tokenizer = new StringTokenizer(reader.readLine());
        }
    }

    private static void solve() throws IOException {
        FastScanner fastScanner = new FastScanner(System.in);
        fastScanner.tokenizeNextLine();

        int k = fastScanner.nextInt();
        int i = 0;
        BigInteger[] a = new BigInteger[(int) 1e5];

        fastScanner.tokenizeNextLine();
        BigInteger cur;
        while (true) {
            try {
                cur = fastScanner.nextBigInt();
            } catch (NoSuchElementException ex) {
                break;
            }
            a[i] = cur;
            ++i;
        }

        System.out.println(findOrderStatistic(a, k, i));
    }

    public static void main(String[] args) throws IOException {
        solve();
    }
}
