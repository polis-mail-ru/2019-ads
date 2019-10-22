package ru.mail.polis.ads.part3.kokobox7;

import java.io.*;
import java.util.Comparator;

public class SimpleSort {

    private static void solve(final BufferedReader in, final PrintWriter out) {
        try {
            int n = Integer.parseInt(in.readLine());

            Integer[] array = new Integer[n];
            String[] input = in.readLine().split(" ");
            for (int i = 0; i < n; i++) {
                array[i] = Integer.parseInt(input[i]);
            }
            //quick sort implementation is too slow for e-olymp
            //quickSort(array);
            //hence I reuse mergeSort
            mergeSort(array, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1 - o2;
                }
            });

            for (int i : array) {
                out.print(i + " ");
            }
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    static void quickSort(Integer[] array) {
        if (array.length < 2) {
            return;
        } else {
            quickSortIteration(array, 0, array.length - 1);
        }
    }

    private static void quickSortIteration(Integer[] array, int start, int end) {
        if (start < end) {
            int pIndex = quickSortPartition(array, start, end);
            quickSortIteration(array, start, pIndex - 1);
            quickSortIteration(array, pIndex + 1, end);
        } else
            return;
    }

    private static int quickSortPartition(Integer[] a, int start, int end) {
        int pivot = a[end];
        int pIndex = start;
        for (int i = start; i < end; i++) {
            if (a[i] <= pivot) {
                swap(a, i, pIndex);
                pIndex++;
            }
        }
        swap(a, pIndex, end);
        return pIndex;
    }

    private static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static <T> void mergeSort(T[] array, Comparator<T> comparator) {
        int length = array.length;
        if (length < 2) return;

        int halfLength = length / 2;
        Object[] left = new Object[halfLength];
        Object[] right = new Object[length - halfLength];
        for (int i = 0; i < halfLength; i++) {
            left[i] = array[i];
        }
        for (int i = halfLength; i < length; i++) {
            right[i - halfLength] = array[i];
        }
        mergeSort((T[]) left, comparator);
        mergeSort((T[]) right, comparator);
        merge((T[]) left, (T[]) right, (T[]) array, comparator);

    }

    private static <T> void merge(T[] left, T[] right, T[] array, Comparator<T> comparator) {
        int leftLength = left.length;
        int rightLength = right.length;
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < leftLength && j < rightLength) {
            if (comparator.compare(left[i], right[j]) <= 0) {
                array[k] = left[i];
                i++;
                k++;
            } else {
                array[k] = right[j];
                j++;
                k++;
            }
        }
        while (i < leftLength) {
            array[k] = left[i];
            i++;
            k++;
        }
        while (j < rightLength) {
            array[k] = right[j];
            j++;
            k++;
        }
    }

    public static void main(final String[] arg) {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        solve(in, out);
    }
}
