package ru.mail.polis.ads.valer1435.part4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Problem5149 {
    public static void main(String[] args){
        PrintWriter out = new PrintWriter(System.out);
        FastScanner in = new FastScanner();
        int n = in.nextInt();
        int k = in.nextInt();
        ArrayList<Integer> arr = new ArrayList<>();

        ArrayList<Integer> len = new ArrayList<>();
        for (int i = 0; i < n; i++){
            arr.add(in.nextInt());
        }
//        ArrayList<Integer> arrCopy = (ArrayList<Integer>) arr.clone();

        len.add(arr.get(0));
        len.add(arr.get(arr.size()-1));
        if (arr.size() == 2){
            out.println(arr.get(arr.size()-1)-arr.get(0));
            out.flush();
            return;
        }
        len.add(arr.get(0));
        len.add(arr.get(arr.size()-1));
        int m = arr.get(arr.size()-1)-arr.get(0);
        int min1 = 9999999;
        int max1 = 0;
        int min2 = 0;
        int max2 = 0;
        int min3 = 0;
        int max3 = 0;
        int val = 0;
        for (int i=2; i < k; i++){
            val = 0;
            max2 = 0;
            min2 = 0;

            for (int j = 1; j < arr.size()-1; j++){


                if (!len.contains(arr.get(j))){
                    min1 = 9999999;
                    max1 = 0;
                    for (int u:len) {
                        if (Math.abs(u-arr.get(j)) < min1){
                            min1 = Math.abs(u-arr.get(j));
                        }
                        if (Math.abs(u-arr.get(j)) > max1){
                            max1 = Math.abs(u-arr.get(j));
                        }
                    }
                    //System.out.println(min1);
                    if (min2 < min1){
                        max2 = max1;
                        min2 = min1;
                        val = arr.get(j);
                    }

                }


            }
            len.add(val);


        }
        out.println(min2);
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
