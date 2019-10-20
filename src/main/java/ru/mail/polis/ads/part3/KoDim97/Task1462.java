package ru.mail.polis.ads.part3.KoDim97;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * Problem solution template.
 */
public final class Task1462 {
    private Task1462() {
        // Should not be instantiated
    }
    static int Digit(int num, int index){
        num /= (Math.pow(10, (index - 1)));
        return (num % 10);
    }
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] arr = new int[n];
        ArrayList<Integer>[] busket= new ArrayList[10];
        for (int i = 0; i < 10; i++) {
            busket[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        for (int i = 0; i < n; i++) {
            busket[arr[i] % 10].add(arr[i]);
        }
        for (ArrayList<Integer> i :
                busket) {
            if(!i.isEmpty()) {
                Collections.sort(i);
            }
        }
        for (ArrayList<Integer> i :
                busket) {
            if(!i.isEmpty()){
                for (Integer j :
                        i) {
                    out.print(j);
                    out.print(' ');
                }
            }
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
