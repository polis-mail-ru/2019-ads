package ru.mail.polis.ads.part2.kuzo_liza;

import java.io.*;
import java.util.StringTokenizer;

public class Expressions {

    private Expressions() {
    }

    static int index;
    private static void calculation(char[] charArray, int depth, StringBuffer[] buffer){
        buffer[depth].append(charArray[index]);
        index--;
        if (Character.isUpperCase(charArray[index+1])){
            calculation(charArray,depth+1,buffer);
            calculation(charArray,depth+1,buffer);
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            String str = in.next();
            char[] charArray = str.toCharArray();
            StringBuffer[] buffer = new StringBuffer[charArray.length];
            for (int j = 0; j < charArray.length; j++){
                buffer[j] = new StringBuffer("");
            }
            index = charArray.length - 1;
            calculation(charArray,0, buffer);
            for (int k = charArray.length-1; k >=0 ; k--) {
                out.print(buffer[k]);
            }
            out.println();
        }
    }

    public static void main(final String[] args) {
        final FastScanner in = new FastScanner(System.in);
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