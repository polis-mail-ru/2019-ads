package ru.mail.polis.ads.part4.nik27090;

import java.io.*;
import java.util.StringTokenizer;

//Задача: Бинарный поиск
//Решение: https://www.e-olymp.com/ru/submissions/5965163

public class BinarySearch {
    private BinarySearch() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int q =in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i]=in.nextInt();
        }
        int target;
        for (int i = 0; i <q ; i++) {
            target = in.nextInt();
            if (binSearch(arr,target)==-1)
                out.println("NO");
            else out.println("YES");
        }
    }

    private static int binSearch(int[] arr, int target){
        int low=0;
        int high=arr.length-1;
        int mid;
        while (low<=high){
            mid =(low+high)/2;
            if (target>arr[mid])
                low = mid+1;
            else if (target<arr[mid])
                high = mid-1;
            else return mid;
        }
        return -1;
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