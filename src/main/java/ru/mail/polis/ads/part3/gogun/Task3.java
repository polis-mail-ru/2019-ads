package ru.mail.polis.ads.part3.gogun;

import java.io.*;
import java.util.*;

public class Task3 {
    static ArrayList<Integer> array;
    static int changes = 0;

    private static void swap(int i, int j) {
        int tmp = array.get(j);
        array.set(j, array.get(i));
        array.set(i, tmp);
        changes++;
    }

    private static void sort(int n) {
        for (int i = 0; i < n; ++i) {
            for (int j = n - 1; j > i; --j) {
                if (array.get(j-1) > array.get(j)) {
                    swap(j-1, j);
                }
            }
        }
    }

    private static void solve(BufferedReader input, PrintWriter output) {
        try {
            int k = Integer.parseInt(input.readLine());

            array = new ArrayList<Integer>();

            for (String a: input.readLine().split(" ")){
                array.add(Integer.parseInt(a));
            }

            sort(array.size());

            output.println(changes);



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
