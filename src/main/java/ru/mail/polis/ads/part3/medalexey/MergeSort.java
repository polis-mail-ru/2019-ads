package ru.mail.polis.ads.part3.medalexey;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 *  Задача: Сортировка слиянием (https://www.e-olymp.com/ru/problems/4037)
 *  Тестирование: https://www.e-olymp.com/ru/submissions/5839111
 */
public class MergeSort {

    private static Element[] arr;
    private static Element[] sortedSubArr;

    private MergeSort() {
    }


    private static void solve(final BufferedReader in, final PrintWriter out) throws IOException {
        final int numberOfElements = Integer.parseInt(in.readLine());
        arr = new Element[numberOfElements];
        sortedSubArr = new Element[numberOfElements];

        readElements(in, numberOfElements);
        sort(0, arr.length-1);
        printElements(out);

        out.close();
        in.close();
    }

    private static class Element {
        int mainNumber;
        int secondaryNumber;

        Element() {
        }

        Element setMainNumber(int mainNumber) {
            this.mainNumber = mainNumber;
            return this;
        }

        Element setSecondaryNumber(int secondaryNumber) {
            this.secondaryNumber = secondaryNumber;
            return this;
        }
    }


    private static void readElements(final BufferedReader in, final int numberOfElements) throws IOException{

        for (int i = 0; i < numberOfElements; i++) {
            String[] elementInfo = in.readLine().split(" ");
            arr[i] = new Element()
                    .setMainNumber(Integer.parseInt(elementInfo[0]))
                    .setSecondaryNumber(Integer.parseInt(elementInfo[1]));
        }

    }

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
            } else if (arr[rightPointer].mainNumber < arr[leftPointer].mainNumber) {
                sortedSubArr[sortedArrPointer] = arr[rightPointer];
                sortedArrPointer++;
                rightPointer++;
            } else {
                sortedSubArr[sortedArrPointer] = arr[leftPointer];
                sortedArrPointer++;
                leftPointer++;
            }
        }

        // copy sorted part back to main array
        System.arraycopy(sortedSubArr, 0, arr, left, right-left+1);
    }


    private static void printElements(final PrintWriter out) {
        for (Element element : arr) {
            out.println(
                    element.mainNumber + " " + element.secondaryNumber
            );
        }
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
