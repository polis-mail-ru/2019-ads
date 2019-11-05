package ru.mail.polis.ads.part5;

import java.io.*;

/**
 * Problem solution template.
 */
public final class Task2 {
    private Task2() {
        // Should not be instantiated
    }


    private static void solve(final BufferedReader in, final BufferedWriter out) throws IOException {
        String[] str = in.readLine().split(" ");
        long w = Integer.parseInt(str[0]);
        long h = Integer.parseInt(str[1]);
        long n = Integer.parseInt(str[2]);
        long left = Math.max(w, h) ;
        long right = Math.max(w, h) * n;
        do {
            long mid = (left + right) / 2;
            long v = (mid / w) * (mid / h);
            if (v >= n) {
                right = mid;
            } else {
                left = mid + 1;
            }
        } while (left < right);

        System.out.println(left);


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