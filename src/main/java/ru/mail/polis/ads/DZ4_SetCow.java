package ru.mail.polis.ads;

import java.io.*;
import java.util.StringTokenizer;

public class DZ4_SetCow {
    static FastScanner scanner = new FastScanner(System.in);
    static int n = scanner.nextInt();
    static int k = scanner.nextInt();
    static long[] arr = new long[n];

    public static void main(String[] args) {
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        logic(arr);
    }

    private static void logic(long[] arr) {
        long l = 0;
        long r = Integer.MAX_VALUE;
        long mid = (l + r) / 2;
        while (r - l > 1) {
            mid = (l + r) / 2;
            if (may(mid)) {
                l = mid;
            } else {
                r = mid;
            }
        }
        System.out.println(mid);
    }

    private static boolean may(long mid) {
        int g = 1;
        int h = 1;
        for(int i = 2; i < n; i++){
            if(arr[i] - arr[g] >= mid){
                g = i;
                h++;
            }
        }
        if(h >= k){
            return true;
        }
        else{
            return false;
        }
    }


    static class FastScanner {
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
