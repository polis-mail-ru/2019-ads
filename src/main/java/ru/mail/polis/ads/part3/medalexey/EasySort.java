package ru.mail.polis.ads.part3.medalexey;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.BufferedOutputStream;
import java.io.InputStreamReader;


/**
 *  Задача: Простая сортировка (https://www.e-olymp.com/ru/problems/3738)
 *  Тестирование: https://www.e-olymp.com/ru/submissions/5838746
 */
public class EasySort {
    private static int[] arr;
    private static int[] sortedSubArr;

    private EasySort() {

    }


    private static void solve(final BufferedReader in, final PrintWriter out) throws IOException{
        final int numberOfElements = Integer.parseInt(in.readLine());
        arr = new int[numberOfElements];
        sortedSubArr = new int[numberOfElements];

        readElements(in, numberOfElements);
        sort(0, arr.length-1);
        printArray(out, arr);

        in.close();
        out.close();
    }


    private static void readElements(final BufferedReader in, final int numberOfElements) throws IOException{
        final String[] elements = in.readLine().split(" ");

        for (int i = 0; i < numberOfElements; i++) {
            arr[i] = Integer.parseInt(elements[i]);
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
                System.arraycopy(arr, rightPointer, sortedSubArr, sortedArrPointer, right-rightPointer+1);
                rightPointer = right+1;
            } else if (rightPointer == right+1) {
                System.arraycopy(arr, leftPointer, sortedSubArr, sortedArrPointer, middle-leftPointer+1);
                leftPointer = middle+1;
            } else if (arr[leftPointer] < arr[rightPointer]) {
                sortedSubArr[sortedArrPointer] = arr[leftPointer];
                sortedArrPointer++;
                leftPointer++;
            } else {
                sortedSubArr[sortedArrPointer] = arr[rightPointer];
                sortedArrPointer++;
                rightPointer++;
            }
        }

        // copy sorted part back to main array
        System.arraycopy(sortedSubArr, 0, arr, left, right-left+1);
    }


    private static void printArray(final PrintWriter out, final int[] elements) {
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
