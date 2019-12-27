package ru.mail.polis.ads;

import java.io.*;

public class DZ4_Median {
    static int size1 = 0, size2 = 0;

    public static void main(String[] args) throws IOException {
        int[] minHeap = new int[1000001];
        int[] maxHeap = new int[1000001];

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String s = bf.readLine();
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        int median;
        if (s != null && !s.equals("")) {
            median = Integer.parseInt(s);
            out.println(median);
        } else {
            return;
        }

        s = bf.readLine();
        boolean isMedianPushed = true;
        int buff;
        while (s != null && !s.equals("")) {
            buff = Integer.parseInt(s);
            if (buff < median) {
                if (isMedianPushed) {
                    insertMin(minHeap, median);
                }
                insertMax(maxHeap, buff);
            } else {
                if (isMedianPushed) {
                    insertMax(maxHeap, median);
                }
                insertMin(minHeap, buff);
            }


            if (size1 != size2) {
                median = getMedian(minHeap, maxHeap);
                isMedianPushed = true;
            } else {
                median = (minHeap[1] + maxHeap[1]) / 2;
                isMedianPushed = false;
            }
            out.println(median);
            s = bf.readLine();
        }

        bf.close();
        out.close();
    }

    static int getMedian(int[] listMin, int[] listMax) {
        int median;
        if (size1 > size2) {
            median = extractMin(listMin);
        } else {
            median = extractMax(listMax);
        }

        return median;
    }

    static int extractMax(int[] arr) {
        int max = arr[1];
        swap(arr, 1, size2);
        arr[size2] = 0;
        size2--;
        sinkMax(arr, 1);
        return max;
    }

    static int extractMin(int[] arr) {
        int max = arr[1];
        swap(arr, 1, size1);
        arr[size1] = 0;
        size1--;
        sinkMin(arr, 1);
        return max;
    }

    static void sinkMax(int[] arr, int k) {
        while (2 * k <= size2) {
            int j = 2 * k;
            if (j < size2 && arr[j] < arr[j+1]) {
                j++;
            }

            if (arr[k] >= arr[j]) {
                break;
            }

            swap(arr, k, j);
            k = j;
        }
    }

    static void sinkMin(int[] arr, int k) {
        while (2 * k <= size1) {
            int j = 2 * k;
            if (j < size1 && arr[j] > arr[j+1]) {
                j++;
            }

            if (arr[k] <= arr[j]) {
                break;
            }

            swap(arr, k, j);
            k = j;
        }
    }

    static void insertMax(int[] arr, int x) {
        ++size2;
        arr[size2] = x;
        swimMax(arr);
    }

    static void insertMin(int[] arr, int x) {
        ++size1;
        arr[size1] = x;
        swimMin(arr);
    }

    static void swimMax(int[] arr) {
        int k = size2;
        while (k > 1 && arr[k] > arr[k/2]) {
            swap(arr, k, k / 2);
            k = k / 2;
        }
    }

    static void swimMin(int[] arr) {
        int k = size1;
        while (k > 1 && arr[k] < arr[k/2]) {
            swap(arr, k, k / 2);
            k = k / 2;
        }
    }

    static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}