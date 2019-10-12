package ru.mail.polis.ads.part1.KoDim97;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

public final class Task1087 {
    private Task1087() {
        // Should not be instantiated
    }
    private static void solve(final FastScanner in, final PrintWriter out) {
        Scanner scan = new Scanner(System.in);
        String s = scan.nextLine();
        if (s.isEmpty()){
            out.println();
            return;
        }
        int n = s.length();
        int[][] d = new int[n][n];
        int[][] split = new int[n][n];
        for (int j = 0; j < n; j++){
            for (int i = j; i >= 0 ; i--) {
                if(i == j){
                    d[i][j] =  1;
                    continue;
                }
                int min = Integer.MAX_VALUE;
                int splitMin = -1;
                if (s.charAt(i) == '(' && s.charAt(j) == ')' ||
                        s.charAt(i) == '[' && s.charAt(j) == ']'){
                    min = d[i+1][j-1];
                }
                for (int k = i; k < j; k++){
                    if (d[i][k] + d[k+1][j] < min){
                        min = d[i][k] + d[k+1][j];
                        splitMin = k;
                    }
                }
                d[i][j] = min;
                split[i][j] = splitMin;
            }
        }
        Restore(in, out, 0, n-1, d, split, s);
    }
    static void Restore(final FastScanner in, final PrintWriter out, int i, int j, int[][] d, int[][]split, String s){
        if (i == j){
            switch (s.charAt(i)){
                case '(':
                case ')':
                    out.print("()");
                    break;
                case '[':
                case ']':
                    out.print("[]");
                    break;
            }
            return;
        }
        if (d[i][j] == 0){
            out.print(s.substring(i, j+1));
            return;
        }
        if (split[i][j] == -1){
            out.print(s.charAt(i));
            Restore(in, out, i+1, j-1, d, split, s);
            out.print(s.charAt(j));
            return;
        }
        int k = split[i][j];
        Restore(in, out, i,  k, d, split, s);
        Restore(in, out, k+1, j, d, split, s);
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