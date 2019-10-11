package ru.mail.polis.ads.part3.suhova;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Task4827 {
    /*
     Task 4: https://www.e-olymp.com/ru/submissions/5833654
     */

    private static void solve(final Task4827.FastScanner in, final PrintWriter out) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int k = Integer.parseInt(scanner.nextLine());
        String[] a = scanner.nextLine().trim().split(" ");
        Arrays.sort(a, (x, y) -> x.length() > y.length() ? 1 : x.length() < y.length() ? -1 : x.compareTo(y));
        out.print(a[a.length - k]);
        out.flush();
    }


    public static void main(final String[] arg) throws IOException {
        final Task4827.FastScanner in = new Task4827.FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
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
