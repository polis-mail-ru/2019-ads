package ru.mail.polis.ads.part4.shakirov_aa;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Task3 {
    public static void main(String[] args) throws IOException {
        List<Integer> minHeap = new ArrayList<>();
        minHeap.add(-1);
        List<Integer> maxHeap = new ArrayList<>();
        maxHeap.add(-1);

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
        int size1, size2, buff;
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

            size1 = minHeap.size();
            size2 = maxHeap.size();
            if (size1 != size2) {
                median = getMedian(minHeap, maxHeap, size1, size2);
                isMedianPushed = true;
            } else {
                median = (minHeap.get(1) + maxHeap.get(1)) / 2;
                isMedianPushed = false;
            }
            out.println(median);
            s = bf.readLine();
        }

        bf.close();
        out.close();
    }

    static int getMedian(List<Integer> listMin, List<Integer> listMax, int size1, int size2) {
        int median;
        if (size1 > size2) {
            median = extractMin(listMin, size1);
        } else {
            median = extractMax(listMax, size2);
        }

        return median;
    }

    static int extractMax(List<Integer> list, int size) {
        int max = list.get(1);
        swap(list, 1, size - 1);
        list.remove(size - 1);
        sinkMax(list, 1, size - 1);
        return max;
    }

    static int extractMin(List<Integer> list, int size) {
        int max = list.get(1);
        swap(list, 1, size - 1);
        list.remove(size - 1);
        sinkMin(list, 1, size - 1);
        return max;
    }

    static void sinkMax(List<Integer> list, int k, int size) {
        int n = size - 1;
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && list.get(j) < list.get(j+1)) {
                j++;
            }

            if (list.get(k) >= list.get(j)) {
                break;
            }

            swap(list, k, j);
            k = j;
        }
    }

    static void sinkMin(List<Integer> list, int k, int size) {
        int n = size - 1;
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && list.get(j) > list.get(j+1)) {
                j++;
            }

            if (list.get(k) <= list.get(j)) {
                break;
            }

            swap(list, k, j);
            k = j;
        }
    }

    static void insertMax(List<Integer> list, int x) {
        list.add(x);
        swimMax(list, list.size() - 1);
    }

    static void insertMin(List<Integer> list, int x) {
        list.add(x);
        swimMin(list, list.size() - 1);
    }

    static void swimMax(List<Integer> list, int k) {
        while (k > 1 && list.get(k) > list.get(k / 2)) {
            swap(list, k, k / 2);
            k = k / 2;
        }
    }

    static void swimMin(List<Integer> list, int k) {
        while (k > 1 && list.get(k) < list.get(k / 2)) {
            swap(list, k, k / 2);
            k = k / 2;
        }
    }

    static void swap(List<Integer> list, int a, int b) {
        int buff = list.get(a);
        list.set(a, list.get(b));
        list.set(b, buff);
    }
}
