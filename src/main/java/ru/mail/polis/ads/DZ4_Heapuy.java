package ru.mail.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Locale;

public class DZ4_Heapuy {
    public static int[] arr;
    public static int size = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int n = Integer.parseInt(in.readLine());
        arr = new int[n + 1];

        for (int i = 0; i < n; i++) {
            String[] str = in.readLine().split(" ");
            if (Integer.parseInt(str[0]) == 0) {
                insert(Integer.parseInt(str[1]));
            } else {
                out.println(extract());
            }
        }
        out.flush();
    }

    public static void insert(int x) {
        if (size == 0) {
            arr[1] = x;
            size++;
            return;
        }

        arr[++size] = x;
        int k = size;
        while (arr[k] > arr[k / 2] && k > 0 && k / 2 != 0) {
            int parent = k / 2;
            arr[k] = arr[parent];
            arr[parent] = x;
            k = parent;
        }
    }

    public static int extract() {
        int res = arr[1];
        arr[1] = arr[size];
        arr[size] = 0;
        size--;
        int k = 1;

        while (k * 2 <= size) {
            int j = 2 * k;
            if (j < size && arr[j] < arr[j + 1]) j++;
            if (arr[k] >= arr[j]) break;
            int swap = arr[j];
            arr[j] = arr[k];
            arr[k] = swap;
            k = j;
        }

        return res;
    }
}
