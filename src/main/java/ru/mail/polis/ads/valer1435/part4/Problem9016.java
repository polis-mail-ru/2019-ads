package ru.mail.polis.ads.valer1435.part4;
//https://www.e-olymp.com/ru/submissions/5875826
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Problem9016 {
    public static void main(String[] args){
        PrintWriter out = new PrintWriter(System.out);
        FastScanner in = new FastScanner();
        int n = in.nextInt();
        int[] arr = new int[n];
        int q = in.nextInt();
        for (int i=0; i < n; i++){
            arr[i] = in.nextInt();
        }
        for (int i = 0; i < q; i++){
            int p = in.nextInt();
            out.println(isContains(arr, p, 0, n-1));
        }
        out.flush();
    }
    public static String isContains(int[] arr, int n, int l, int r) {
        if (l > r) {
            return "NO";
        }
        String res;
        int mid = l + (r - l) / 2;
        if (n > arr[mid]) {
            res = isContains(arr, n, mid+1, r);
        } else if (n < arr[mid]) {
            res = isContains(arr, n, l, mid-1);
        } else {
            return "YES";
        }
        return res;
    }
    private static class FastScanner {
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
