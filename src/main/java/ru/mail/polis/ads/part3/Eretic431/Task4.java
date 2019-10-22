package main.java.ru.mail.polis.ads.part3.Eretic431;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;
import java.util.concurrent.ThreadLocalRandom;

/**
 * https://www.e-olymp.com/ru/submissions/5922584
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

        BigInteger answer = quickSort(arr, k - 1);

        System.out.println(answer);
    }

    private static BigInteger quickSort(BigInteger[] array, int k) {
        int left = 0;
        int right = array.length - 1;
        while (true) {
            int middle = partition(array, left, right);

            if (k == middle) {
                return array[middle];
            }

            if (k < middle) {
                right = middle;
            }

            if (k > middle) {
                left = middle + 1;
            }
        }
    }

    private static int partition(BigInteger[] mas, int l, int r)
    {
        BigInteger tmp;
        if (l != r)
        {
            int tmpPos = ThreadLocalRandom.current().nextInt(l, r);
            tmp = mas[tmpPos];
            mas[tmpPos] = mas[r];
            mas[r] = tmp;
        }

        BigInteger x = mas[r];
        int i = l - 1;
        for (int j = l; j <= r; j++)
        {
            if (mas[j].compareTo(x) >= 0)
            {
                ++i;
                tmp = mas[i];
                mas[i] = mas[j];
                mas[j] = tmp;
            }
        }
        return i;
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
