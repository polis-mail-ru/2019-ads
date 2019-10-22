package ru.mail.polis.ads.part3;

import java.io.*;

public final class Task5 {

    private static class Robot {
        int basic;
        int auxiliary;

        Robot(int basic, int auxiliary) {
            this.basic = basic;
            this.auxiliary = auxiliary;
        }
    }

    private Task5() {
        // Should not be instantiated
    }

    private static void mergeSort(Robot[] a) {
        if (a.length < 2) {
            return;
        }
        int mid = a.length / 2;
        Robot[] l = new Robot[mid];
        Robot[] r = new Robot[a.length - mid];

        System.arraycopy(a, 0, l, 0, mid);

        System.arraycopy(a, mid, r, 0, a.length - mid);

        mergeSort(l);
        mergeSort(r);
        merge(a, l, r);
    }

    private static void merge(Robot[] a, Robot[] l, Robot[] r) {
        int i = 0, j = 0, k = 0;
        int left = l.length;
        int right = r.length;
        while (i < left && j < right) {
            if (l[i].basic <= r[j].basic) {
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
        //int[] basic = new int[n];
        //int[] auxiliary = new int[n];
        Robot[] robotArray = new Robot[n];
        for (int i = 0; i < n; ++i) {
            String[] str = in.readLine().split(" ");
            robotArray[i] = new Robot(Integer.parseInt(str[0]), Integer.parseInt(str[1]));
        }

        mergeSort(robotArray);

        for (int i = 0; i < n; ++i) {
            out.write(robotArray[i].basic + " " + robotArray[i].auxiliary + "\n");
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