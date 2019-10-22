package ru.mail.polis.ads.part3.nik27090;

import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.StringTokenizer;

//задача: k-тая порядковая статистика
//Решение:https://www.e-olymp.com/ru/submissions/5923097

public class OrdinalStatistics {

    private OrdinalStatistics() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int k= in.nextInt();
        String[] str=in.nextLine().split(" ");
        ArrayList<BigInteger> arr = new ArrayList<>();
        for (int i = 0; i <str.length ; i++) {
            arr.add(new BigInteger(str[i]));
        }
        k=arr.size()-k;
        System.out.println(quickSelect(arr,k));
    }

    public static BigInteger quickSelect(ArrayList<BigInteger> arr, int k){
        if (arr.size()==1){
            return arr.get(0);
        }
        int pivot= 0 + (int)(Math.random()*arr.size());
        ArrayList<BigInteger> lows =new ArrayList<>();
        ArrayList<BigInteger> highs = new ArrayList<>();
        ArrayList<BigInteger> pivots = new ArrayList<>();

        for (int i = 0; i <arr.size() ; i++) {
            if (0>arr.get(i).compareTo(arr.get(pivot))){
                lows.add(arr.get(i));
            } else if (0<arr.get(i).compareTo(arr.get(pivot))){
                highs.add(arr.get(i));
            } else {
                pivots.add(arr.get(i));
            }
        }

        if (k<lows.size()){
            return quickSelect(lows, k);
        } else if (k<lows.size()+pivots.size()){
            return pivots.get(0);
        } else {
            return quickSelect(highs,k-lows.size()-pivots.size());
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