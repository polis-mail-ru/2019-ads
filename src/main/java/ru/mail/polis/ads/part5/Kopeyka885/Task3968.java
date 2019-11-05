package ru.mail.polis.ads.part5.Kopeyka885;

import java.io.*;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public class Task3968{

    private static void solve(final FastScanner in, final PrintWriter out) {
        double c = in.nextDouble();
        double l = 0;
        double r = c;
        double x,y;
        do {
            x=(r+l)/2;
            y=function(x);
            if(y>c){
                r=x;
            } else{
                l=x;
            }
        } while(Math.abs(y-c)>=1e-6);
        System.out.println(x);
    }

    public static double function(double x){
        return x*x+Math.sqrt(x);
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

        double nextDouble(){
            return Double.parseDouble(next());
        }
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
} 