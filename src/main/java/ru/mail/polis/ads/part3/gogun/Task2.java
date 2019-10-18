package ru.mail.polis.ads.part3.gogun;

import java.io.*;

public class Task2 {
    static int[] array;

    private static void swap(int i, int j) {
        int tmp = array[j];
        array[j] = array[i];
        array[i] = tmp;
    }

    private static void sort(int[] array, int n) {
        for (int i = 0; i < n; ++i) {
            int indMin = i;
            for (int j = i; j < n; ++j) {
                if ((array[j]%10) < (array[indMin]%10)) {
                    indMin = j;
                }
                if (((array[j]%10) == (array[indMin]%10))&&(array[j] < array[indMin])) {
                    indMin = j;
                }
            }
            swap(i, indMin);
        }
    }



    private static void solve(BufferedReader input, PrintWriter output) {
        try {
            int n = Integer.parseInt(input.readLine());

            array = new int[n];

            for (int i =0; i < n; i++) {
                array[i] = Integer.parseInt(input.readLine());
            }



            sort(array, array.length);

            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; ++j) {
                sb.append(array[j]);
                sb.append(" ");
            }

            output.println(sb);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}