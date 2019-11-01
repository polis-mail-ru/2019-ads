package ru.mail.polis.ads.part4.bardaev;

import java.io.*;

public class Task4074 {
    private Task4074() {}

    static int[] leftHeap = new int[500001];
    static int[] rightHeap = new int[500001];
    static int sizeLeft = 0;
    static int sizeRight = 0;

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

    public static void main(final String[] arg) throws IOException {
        int median = -1;
        String num;

        while ((num = in.readLine()) != null) {
            int x = Integer.parseInt(num);
            if (median == -1) {
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
                median = -1;
                out.println((leftHeap[1] + rightHeap[1]) / 2);
            }
            out.flush();
        }
    }

    public static void insertLeft(int x) {
        if (sizeLeft == 0) {
            leftHeap[1] = x;
            sizeLeft++;
            return;
        }

        leftHeap[++sizeLeft] = x;
        int k = sizeLeft;
        while (leftHeap[k] > leftHeap[k / 2] && k > 0 && k / 2 != 0) {
            int parent = k / 2;
            leftHeap[k] = leftHeap[parent];
            leftHeap[parent] = x;
            k = parent;
        }
    }

    public static void insertRight(int x) {

        if (sizeRight == 0) {
            rightHeap[1] = x;
            sizeRight++;
            return;
        }

        rightHeap[++sizeRight] = x;
        int k = sizeRight;
            while (rightHeap[k] < rightHeap[k/2] && k > 0 && k/2 != 0) {
                int parent = k / 2;
                rightHeap[k] = rightHeap[parent];
                rightHeap[parent] = x;
                k = parent;
            }
    }

    public static int extractMax() {
        int resuult = leftHeap[1];
        leftHeap[1] = leftHeap[sizeLeft];
        leftHeap[sizeLeft] = 0;
        sizeLeft--;
        int k = 1;

        while (k*2 <= sizeLeft) {
            int j = 2 * k;
            if (j < sizeLeft && leftHeap[j] < leftHeap[j+1]) j++;
            if (leftHeap[k] >= leftHeap[j]) break;
            int temp = leftHeap[j];
            leftHeap[j] = leftHeap[k];
            leftHeap[k] = temp;
            k = j;
        }

        return resuult;
    }

    public static int extractMin() {
        int resuult = rightHeap[1];
        rightHeap[1] = rightHeap[sizeRight];
        rightHeap[sizeRight] = 0;
        sizeRight--;
        int k = 1;

        while (k*2 <= sizeRight) {
            int j = 2 * k;
            if (j < sizeRight && rightHeap[j] < rightHeap[j+1]) j++;
            if (rightHeap[k] >= rightHeap[j]) break;
            int temp = rightHeap[j];
            rightHeap[j] = rightHeap[k];
            rightHeap[k] = temp;
            k = j;
        }

        return resuult;
    }

}
