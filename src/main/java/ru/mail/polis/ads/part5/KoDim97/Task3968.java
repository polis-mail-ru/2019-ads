package ru.mail.polis.ads.part5.KoDim97;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task3968 {
    private Task3968() {
        // Should not be instantiated
    }
    static double function(double x){
        return (x*x + Math.sqrt(x));
    }
    private static void solve(final FastScanner in, final PrintWriter out) {
        Scanner scanner = new Scanner(System.in);
        double c = scanner.nextDouble();
        double l = 0;
        double r = c;
        double x,y;
        do{
            x = (l + r)/2;
            y = function(x);
            if (y > c){
                r = x;
            }
            else{
                l = x;
            }
        }while(Math.abs(y - c) > 1.e6);
        out.print(String.format("%.6f", x));
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
