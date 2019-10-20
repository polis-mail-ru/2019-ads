package ru.mail.polis.ads.part4.medalexey;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


/**
 *  Название задачи: Станция "Сортировочная"  https://www.e-olymp.com/ru/problems/1457
 *  Тестирование: https://www.e-olymp.com/ru/submissions/5907057
 */
public class SortingStation {

    private static int[] wagons;
    private static int maxLoadCapacity;


    private static void solve(final FastScanner in, final PrintWriter out) {
        int numberOfWagons = in.nextInt();
        maxLoadCapacity = in.nextInt();
        wagons = new int[numberOfWagons];

        fillWagons(in);

        out.println(
                splitAndCompare(0, wagons.length-1) ? "Yes" : "No"
        );
    }


    private static void fillWagons(final FastScanner in) {
        for (int i = 0; i < wagons.length; i++) {
            wagons[i] = in.nextInt();
        }
    }


    private static boolean splitAndCompare(int left, int right) {
        int median = left + (right - left)/2;

        if (left == right) {
            return true;
        }

        if (!splitAndCompare(left, median) || !splitAndCompare(median+1, right)) {
            return false;
        }

        return compare(left, median, right);
    }


    private static boolean compare(int left, int median, int right) {
        int leftPointer = left;
        int rightPointer = median+1;

        while (leftPointer <= median || rightPointer <= right) {
            if (leftPointer > median) {
                return true;
            } else if (rightPointer > right) {
                if (wagons[leftPointer] + wagons[rightPointer-1] > maxLoadCapacity) {
                    return false;
                }
                leftPointer++;
            } else if (wagons[rightPointer] < wagons[leftPointer]) {
                if (wagons[leftPointer] + wagons[rightPointer] > maxLoadCapacity) {
                    return false;
                }
                rightPointer++;
            } else {
                leftPointer++;
            }
        }

        return true;
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
