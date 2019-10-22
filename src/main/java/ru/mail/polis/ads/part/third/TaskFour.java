package ru.mail.polis.ads.part.third;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * @author v.ivlev
 * https://www.e-olymp.com/ru/submissions/5918728
 */
public class TaskFour {

    private static void solve(final PrintWriter out) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(in.readLine());
        String[] array = in.readLine().trim().split(" ");
        out.print(findOrderStatistic(array, k - 1));
    }

    private static String findOrderStatistic(String[] array, int k) {
        int left = 0;
        int right = array.length - 1;
        while (true) {
            int mid = partition(array, left, right);
            if (mid == k) {
                return array[mid];
            } else if (k < mid) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
    }

    public static int partition(String[] array, int left, int right) {
        String pivot = array[left];
        int i = left;
        int j = right;
        while (i <= j) {
            while (compare(array[i], pivot) > 0) { ++i; }
            while (compare(array[j], pivot) < 0) { --j; }
            if (i >= j) {
                break;
            }
            array = swap(array, i, j);
        }
        return j;
    }

    public static String[] swap(String[] array, int i, int j) {
        String temp = array[i];
        array[i] = array[j];
        array[j] = temp;
        return array;
    }

    private static int compare(String i, String j) {
        if (i.length() > j.length() ||
                i.length() == j.length() &&
                        i.compareTo(j) > 0) {
            return 1;
        } else if (i.compareTo(j) == 0) {
            return 0;
        } else {
            return -1;
        }
    }

    public static void main(final String[] arg) throws IOException {
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(out);
        }
    }
}