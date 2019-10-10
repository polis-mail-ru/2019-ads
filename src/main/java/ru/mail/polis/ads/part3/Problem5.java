package ru.mail.polis.ads.part3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


/**
 * submission - https://www.e-olymp.com/ru/submissions/5824985
 */
public final class Problem5 {

    static class Robot {
        int n1;
        int n2;

        Robot(int n1, int n2) {
            this.n1 = n1;
            this.n2 = n2;
        }

        @Override
        public String toString() {
            return n1 + " " + n2;
        }
    }

    private static Robot[] arr;

    private Problem5() {
        // Should not be instantiated
    }

    private static void solve() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());

        arr = new Robot[n];
        for (int i = 0; i < n; i++) {
            String[] tokens = reader.readLine().split(" ");
            arr[i] = new Robot(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
        }

        sort(0, arr.length - 1);

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        for (Robot i : arr) {
            writer.write(i.toString() + "\n");
        }
        writer.flush();
    }

    private static void sort(final int left, final int right) {
        if (left >= right) {
            return;
        }

        int midIndex = (right + left) / 2;
        sort(left, midIndex);
        sort(midIndex + 1, right);

        Robot[] temp = new Robot[right - left + 1];
        int i = 0;
        int left1 = left;
        int left2 = midIndex + 1;
        while (left1 <= midIndex && left2 <= right) {
            if (arr[left2].n1 < arr[left1].n1) {
                temp[i++] = arr[left2++];
            } else {
                temp[i++] = arr[left1++];
            }
        }
        while (left1 <= midIndex) {
            temp[i++] = arr[left1++];
        }
        while (left2 <= right) {
            temp[i++] = arr[left2++];
        }

        System.arraycopy(temp, 0, arr, left, temp.length);
    }

    public static void main(final String[] arg) {
        try {
            solve();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
