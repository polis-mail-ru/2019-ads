package ru.mail.polis.ads.part1.suhova;

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Task1090 {
    /*
    https://www.e-olymp.com/ru/submissions/5756855
     */
    static String[][] res;

    private static String packing(String str, String sub, int length, int l, int r) {
        for (int i = 1; i <= length / 2; i++) {
            int count = length / i;
            if (length % i == 0) {
                for (int j = l; j <= r - i; j++) {
                    if (str.charAt(j) != str.charAt(j + i)) {
                        count = 0;
                        break;
                    }
                }
            } else count = 0;
            if (count > 1) {
                String s = length / i + "(" + res[l][l + i - 1] + ")";
                if (s.length() < sub.length())
                    sub = s;
            }
        }
        return sub;
    }

    private static void solve(final Scanner in, final PrintWriter out) {
        String str = in.nextLine();
        int len = str.length();
        res = new String[len][len];
        for (int n = 1; n <= len; n++) {
            for (int l = 0; l <= len - n; l++) {
                int r = n + l - 1;
                String sub = str.substring(l, r + 1);
                if (n > 4) {
                    sub = packing(str, sub, n, l, r);
                    for (int part = l; part < r; part++) {
                        String newStr = res[l][part] + res[part + 1][r];
                        if (newStr.length() < sub.length()) {
                            sub = newStr;
                        }
                    }
                }
                res[l][r] = sub;
            }
        }
        out.println(res[0][len - 1]);
        out.flush();
    }

    public static void main(final String[] arg) {
        final Scanner in = new Scanner(System.in);
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
