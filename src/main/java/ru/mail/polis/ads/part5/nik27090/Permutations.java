package ru.mail.polis.ads.part5.nik27090;

import java.io.*;
import java.util.StringTokenizer;

//Задача: перестановки
//Реение: https://www.e-olymp.com/ru/submissions/5965542

public class Permutations {
    private Permutations() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i]=i+1;
        }
        print(arr, out);

        while (nextPermut(arr)){
            print(arr,out);
        }
    }

    public static void print(int[] arr, PrintWriter out){
        for (int num:arr){
            out.print(num+" ");
        }
        out.println();
    }

    public static boolean nextPermut(int[] arr){
        int n =arr.length;
        int j = n-2;
        while (j != -1 && arr[j] >= arr[j+1]) j--;
        if (j==-1)
            return false;
        int k =n-1;
        while (arr[j]>=arr[k]) k--;
        swap(arr,j,k);
        int l=j+1,r=n-1;
        while (l<r)
            swap(arr,l++,r--);
        return true;
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
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

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}