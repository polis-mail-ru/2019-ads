package ru.mail.polis.ads.part2.Kopeyka885;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task15{

    private static void solve(final FastScanner in, final PrintWriter out) {
        int height = in.nextInt();
        int width = in.nextInt();

        if (height == 1 && width == 1){
            out.println();
            return;
        }

        int[][] table= new int[height][width];

        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){
                table[i][j] = in.nextInt();
            }
        }

        for (int i = height - 2; i >=0 ; i--){
            table[i][0] += table[i+1][0];
        }
        for (int i = 1; i < width; i++){
            table[height-1][i] += table[height-1][i-1];
        }


        for (int i = height - 2; i >= 0; i--) {
            for (int j = 1; j < width; j++) {
                table[i][j] = Math.max(table[i][j-1], table[i+1][j]) + table[i][j];
            }
        }

        Stack<Character> wayStack = new Stack<>();
        int i = 0;
        int j = width - 1;
        while (i != height -1 && j!= 0){
            if (table[i][j-1] > table[i+1][j]){
                wayStack.push('R');
                j--;
            }
            else{
                wayStack.push('F');
                i++;
            }
        }
        if (i != height-1)
            for ( int k = i; k < height -1; k++){
                wayStack.push('F');
            }
        else if ( j != 0)
            for (int k = j; k > 0; k--) {
                wayStack.push('R');
            }

        for (int k = 0; k < width + height -2 ; k++){
            out.print(wayStack.pop());
            out.flush();
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