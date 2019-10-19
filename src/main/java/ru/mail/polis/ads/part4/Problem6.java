package ru.mail.polis.ads.part4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Problem6 {

    private static int[] arr;

    private static void solve() throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))
        ) {
            String[] tokens = reader.readLine().split(" ");
            int n = Integer.parseInt(tokens[0]);
            int q = Integer.parseInt(tokens[1]);
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

            arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(tokenizer.nextToken());
            }

            for (int i = 0; i < q; i++) {
                if (isPresent(Integer.parseInt(reader.readLine()))) {
                    writer.write("YES\n");
                } else {
                    writer.write("NO\n");
                }
            }
        }
    }

    private static boolean isPresent(int elem) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == elem) {
                return true;
            } else if (elem < arr[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        try {
            solve();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
