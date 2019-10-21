package ru.mail.polis.ads.dz3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DZ3_First {
    public static void main(String[] args) {
        FastScanner in = new FastScanner(System.in);
        int len = in.nextInt();
        int[] mass = new int[len];
        for (int i = 0; i < len; i++) {
            int a = in.nextInt();
            mass[i] = a;
        }
        int left = 0;
        int right = mass.length - 1;
        sort(mass, left, right);
        for (int i = 0; i < mass.length; i++) {
            System.out.print(mass[i] + " ");
        }
    }

    private static void sort(int[] mass, int left, int right) {
        if (mass.length == 0){
            return;
        }
        if (left >= right){
            return;
        }

        int middle = left + (right - left) / 2;
        int opora = mass[middle];

        int i = left, j = right;
        while (i <= j) {
            while (mass[i] < opora) {
                i++;
            }

            while (mass[j] > opora) {
                j--;
            }

            if (i <= j) {
                int temp = mass[i];
                mass[i] = mass[j];
                mass[j] = temp;
                i++;
                j--;
            }
        }

        if (left < j) {
            sort(mass, left, j);
        }
        if (right > i) {
            sort(mass, i, right);
        }
    }
}

class FastScanner {
    private final BufferedReader reader;
    private StringTokenizer tokenizer;

    FastScanner(final InputStream in) {
        reader = new BufferedReader(new InputStreamReader(in));
    }

    String next() {
        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
            try {
                tokenizer = new StringTokenizer(reader.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return tokenizer.nextToken();
    }

    int nextInt() {
        return Integer.parseInt(next());
    }
}
