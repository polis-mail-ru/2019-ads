package ru.mail.polis.ads.part4.kokobox7;

import java.io.*;
import java.util.StringTokenizer;

public class Trains {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int M = in.nextInt();
        int[] trains = new int[n];
        for (int i = 0; i < n; i++) {
            trains[i] = Integer.parseInt(in.next());
        }
        //bubble sort is too slow
        /*modifiedBubbleSort(trains, M);
        out.println(checkIfSorted(trains) ? "Yes" : "No");*/
        out.println(check(n, M, trains) ? "Yes" : "No");
    }

    private static boolean check(int n, int m, int[] trains) {
        /*For this task its enough to check if there is a pai of elements e[i], e2[j], i < j
        such that e[i] + e[j] > M*/
        /*It works because these two elements will occasionally be compared
        and if e[i] + e[j] > M they cannot be swapped with each other -> array will not be sorted.*/
        int max = -1;
        for (int i = 0; i < n; i++) {
            if (trains[i] < max && trains[i] + max > m) return false;
            max = Math.max(trains[i], max);
        }
        return true;
    }

    private static void modifiedBubbleSort(long[] array, int M) {
        for (int iter = 0; iter < (array.length / 2) + 1; iter++) {
            for (int i = 0; i < array.length - 1; i++) {
                if (array[i + 1] < array[i] && (array[i] + array[i + 1]) <= M) {
                    swap(array, i + 1, i);
                }
            }
            for (int i = array.length - 1; i > 1; i--) {
                if (array[i] < array[i - 1] && (array[i] + array[i - 1]) <= M) {
                    swap(array, i + 1, i);
                }
            }
        }
    }

    private static void swap(long[] array, int index1, int index2) {
        final long tmp = array[index2];
        array[index2] = array[index1];
        array[index1] = tmp;
    }

    private static boolean checkIfSorted(long[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i + 1] < array[i]) return false;
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
