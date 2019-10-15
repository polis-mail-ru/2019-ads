package ru.mail.polis.ads.part2.Kondrat04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Brackets {
    private static void solve(final Scanner in, final PrintWriter out) {
        String startSequence = in.nextLine();
        int counter = 0;
        for (int i = 0; i < startSequence.length(); i++){
            if (startSequence.charAt(i) == '(')
                counter++;
            else
                counter--;
            if (counter < 0){
                break;
            }
        }
        if (counter == 0)
            out.write("YES");
        else
            out.write("NO");
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
        final Scanner in = new Scanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
