package ru.mail.polis.ads.part3.kuzo_liza;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;

public class KthOrdinalStatistics {

    private static BigInteger[] elements;
    private static BigInteger[] small;
    private static BigInteger[] big;

    private KthOrdinalStatistics() {
    }

    private static void createArrays(final int size) {
        elements = new BigInteger[size];
        small = new BigInteger[size];
        big = new BigInteger[size];
    }

    private static void toBigInteger(final String[] strings) {
        for (int i = 0; i < strings.length; i++) {
            elements[i] = new BigInteger(strings[i]);
        }
    }
    private static void solve(final BufferedReader in, final PrintWriter out) throws IOException {
        final int k = Integer.parseInt(in.readLine());
        final String[] strings = in.readLine().split(" ");

        createArrays(strings.length);
        toBigInteger(strings);

        out.println(findOrderStatistic(elements.length - k));

        in.close();
        out.close();
    }

    private static BigInteger findOrderStatistic(final int index) {
        int left = 0;
        int right = elements.length - 1;

        int pivot = left + (right - left) / 2;

        int pivotPosition = sort(left, right, pivot);
        while (pivotPosition != index) {
            if (pivotPosition < index) {
                if(right - left == 1) {
                    left++;
                } else {
                    left = pivotPosition;
                }
            } else {
                right = pivotPosition;
            }
            pivot = left + (right - left) / 2;
            pivotPosition = sort(left, right, pivot);
        }

        return elements[index];
    }

    private static int sort(final int left, final int right, final int pivot) {
        int smallPointer = 0;
        int bigPointer = 0;


        for (int i = left; i <= right; i++) {
            if (i != pivot) {
                if (elements[i].compareTo(elements[pivot]) < 0) {
                    small[smallPointer] = elements[i];
                    smallPointer++;
                } else {
                    big[bigPointer] = elements[i];
                    bigPointer++;
                }
            }
        }
        small[smallPointer] = elements[pivot];
        smallPointer++;

        System.arraycopy(small, 0, elements, left, smallPointer);
        System.arraycopy(big, 0, elements, left + smallPointer, bigPointer);

        return left + smallPointer-1;
    }


    public static void main(final String[] arg) {
        final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out =  new PrintWriter(new BufferedOutputStream(System.out));

        try {
            solve(in, out);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
