package ru.mail.polis.ads.valer1435.part2;


// https://www.e-olymp.com/ru/submissions/5788121
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem262 {
    public static void main(String[] args) {
        FastScanner in = new FastScanner();
        int n = in.nextInt();
        int[] arr = new int[n+2];
        for (int i = 1; i < n+1; i++) {
            arr[i] = in.nextInt();
        }
        int k = in.nextInt();

        int[] d = new int[n+2];
        d[0] = 0;
        for (int i = 1; i < arr.length; i++){
            int maxValue = -999999;
            for (int j = 1; j <= k; j++){


                if (i-j >= 0){
                    if (d[i-j]+arr[i] > maxValue){
                        maxValue = d[i-j]+arr[i];
                    }
                }
            }
            d[i] = maxValue;
        }
        System.out.println(d[n+1]);
    }
    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        public FastScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }
    }

}







