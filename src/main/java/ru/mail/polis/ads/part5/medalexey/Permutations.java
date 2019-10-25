package ru.mail.polis.ads.part5.medalexey;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 *  Задача: Перестановки https://www.e-olymp.com/ru/problems/2169
 *  Тестирование:   https://www.e-olymp.com/ru/submissions/5946255
 */
public class Permutations {

    private static int[] sequence;

    private Permutations() {
    }


    private static void solve(final FastScanner in, final PrintWriter out) {
        int numberOfNumbers = in.nextInt();
        sequence = new int[numberOfNumbers];

        fillSequence();

        do {
            out.println( convertArrayToString() );
        } while ( makeNextSequence() );
    }


    private static boolean makeNextSequence() {
        int k = sequence.length - 2;

        if (sequence.length == 1) {
            return false;
        }

        while (k >= 0 && sequence[k] > sequence[k+1]) {
            k--;

            if (k < 0 || k == 0 && sequence[k] > sequence[k+1]) {
                return false;
            }
        }

        swapElements(k, findBiggerElement(k));
        reverseArrayPart(k+1, sequence.length-1);

        return true;
    }


    private static int findBiggerElement(int elementIndex) {
        int i = sequence.length - 1;
        while (sequence[i] < sequence[elementIndex]) {
            i--;
        }

        return i;
    }


    private static void swapElements(int firstElementIndex, int secondElementIndex) {
        int tmp = sequence[firstElementIndex];
        sequence[firstElementIndex] = sequence[secondElementIndex];
        sequence[secondElementIndex] = tmp;
    }


    private static void fillSequence() {
        for (int i = 0; i < sequence.length; i++) {
            sequence[i] = i + 1;
        }
    }


    private static void reverseArrayPart(int left, int right) {
        int tmp;

        while (left < right) {
            if (sequence[left] > sequence[right]) {
                tmp = sequence[left];
                sequence[left] = sequence[right];
                sequence[right] = tmp;
            }

            left++;
            right--;
        }
    }


    private static String convertArrayToString() {
        StringBuilder result = new StringBuilder();

        for (int value : sequence) {
            result.append(value);
            result.append(" ");
        }

        return result.toString().trim();
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