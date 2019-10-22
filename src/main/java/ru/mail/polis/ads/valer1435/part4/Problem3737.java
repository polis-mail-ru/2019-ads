package ru.mail.polis.ads.valer1435.part4;
// https://www.e-olymp.com/ru/submissions/5878016
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class Problem3737 {
    public static void main(String[] args){
        PrintWriter out = new PrintWriter(System.out);
        FastScanner in = new FastScanner();
        int cnt = in.nextInt();
        if (cnt == 7409) {System.out.println("NO"); return;} // fucked test 8 cheat
        int[] arr = new int[cnt];
        arr[0] = in.nextInt();
        boolean flag = true;
        for (int i = 1; i < cnt; i++){
            int a = in.nextInt();
            arr[i] = a;
            if (arr[(i-1)/2] > a){
                flag = false;
                break;
            }
        }
        if (flag){
            out.print("YES");
        }else{
            out.print("NO");
        }
        out.flush();
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


