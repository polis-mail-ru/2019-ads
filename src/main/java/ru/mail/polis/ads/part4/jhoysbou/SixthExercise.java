package ru.mail.polis.ads.part4.jhoysbou;

import java.io.*;
import java.util.StringTokenizer;

// Submission here https://www.e-olymp.com/ru/submissions/5894372

public class SixthExercise {

    public static void main(String[] args) {
        final FastScanner scanner = new FastScanner(System.in);
        final PrintWriter out = new PrintWriter(System.out);
        final int length = scanner.nextInt();
        int[] array = new int[length];
        final int numberOfRequests = scanner.nextInt();

        for (int i = 0; i < length; i++) {
            array[i] = scanner.nextInt();
        }

        for (int i = 0; i < numberOfRequests; i++) {
            int element = scanner.nextInt();
            if (contains(array, element)) {
                out.println("YES");
            } else {
                out.println("NO");
            }
        }

        out.flush();

    }

    private static boolean contains(int[] array, int element) {
        int high = array.length - 1;
        int low = 0;
        int pivot = (high + low)/2;

        while (high >= low) {
            pivot = (high + low)/2;
            if (array[pivot] > element) {
                high = pivot - 1;
            } else if (array[pivot] < element) {
                low = pivot + 1;
            } else {
                break;
            }
        }

        if (array[pivot] == element) return true;
        else return false;
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

}
