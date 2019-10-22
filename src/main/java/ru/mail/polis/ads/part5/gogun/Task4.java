package ru.mail.polis.ads.part5.gogun;

import java.io.*;

public class Task4 {
    static boolean[][] d;

    private static void solve(BufferedReader input, PrintWriter output) {
        try {
            String s = input.readLine();
            String p, w;
            if (s.contains("*") || s.contains("?")) {
                p = s;
                w = input.readLine();
            } else {
                w = s;
                p = input.readLine();
            }

            d = new boolean[w.length() + 1][p.length() + 1];
            d[0][0] = true;

            for (int i = 1; i <= w.length(); i++) {
                for (int j = 1; j <= p.length(); j++) {
                    if (w.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
                        d[i][j] = d[i-1][j-1];
                    } else if (p.charAt(j - 1) == '*') {
                        d[i][j] = d[i-1][j] || d[i-1][j-1] || d[i][j - 1];
                    } else {
                        d[i][j] = false;
                    }
                }
            }

            if (d[w.length()][p.length()]) {
                output.println("YES");
            } else {
                output.println("NO");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out))) {
            solve(in, out);
        }
    }
}
