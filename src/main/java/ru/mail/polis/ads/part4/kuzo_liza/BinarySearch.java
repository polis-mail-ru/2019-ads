package ru.mail.polis.ads.part4.kuzo_liza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class BinarySearch {

    public static void main(String[] args) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }

    public static void solve(FastScanner in, PrintWriter out){
        int n = in.nextInt();
        int q = in.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        for (int i = 0; i < q ; i++) {
            if (search(arr, 0, n - 1, in.nextInt()) == -1) {
                out.println("NO");
            }
            else {
                out.println("YES");
            }
        }
    }


    public static int search(int arr[], int firstElement, int lastElement, int elementToSearch) {

        if (lastElement >= firstElement) {
            int mid = firstElement + (lastElement - firstElement) / 2;

            if (arr[mid] == elementToSearch) {
                return mid;
            }

            if (arr[mid] > elementToSearch) {
                return search(arr, firstElement, mid - 1, elementToSearch);
            }

            return search(arr, mid + 1, lastElement, elementToSearch);
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

}
