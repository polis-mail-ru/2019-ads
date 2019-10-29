package ru.mail.polis.ads.part4.Kopeyka885;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task9016 {

    public static int binarySearch(int arr[], int elementToSearch) {
        int firstIndex = 0;
        int lastIndex = arr.length - 1;
        while(firstIndex <= lastIndex) {
            int middleIndex = (firstIndex + lastIndex) / 2;
            if (arr[middleIndex] == elementToSearch) {
                return middleIndex;
            }
            else if (arr[middleIndex] < elementToSearch)
                firstIndex = middleIndex + 1;
            else if (arr[middleIndex] > elementToSearch)
                lastIndex = middleIndex - 1;
        }
        return -1;
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int arr_size = in.nextInt();
        int checks = in.nextInt();
        int[] arr = new int[arr_size];
        for (int i = 0; i < arr_size; i++){
            arr[i] = in.nextInt();
        }
        for (int i = 0; i < checks; i++){
            if (binarySearch(arr,in.nextInt()) == -1){
                out.println("NO");
            }
            else{
                out.println("YES");
            }
        }
        out.flush();
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