package ru.mail.polis.ads.valer1435.part3;

// https://www.e-olymp.com/ru/submissions/5833394
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem4741 {
    public static void main(String[] args){
        FastScanner in = new FastScanner();
        int res = 0;
        int cnt = in.nextInt();
        int[] arr = new int[cnt];
        for (int i = 0; i < cnt; i++) {
            arr[i] = in.nextInt();
            for(int j = 0; j < i; j++){
                if (arr[j] > arr[i]){
                    res+=1;
                }
            }
        }
        System.out.print(res);
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

    }
}
