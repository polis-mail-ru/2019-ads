package ru.mail.polis.ads.part2.Kopeyka885;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task3837{

    public static void get_levels(String Expr, Index index, int depth, StringBuilder[] Answer) {
        Answer[depth].append(Expr.charAt(index.index));
        index.index--;
        if (Character.isUpperCase(Expr.charAt(index.index + 1))) {
            get_levels(Expr, index, depth + 1, Answer);
            get_levels(Expr, index, depth + 1, Answer);
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        String str;

        for (int k = 0; k < n; k++) {
            str = in.next();
            int str_lenght = str.length();
            StringBuilder[] Answer = new StringBuilder[str_lenght];
            for (int i = 0; i < str_lenght; i++){
                Answer[i] = new StringBuilder("");
            }
            //Arrays.fill(Answer, "");
            Index index = new Index(str_lenght - 1);
            get_levels(str, index, 0, Answer);
            for (int j = str_lenght-1; j >= 0; j--)
                System.out.print(Answer[j]);
            System.out.println();
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

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}

class Index {
    int index;
    public Index(int index){
        this.index = index;
    }
}