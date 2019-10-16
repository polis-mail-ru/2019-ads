package ru.mail.polis.ads.part3.gogun;

import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Task4 {
    static ArrayList<BigInteger> array;

    private static int partition(int left, int right) {
        BigInteger pivot = array.get(left);
        int j = left;
        for (int i = left+1; i < right; ++i) {
            if (array.get(i).compareTo(pivot) > 0) {
                j++;
                Collections.swap(array, i, j);
            }
        }
        Collections.swap(array, left, j);

        return j;
    }

    private static BigInteger k_por(int k) {
        int left = 0;
        int right = array.size();
        while (true) {
            int mid = partition(left, right);

            if (mid == k) {
                return array.get(mid);
            }
            else if (k < mid) {
                right = mid;
            }
            else {
                left = mid + 1;
            }
        }
    }

    private static void solve(BufferedReader input, PrintWriter output) {
        try {
            int k = Integer.parseInt(input.readLine())-1;

            array = new ArrayList<BigInteger>();

            for (String a: input.readLine().split(" ")){
                array.add(new BigInteger(a));
            }

            output.println(k_por(k));


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
