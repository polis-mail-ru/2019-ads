package ru.mail.polis.ads.part1.makaryb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Modified by БорискинМА
 * 27.09.19
 * gr. Java-10, Технополис
 * IntelliJ IDEA Ultimate 2019.2, for educational use only
 * e-olymp: https://www.e-olymp.com/ru/submissions/5712136
 */
public final class FirstTask {
    private FirstTask() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Scanner input = new Scanner(System.in);
        PrintWriter output = new PrintWriter(System.out);

        int total = input.nextInt();
        int digit1,digit2;

        digit1=total/10;
        digit2=total%10;
        output.println(digit1 +" " + digit2);

        output.flush();
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
