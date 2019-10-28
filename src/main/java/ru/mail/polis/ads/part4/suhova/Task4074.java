package ru.mail.polis.ads.part4.suhova;

import java.io.*;

public class Task4074 {
    /*
    Task 3: https://www.e-olymp.com/ru/submissions/5968561
     */

    private static long[] maxHeap;
    private static int sizeMax;
    private static long[] minHeap;
    private static int sizeMin;

    private static void solve() throws IOException {
        maxHeap = new long[500001];
        minHeap = new long[500001];
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        long mid = 0;
        boolean f = false;
        String s;
        while ((s = in.readLine()) != null) {
            long x = Long.parseLong(s);
            if (!f) {
                f = true;
                if (x < maxHeap[1]) {
                    insertInMax(x);
                    mid = extractFromMax();
                } else if (x > minHeap[1]) {
                    insertInMin(x);
                    mid = extractFromMin();
                } else mid = x;
            } else {
                f = false;
                if (x < mid) {
                    insertInMin(mid);
                    insertInMax(x);
                } else {
                    insertInMin(x);
                    insertInMax(mid);
                }
                mid = (maxHeap[1] + minHeap[1]) / 2;
            }
            out.println(mid);
        }
        out.close();
        in.close();
    }

    private static void insertInMax(long x) {
        maxHeap[++sizeMax] = x;
        int k = sizeMax;
        while (k > 1 && maxHeap[k] > maxHeap[k / 2]) {
            int par = k / 2;
            long temp = maxHeap[k];
            maxHeap[k] = maxHeap[par];
            maxHeap[par] = temp;
            k = par;
        }
    }

    private static long extractFromMax() {
        long res = maxHeap[1];
        maxHeap[1] = maxHeap[sizeMax--];
        int k = 1;
        while (2 * k <= sizeMax) {
            int child = 2 * k;
            if (child < sizeMax && maxHeap[child] < maxHeap[child + 1]) child++;
            if (maxHeap[k] >= maxHeap[child]) break;
            long temp = maxHeap[k];
            maxHeap[k] = maxHeap[child];
            maxHeap[child] = temp;
            k = child;
        }
        return res;
    }

    private static void insertInMin(long x) {
        minHeap[++sizeMin] = x;
        int k = sizeMin;
        while (k > 1 && minHeap[k] < minHeap[k / 2]) {
            int par = k / 2;
            long temp = minHeap[k];
            minHeap[k] = minHeap[par];
            minHeap[par] = temp;
            k = par;
        }
    }

    private static long extractFromMin() {
        long res = minHeap[1];
        minHeap[1] = minHeap[sizeMin--];
        int k = 1;
        while (2 * k <= sizeMin) {
            int child = 2 * k;
            if (child < sizeMin && minHeap[child] > minHeap[child + 1]) child++;
            if (minHeap[k] <= minHeap[child]) break;
            long temp = minHeap[k];
            minHeap[k] = minHeap[child];
            minHeap[child] = temp;
            k = child;
        }
        return res;
    }

    public static void main(final String[] arg) throws IOException {
        solve();
    }
}