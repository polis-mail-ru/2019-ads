package ru.mail.polis.ads.part4.bardaev;

import java.io.*;

public class Task4074 {
    private Task4074() {}

    static long[] leftHeap = new long[500001];
    static long[] rightHeap = new long[500001];
    static int sizeLeft = 0;
    static int sizeRight = 0;

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

    public static void main(final String[] arg) throws IOException {
        Long median = null;
        String num;

        try {
            while ((num = in.readLine()) != null) {
                long x = Integer.parseInt(num);
                if (median == null) {
                    if (x > rightHeap[1]) {
                        insertRight(x);
                        median = extractMin();
                    } else if (x < leftHeap[1]) {
                        insertLeft(x);
                        median = extractMax();
                    } else {
                        median = x;
                    }
                    out.println(median);
                } else {
                    if (x < median) {
                        insertLeft(x);
                        insertRight(median);
                    } else {
                        insertLeft(median);
                        insertRight(x);
                    }
                    median = null;
                    out.println((leftHeap[1] + rightHeap[1]) / 2);
                }
            }
        } catch (IOException e) {

        } catch (NumberFormatException e) {

        } finally {
        out.flush();

        }
    }

    public static void insertLeft(long x) {
        if (sizeLeft == 0) {
            leftHeap[1] = x;
            sizeLeft++;
            return;
        }

        leftHeap[++sizeLeft] = x;
        int k = sizeLeft;
        while (leftHeap[k] > leftHeap[k / 2] && k > 1) {
            int parent = k / 2;
            swap(leftHeap, k, parent);
            k = parent;
        }
    }

    static void swap(long[] a, int i, int j) {
        long t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void insertRight(long x) {

        if (sizeRight == 0) {
            rightHeap[1] = x;
            sizeRight++;
            return;
        }

        rightHeap[++sizeRight] = x;
        int k = sizeRight;
            while (rightHeap[k] < rightHeap[k/2] && k > 1) {
                int parent = k / 2;
                swap(rightHeap, k, parent);
                k = parent;
            }
    }

    public static long extractMax() {
        long resuult = leftHeap[1];
        leftHeap[1] = leftHeap[sizeLeft--];

        int k = 1;

        while (k*2 <= sizeLeft) {
            int j = 2 * k;
            if (j < sizeLeft && leftHeap[j] < leftHeap[j+1]) j++;
            if (leftHeap[k] >= leftHeap[j]) break;
            swap(leftHeap, k, j);
            k = j;
        }

        return resuult;
    }

    public static long extractMin() {
        long resuult = rightHeap[1];
        rightHeap[1] = rightHeap[sizeRight--];

        int k = 1;

        while (k*2 <= sizeRight) {
            int j = 2 * k;
            if (j < sizeRight && rightHeap[j] > rightHeap[j+1]) j++;
            if (rightHeap[k] <= rightHeap[j]) break;
            swap(rightHeap, k, j);
            k = j;
        }

        return resuult;
    }

}
