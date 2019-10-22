package ru.mail.polis.ads.part3.Kopeyka885;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task4827 {

    private static void solve(final FastScanner in, final PrintWriter out) throws IOException {
        Scanner sin = new Scanner(System.in);
        int k = Integer.parseInt(sin.nextLine());
        String[] students = sin.nextLine().trim().split(" ");
        Arrays.sort(students, (first, second) ->
                first.length() > second.length() ? 1 : first.length() < second.length() ? -1 : first.compareTo(second));

        out.print(students[students.length - k]);
        out.flush();
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

    public static void main(final String[] arg) throws IOException{
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}