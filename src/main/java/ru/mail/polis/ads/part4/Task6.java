package ru.mail.polis.ads.part4;

import java.io.*;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task6 {
    private Task6() {
        // Should not be instantiated
    }

    private static String BinarySearch(int[] arr, int k) {
        int mid = 0;
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            mid = (right + left) / 2;
            if (k == arr[mid]) {
                break;
            } else if (k > arr[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        if (arr[mid] == k) return "YES";
        else return "NO";
    }


    private static void solve(final BufferedReader in, final BufferedWriter out) throws IOException {
        String[] str = in.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int q = Integer.parseInt(str[1]);
        int[] arr = new int[n];
        StringTokenizer tokenizer = new StringTokenizer(in.readLine());

        for (int i = 0; i < n; ++i) {
            arr[i] = Integer.parseInt(tokenizer.nextToken());
        }

        for (int i = 0; i < q; ++i) {
            out.write(BinarySearch(arr, Integer.parseInt(in.readLine())) + "\n");
        }
    }


    public static void main(final String[] arg) {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out))) {
            solve(in, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}