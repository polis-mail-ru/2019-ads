package ru.mail.polis.ads.task3.stasmilke;

import java.io.*;
import java.util.Arrays;

public class Task4827 {
    private Task4827() {
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int k = Integer.parseInt(in.nextLine());
        String[] array = Arrays.stream(in.nextLine()
                .split(" "))
                .filter((String str) -> !str.isEmpty())
                .sorted((String a, String b) ->
                        (a.length() > b.length()) ? 1 : (a.length() < b.length()) ? -1 : a.compareTo(b))
                .toArray(String[]::new);
        out.println(array[array.length - k]);
    }

    private static class FastScanner {
        private final BufferedReader reader;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
        }

        String nextLine() {
            try {
                return reader.readLine();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return "";
        }
    }

    public static void main(final String[] args) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}