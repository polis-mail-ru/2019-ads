package ru.mail.polis.ads.part3.medalexey;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;


/**
 *  Задача: k-тая порядковая статистика (https://www.e-olymp.com/ru/problems/4827)
 *  Тестирование:   https://www.e-olymp.com/ru/submissions/5846396
 */
public class OrdinalStatistic {

    private static BigInteger[] elements;
    private static BigInteger[] smallerElements;
    private static BigInteger[] biggerElements;


    private OrdinalStatistic() {
    }


    private static void solve(final BufferedReader in, final PrintWriter out) throws IOException {
        final int k = Integer.parseInt(in.readLine());
        final String[] sElements = in.readLine().split(" ");

        defineArrays(sElements.length);

        convertElements(sElements);

        out.println( findOrderStatistic(elements.length - k) );

        in.close();
        out.close();
    }


    private static void defineArrays(final int size) {
        elements = new BigInteger[size];
        smallerElements = new BigInteger[size];
        biggerElements = new BigInteger[size];
    }


    // convert elements from String array to BigInteger array
    private static void convertElements(final String[] sElements) {
        for (int i = 0; i < sElements.length; i++) {
            elements[i] = new BigInteger(sElements[i]);
        }
    }


    private static BigInteger findOrderStatistic(final int index) {
        int left = 0;
        int right = elements.length-1;

        int pivot = left+(right-left)/2;

        int pivotPosition = rearrangeElements(left, right, pivot);
        while (pivotPosition != index) {
            if (pivotPosition < index) {
                if(right-left == 1) {
                    ++left;
                } else {
                    left = pivotPosition;
                }
            } else {
                right = pivotPosition;
            }
            pivot = left+(right-left)/2;
            pivotPosition = rearrangeElements(left, right, pivot);
        }

        return elements[index];
    }


    private static int rearrangeElements(final int left, final int right, final int pivot) {
        int smallerElementsPointer = 0;
        int biggerElementsPointer = 0;


        for (int i = left; i <= right; i++) {
            if (i != pivot) {
                if (elements[i].compareTo(elements[pivot]) < 0) {
                    smallerElements[smallerElementsPointer] = elements[i];
                    smallerElementsPointer++;
                } else {
                    biggerElements[biggerElementsPointer] = elements[i];
                    biggerElementsPointer++;
                }
            }
        }
        smallerElements[smallerElementsPointer] = elements[pivot];
        smallerElementsPointer++;

        System.arraycopy(smallerElements, 0, elements, left, smallerElementsPointer);
        System.arraycopy(biggerElements, 0, elements, left+smallerElementsPointer, biggerElementsPointer);

        return left + smallerElementsPointer-1;
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
