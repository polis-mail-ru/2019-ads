package ru.mail.polis.ads.part3.Kungurov;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;


/**
 * created by kirill kungurov on 16.10.19
 * https://www.e-olymp.com/ru/submissions/5917321
 */
public class SolveOfProblem4827 {
    private SolveOfProblem4827() {
        // Should not be instantiated
    }

    private static int compare(String firstArg, String secondArg) {
        if (firstArg.length() > secondArg.length() || firstArg.length() == secondArg.length() && firstArg.compareTo(secondArg) > 0) {
            return 1;
        }
        if (firstArg.compareTo(secondArg) == 0) {
            return 0;
        }
        return -1;
    }

    private static void swap(String[] arr, int i, int j) {
        String tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private static int partition(String[] arr, int left, int right) {
        String pivot = arr[right];
        int i = (left - 1);
        for (int j = left; j < right; j++) {
            if (compare(arr[j], pivot) < 0) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, right);
        return i + 1;
    }

    private static String getKElement(String[] arr, int k) {
        int i = 0;
        int j = arr.length - 1;

        while (i <= j) {
            int tmp = partition(arr, i, j);
            if (tmp == k) {
                return arr[tmp];
            }
            if (tmp > k) {
                j = tmp - 1;
            } else {
                i = tmp + 1;
                k = k - tmp + i - 1;
            }
        }
        return arr[k];
    }

    private static void solve(final BufferedReader in, final PrintWriter out) throws IOException {
        int k = Integer.parseInt(in.readLine());
        String[] temp = in.readLine().split(" ");
        out.print(getKElement(temp, temp.length - k));
    }

    public static void main(final String[] arg) throws IOException {
        final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}