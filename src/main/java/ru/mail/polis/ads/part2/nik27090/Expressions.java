package ru.mail.polis.ads.part2.nik27090;

import java.io.*;
import java.util.StringTokenizer;

// Задача: "Выражения"
//Решение: https://www.e-olymp.com/ru/submissions/5853889

public class Expressions {

    private Expressions() {
    // Should not be instantiated
}

    private static int index;
    private static void get_levels(char[] expr, int depth, StringBuilder[] result){
        result[depth].append(expr[index]);
        index--;
        if (Character.isUpperCase(expr[index+1])){
            get_levels(expr,depth+1,result);
            get_levels(expr,depth+1,result);
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int t = in.nextInt();
        String str;

        for (int i = 0; i < t; i++) {
            str = in.next();
            char[] expr = str.toCharArray();
            StringBuilder[] result = new StringBuilder[expr.length];
            for (int j = 0; j < expr.length; j++){
                result[j] = new StringBuilder("");
            }
            index = expr.length - 1;
            get_levels(expr,0, result);
            for (int k = expr.length-1; k >=0 ; k--) {
                out.print(result[k]);
            }
            out.println();
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