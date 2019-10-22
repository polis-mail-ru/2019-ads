package ru.mail.polis.ads.part3;

import java.io.*;

/**
 * Problem solution template.
 */
public final class Task1 {
    private Task1() {
        // Should not be instantiated
    }

    private static void mergeSort(int[] a) {
        if (a.length < 2) {
            return;
        }
        int mid = a.length / 2;
        int[] l = new int[mid];
        int[] r = new int[a.length - mid];

        System.arraycopy(a, 0, l, 0, mid);

        System.arraycopy(a, mid, r, 0, a.length - mid);

        mergeSort(l);
        mergeSort(r);
        merge(a, l, r);
    }

    private static void merge(int[] a, int[] l, int[] r) {
        int i = 0, j = 0, k = 0;
        int left = l.length;
        int right = r.length;
        while (i < left && j < right) {
            if (l[i] <= r[j]) {
                a[k++] = l[i++];
            } else {
                a[k++] = r[j++];
            }
        }
        while (i < left) {
            a[k++] = l[i++];
        }
        while (j < right) {
            a[k++] = r[j++];
        }
    }

    private static void solve(final BufferedReader in, final BufferedWriter out) throws IOException {
        int n = Integer.parseInt(in.readLine());
        int[] arr = new int[n];
        String[] str = in.readLine().split(" ");
        for (int i = 0; i < n; ++i) {
            arr[i] = Integer.parseInt(str[i]);
        }

        mergeSort(arr);

        for (int i = 0; i < n; ++i) {
            out.write(arr[i] + " ");
        }
    }


    public static void main(final String[] arg) {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out))) {
            solve(in, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
