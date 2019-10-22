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
public final class Task262{

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] stair = new int[n+2];
        stair[0] = 0;
        stair[n+1] = 0;
        for (int i = 1; i < n + 1; i++){
            stair[i] = in.nextInt();
        }
        int step = in.nextInt();

        if (step == 1){
            int sum = 0;
            for (int i = 1; i < n+1; i++){
                sum+=stair[i];
            }
            out.print(sum);
            return;
        }

        for (int i = 1; i < n+2; i++){
            stair[i]+=findMaxInSet(stair,i-step,i);
        }

        out.print(stair[n+1]);
        out.flush();

    }

    public static int findMaxInSet(int[] stair,int leftBorder, int rightBorder){
        if (leftBorder < 0 ){
            leftBorder = 0;
        }
        int max_element = stair[leftBorder];
        for (int i = leftBorder + 1; i < rightBorder; i++) {
            if (stair[i] > max_element)
                max_element = stair[i];
        }
        return max_element;
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