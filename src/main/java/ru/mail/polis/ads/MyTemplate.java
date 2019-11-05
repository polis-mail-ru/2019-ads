package ru.mail.polis.ads;

import java.io.*;

/**
 * Problem solution template.
 */
public final class MyTemplate {
    private MyTemplate() {
        // Should not be instantiated
    }


    private static void solve(final BufferedReader in, final BufferedWriter out) throws IOException {
        int n = Integer.parseInt(in.readLine());
        int[] arr = new int[n];
        String[] str = in.readLine().split(" ");
        for (int i = 0; i < n; ++i) {
            arr[i] = Integer.parseInt(str[i]);
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