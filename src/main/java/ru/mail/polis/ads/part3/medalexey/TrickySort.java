package ru.mail.polis.ads.part3.medalexey;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 *  Задача: Хитрая сортировка (https://www.e-olymp.com/ru/problems/1462)
 *  Тестирование:  https://www.e-olymp.com/ru/submissions/5839398
 */
public class TrickySort {

    private static int[] elements;
    private static int[] sortedSubArr;

    private TrickySort() {
    }


    private static void solve(final BufferedReader in, final PrintWriter out) throws IOException{
        final int numberOfElements = Integer.parseInt(in.readLine());
        elements = new int[numberOfElements];
        sortedSubArr = new int[numberOfElements];

        readElements(in, numberOfElements);
        sort(0, elements.length-1);
        printArray(out);

        in.close();
        out.close();
    }


    private static void readElements(final BufferedReader in, int numberOfElements) throws IOException{
        for (int i =0; i < numberOfElements; i++) {
            elements[i] = Integer.parseInt(in.readLine());
        }
    }


    // merge sort
    private static void sort(final int left, final int right) {

        if(left == right) {
            return;
        }

        int middle = left+(right-left)/2;
        sort(left, middle);
        sort(middle+1, right);

        int leftPointer = left, rightPointer = middle+1, sortedArrPointer = 0;
        while (leftPointer <= middle || rightPointer <= right ) {
            if (leftPointer == middle+1) {
                System.arraycopy(elements, rightPointer, sortedSubArr, sortedArrPointer, right-rightPointer+1);
                rightPointer = right+1;
            } else if (rightPointer == right+1) {
                System.arraycopy(elements, leftPointer, sortedSubArr, sortedArrPointer, middle-leftPointer+1);
                leftPointer = middle+1;
            } else if (elements[leftPointer]%10 < elements[rightPointer]%10) {
                sortedSubArr[sortedArrPointer] = elements[leftPointer];
                sortedArrPointer++;
                leftPointer++;
            } else if (elements[rightPointer]%10 < elements[leftPointer]%10){
                sortedSubArr[sortedArrPointer] = elements[rightPointer];
                sortedArrPointer++;
                rightPointer++;
            } else if (elements[leftPointer] <= elements[rightPointer]) {
                sortedSubArr[sortedArrPointer] = elements[leftPointer];
                sortedArrPointer++;
                leftPointer++;
            } else {
                sortedSubArr[sortedArrPointer] = elements[rightPointer];
                sortedArrPointer++;
                rightPointer++;
            }
        }

        // copy sorted part back to main array
        System.arraycopy(sortedSubArr, 0, elements, left, right-left+1);
    }


    private static void printArray(final PrintWriter out) {
        StringBuilder result = new StringBuilder();

        for (Integer element: elements) {
            result.append(element.toString());
            result.append(" ");
        }

        out.println(result.toString().trim());
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
