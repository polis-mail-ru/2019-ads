package main.java.ru.mail.polis.ads.part3.Eretic431;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

/**
 * https://www.e-olymp.com/ru/submissions/5907473
 */

public class Task4 {
    public static void main(String[] args) {
        final FastScanner in = new FastScanner(System.in);
        final int k = in.nextInt();
        final String[] lines = in.next().split(" ");
        final BigInteger[] arr = new BigInteger[lines.length];

        for (int i = 0; i < lines.length; i++) {
            arr[i] = new BigInteger(lines[i]);
        }

        quickSort(arr, 0, arr.length - 1);

        System.out.println(arr[k - 1]);
    }

    public static void quickSort(BigInteger[] array, int low, int high) {
        if (array.length == 0)
            return;

        if (low >= high)
            return;

        int middle = low + (high - low) / 2;
        BigInteger opora = array[middle];

        int i = low, j = high;
        while (i <= j) {
            while (array[i].compareTo(opora) > 0) {
                i++;
            }

            while (array[j].compareTo(opora) < 0) {
                j--;
            }

            if (i <= j) {
                BigInteger temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }

        if (low < j)
            quickSort(array, low, j);

        if (high > i)
            quickSort(array, i, high);
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
}
