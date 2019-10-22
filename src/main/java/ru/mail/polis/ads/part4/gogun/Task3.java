package ru.mail.polis.ads.part4.gogun;

import java.io.*;
import java.util.*;

public class Task3 {

    private static void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    private static void swim(int k, int[] array) {
        while (k > 1 && array[k] > array[k/2]) {
            swap(array, k, k/2);
            k = k/2;
        }
    }

    private static void insertMax(int[] array, int e, int n) {
        array[++n] = e;
        swim(n, array);
    }

    private static void sink(int k, int[] array, int n) {
        while (k*2 <= n) {
            int j = 2 * k;
            if (j < n && array[j] < array[j+1])
                j++;
            if (array[k] >= array[j])
                break;
            swap(array, k, j);
            k = j;
        }
    }

    private static void insertMin(int[] array, int e, int n) {
        array[++n] = e;
        sink(1, array, n);
    }

    private static void solve(BufferedReader input, PrintWriter output) {
        try {
            int nextNum;
            int size = 0;
            int med = 0;

            int[] minHeap = new int[1000001];
            minHeap[0] = -1;

            int[] maxHeap= new int[1000001];
            maxHeap[0] = -1;

            String line;
            while ((line = input.readLine()) != null) {
                nextNum = Integer.parseInt(line);
                ++size;

                if (size == 1) {
                    med = nextNum;
                    insertMax(maxHeap, nextNum, size);
                } else {


                    if (size % 2 == 0) {
                        if (nextNum <= med)
                            insertMax(maxHeap, nextNum, size);

                        if (nextNum >= med)
                            insertMin(minHeap, nextNum, size);

                        med = (maxHeap[1] + minHeap[1]) / 2;

                    } else {
                        if (nextNum >= maxHeap[1] && nextNum <= minHeap[1]) {
                            med = nextNum;
                            insertMin(minHeap, nextNum, size);
                        }
                    }


                    output.println(med);
                }
            }
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
