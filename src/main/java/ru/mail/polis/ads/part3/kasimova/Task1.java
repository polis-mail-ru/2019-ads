package ru.mail.polis.ads.part3.kasimova;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;


public class Task1 {

    private void solve() {
        int n = nextInt();
        int[] arr = nextIntArray(n);
        shuffle(arr);
        quickSort(arr, 0, n-1);
        for (int i = 0; i < n; i ++){
            out.print(arr[i] + " ");
        }

    }

    private void quickSort(int[] arr, int left, int right){
        if (left < right){
            int q = partition(arr, left, right);
            quickSort(arr, left, q);
            quickSort(arr, q + 1, right);
        }

    }

    private int partition(int[] arr, int l, int r) {
        int value = arr[(l + r) / 2];
        int i = l;
        int j = r;
        while (i <= j){
            while (arr[i] < value)
                i++;
            while (arr[j] > value)
                j--;
            if (i >= j) {
                break;
            }
            int temp = arr[i];
            arr[i++] = arr[j];
            arr[j--] = temp;
        }
        return j;
    }
    private static Random random;

    private void shuffle(int[] array) {
        if (random == null) random = new Random();
        int count = array.length;
        for (int i = count; i > 1; i--) {
            int j = random.nextInt(i);
            int temp = array[i-1];
            array[i-1] = array[j];
            array[j] = temp;

        }
    }

    public static void main(String[] args) {
        new Task1().run();
    }

    private void init() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    private void run() {
        try {
            long timeStart = System.currentTimeMillis();

            init();
            solve();

            out.close();

            long timeEnd = System.currentTimeMillis();
            /*System.err.println("Time = " + (timeEnd - timeStart));*/
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    /**
     * FAST_SCANNER
     */

    private BufferedReader in;
    private PrintWriter out;
    private StringTokenizer st;

    String nextLine() {
        try {
            return in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String delimiter = " ";

    private String next() {
        while (st == null || !st.hasMoreTokens()) {
            try {
                st = new StringTokenizer(in.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return st.nextToken(delimiter);
    }

    private int nextInt() {
        return Integer.parseInt(next());
    }

    long nextLong() {
        return Long.parseLong(next());
    }

    private int[] nextIntArray(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = nextInt();
        }
        return a;
    }

    int[][] nextIntMatrix(int n, int m) {
        int[][] a = new int[n][m];
        for (int i = 0; i < n; i++) {
            a[i] = nextIntArray(m);
        }
        return a;
    }

    int getMaxArray(int[] a) {
        int max = Integer.MIN_VALUE;
        for (int x : a) {
            max = Math.max(max, x);
        }
        return max;
    }

    private void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }
}
