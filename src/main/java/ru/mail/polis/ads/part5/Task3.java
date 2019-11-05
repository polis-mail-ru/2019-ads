package ru.mail.polis.ads.part5;

import java.io.*;

/**
 * Problem solution template.
 */
public final class Task3 {
    private Task3() {
        // Should not be instantiated
    }


    private static void solve(final BufferedReader in, final BufferedWriter out) throws IOException {
        int n = Integer.parseInt(in.readLine());
        long[] arr = new long[n];
        String[] str = in.readLine().split(" ");
        for (int i = 0; i < n; ++i) {
            arr[i] = Long.parseLong(str[i]);
        }

        int[] d = new int[n];
        d[0] = 1;
        int ans = 0;

        for(int i = 1; i < n; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (arr[j] != 0 && arr[i] % arr[j] == 0 && d[j] > max) {
                    max = d[j];
                }
                d[i] = max + 1;
                if (d[i] > ans) {
                    ans = d[i];
                }
            }
        }

        System.out.println(ans);
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