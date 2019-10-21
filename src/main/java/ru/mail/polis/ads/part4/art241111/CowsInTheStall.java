package ru.mail.polis.ads.part4.art241111;

import java.io.*;
import java.util.StringTokenizer;

/**
 * Link is https://www.e-olymp.com/ru/submissions/5914780
 */
public class CowsInTheStall {
    private static long n = 0;
    private static long ind = 1;
    private static long ans = 0;
    private static long k = 0;
    private static long[] a = new long[(int) (1e5 * 2 + 1)];

    private static boolean chec(long x){
        for(int i = 2; i <= n; i++){
            if(a[i] - a[(int) ind] >= x){
                ind = i;
                ans ++;
            }
        }
        return ans >= k;
    }

    private static void solve(FastScanner in, final PrintWriter out) {
        n = in.nextInt();
        k = in.nextInt();

        for (int i = 1; i <= n; i++) {
            a[i] = in.nextInt();
        }

        long l = 0;
        long r = (int) (1e9 + 7);

        while(r - l > 1){
            long mid = (l + r) / 2;
            ind = 1;
            ans = 1;
            if(chec(mid)){
                l = mid;
            }
            else{
                r = mid;
            }
        }

        System.out.println(l);
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
            return Integer.parseInt(next().trim());
        }
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
