package ru.mail.polis.ads.part4.gogun;

import java.io.*;
import java.util.StringTokenizer;

public class Task6 {
    static int[] array;

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStreamReader in) {
            reader = new BufferedReader(new InputStreamReader(System.in));
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

    private static String binSearch (int key) {
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (array[mid] == key)
                return "YES";
            if (array[mid] < key)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return "NO";
    }


    private static void solve(FastScanner input, PrintWriter output) {


        int n = input.nextInt();
        int q = input.nextInt();

        array = new int[n];

        for (int i = 0; i < n; ++i) {
            array[i] = input.nextInt();
        }

        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < q; ++j) {
            sb.append(binSearch(input.nextInt()));
            sb.append("\n");
        }

        output.println(sb);


    }

    public static void main(String[] args) {
        FastScanner in = new FastScanner(new InputStreamReader(System.in));
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out))) {
            solve(in, out);
        }
    }
}
