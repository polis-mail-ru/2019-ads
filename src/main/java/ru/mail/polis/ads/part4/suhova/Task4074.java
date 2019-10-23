package ru.mail.polis.ads.part4.suhova;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Task4074 {
    /*
    Task 3: Пока на 70%, но до дедлайна постараюсь найти, в чём бага
     */

    private static long[] maxHeap;
    private static int sizeMax;
    private static long[] minHeap;
    private static int sizeMin;

    private static void solve() throws IOException {
        maxHeap = new long[500000];
        minHeap = new long[500000];
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        long mid = Long.parseLong(in.readLine());
        out.println(mid);
        boolean f = true;
        String s;
        while ((s = in.readLine()) != null) {
            long x = Long.parseLong(s);
            if (!f) {
                f = true;
                if (x < maxHeap[0]) {
                    insertInMax(x);
                    mid = extractFromMax();
                } else if (x > minHeap[0]) {
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
                mid = (maxHeap[0] + minHeap[0]) / 2;
            }
            out.println(mid);
        }
        out.close();
        in.close();
    }

    private static void insertInMax(long x) {
        maxHeap[sizeMax++] = x;
        int k = sizeMax - 1;
        while (k > 0 && maxHeap[k] > maxHeap[(k - 1) / 2]) {
            int par = (k - 1) / 2;
            long temp = maxHeap[k];
            maxHeap[k] = maxHeap[par];
            maxHeap[par] = temp;
            k = par;
        }
    }

    private static long extractFromMax() {
        long res = maxHeap[0];
        maxHeap[0] = maxHeap[--sizeMax];
        int k = 0;
        while (2 * k + 1 < sizeMax) {
            int child = 2 * k + 1;
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
        minHeap[sizeMin++] = x;
        int k = sizeMin - 1;
        while (k > 0 && minHeap[k] < minHeap[(k - 1) / 2]) {
            int par = (k - 1) / 2;
            long temp = minHeap[k];
            minHeap[k] = minHeap[par];
            minHeap[par] = temp;
            k = par;
        }
    }

    private static long extractFromMin() {
        long res = minHeap[0];
        minHeap[0] = minHeap[--sizeMin];
        int k = 0;
        while (2 * k + 1 < sizeMin) {
            int child = 2 * k + 1;
            if (child < sizeMin && minHeap[child] > minHeap[child + 1]) child++;
            if (minHeap[k] >= minHeap[child]) break;
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