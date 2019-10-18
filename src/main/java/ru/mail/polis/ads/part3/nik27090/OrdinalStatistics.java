package ru.mail.polis.ads.part3.nik27090;

import java.io.*;
import java.math.BigInteger;
import java.util.*;

//Задача: "k-тая порядковая статистика"
//Решение: https://www.e-olymp.com/ru/submissions/5893318

public class OrdinalStatistics {
    private OrdinalStatistics() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n= in.nextInt();
        String[] str=in.nextLine().split(" ");
        BigInteger[] arr = new BigInteger[str.length];
        for (int i = 0; i <str.length ; i++) {
            arr[i]= new BigInteger(str[i]);
        }
        Arrays.sort(arr);
        System.out.println(arr[arr.length-n]);
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
        String nextLine() {
            try {
                return reader.readLine();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return "";
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
