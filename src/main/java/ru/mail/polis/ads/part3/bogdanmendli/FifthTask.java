package ru.mail.polis.ads.part3.bogdanmendli;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class FifthTask {

    private static int[][] array;

    private static void solve() throws IOException {
        final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        final int n = Integer.parseInt(in.readLine());
        array = new int[n][2];
        for (int i = 0; i < n; i++) {
            String[] temp  = in.readLine().split(" ");
            array[i][0] = Integer.parseInt(temp[0]);
            array[i][1] = Integer.parseInt(temp[1]);
        }

        sort(0, n - 1);


        PrintWriter out =  new PrintWriter(new BufferedOutputStream(System.out));
        for (int[] arr : array) {
            out.println(arr[0] + " " + arr[1]);
        }
        out.close();
        in.close();
    }

    private static void sort(final int start, final int end) {
        if (end <= start) {
            return;
        }
        int mid = start + (end - start) / 2;
        sort(start, mid);
        sort(mid + 1, end);

        final int n = end - start;
        int[][] temp = new int[n + 1][2];
        for (int i = 0; i <= n; i++) {
            temp[i] = array[i + start];
        }

        int leftArray = 0;
        int rightArray = n / 2 + 1;

        for (int i = start; i <= end; i++) {

            if (leftArray > n / 2) {
                array[i] = temp[rightArray++];
            } else if (rightArray > n) {
                array[i] = temp[leftArray++];
            } else if (temp[leftArray][0] > temp[rightArray][0]) {
                array[i] = temp[rightArray++];
            } else {
                array[i] = temp[leftArray++];
            }
        }
    }

    public static void main(String[] args) {
        try {
            solve();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
