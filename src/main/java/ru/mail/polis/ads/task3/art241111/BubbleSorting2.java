package ru.mail.polis.ads.task3.art241111;

import java.io.*;
import java.util.StringTokenizer;

/**
 Link to result: https://www.e-olymp.com/ru/submissions/5834114
 */

public class BubbleSorting2 {
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

    private static void solve(final FastScanner in, final PrintWriter out) {
        int countN = in.nextInt();
        int[] arrayForSort = new int[countN];

        for (int i = 0; i < countN; i++) {
            arrayForSort[i] = in.nextInt();
        }

        out.print(bubbleSort(arrayForSort));
        out.flush();
    }

    private static int bubbleSort(int[] numbers) {
        int num = 0;
        for (int i = 0; i < numbers.length; i++) {
            for (int j = numbers.length -1; j > i; j--) {
                if (numbers[j] < numbers[j - 1]) {
                    swap(numbers, j, j-1);
                    num++;
                }
            }
        }
        return num;
    }

    private static void swap(int[] array, int from, int to){
        int temp = array[from];
        array[from] = array[to];
        array[to] = temp;
    }


    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }

}
