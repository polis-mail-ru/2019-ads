package ru.mail.polis.ads.part4.gogun;

import java.io.*;
import java.util.*;

public class Task3 {
    static int[] minHeap = new int[1000001];
    static int[] maxHeap= new int[1000001];
    static int sizeMinHeap = 0;
    static int sizeMaxHeap = 0;

    private static void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    private static void swimMax(int k) {
        while (k > 1 && maxHeap[k] > maxHeap[k/2]) {
            swap(maxHeap, k, k/2);
            k = k/2;
        }
    }

    private static void insertMax(int e) {
        maxHeap[++sizeMaxHeap] = e;
        swimMax(sizeMaxHeap);
    }

    private static void swimMin(int k) {
        while (k > 1 && minHeap[k] <= minHeap[k/2]) {
            swap(minHeap, k, k/2);
            k = k/2;
        }
    }

    private static void insertMin(int e) {
        minHeap[++sizeMinHeap] = e;
        swimMin(sizeMinHeap);
    }

    private static void sinkMax(int k) {
        while (k*2 <= sizeMaxHeap) {
            int j = 2 * k;
            if (j < sizeMaxHeap && maxHeap[j] < maxHeap[j+1])
                j++;
            if (maxHeap[k] >= maxHeap[j])
                break;
            swap(maxHeap, k, j);
            k = j;
        }
    }

    private static int extractMax() {
        int del = maxHeap[1];
        swap(maxHeap, 1, sizeMaxHeap);
        --sizeMaxHeap;
        sinkMax(1);
        return del;
    }

    private static void sinkMin(int k) {
        while (k*2 <= sizeMinHeap) {
            int j = 2 * k;
            if (j < sizeMinHeap && minHeap[j] > minHeap[j+1])
                j++;
            if (minHeap[k] <= minHeap[j])
                break;
            swap(minHeap, k, j);
            k = j;
        }
    }

    private static int extractMin() {
        int del = minHeap[1];
        swap(minHeap, 1, sizeMinHeap);
        --sizeMinHeap;
        sinkMin(1);
        return del;
    }

    private static void solve(BufferedReader input, PrintWriter output) {
        try {
            int nextNum;
            int med;

            minHeap[0] = -1;
            maxHeap[0] = -1;
            int first = 0;
            String line;
            while ((line = input.readLine()) != null) {
                ++first;
                nextNum = Integer.parseInt(line);
                if (first == 1)
                    insertMax(nextNum);
                else {
                    if (maxHeap[1] > nextNum) {
                        insertMax(nextNum);
                    } else {
                        insertMin(nextNum);
                    }
                }
                if (Math.abs(sizeMaxHeap - sizeMinHeap) > 1) {
                    if (Math.max(sizeMaxHeap, sizeMinHeap) == sizeMaxHeap) {
                        insertMin(extractMax());
                    } else {
                        insertMax(extractMin());
                    }
                }

                if (sizeMaxHeap == sizeMinHeap) {
                    med = (maxHeap[1] + minHeap[1]) / 2;
                } else {
                    if (Math.max(sizeMaxHeap, sizeMinHeap) == sizeMaxHeap) {
                        med = maxHeap[1];
                    } else {
                        med = minHeap[1];
                    }
                }

                output.println(med);
            }

            int a = 1;



        } catch (IOException e) {
            e.printStackTrace();
        }




    }

    public static void main(String[] args) {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out))) {
            solve(in, out);
        }
    }
}
