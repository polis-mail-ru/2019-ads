package ru.mail.polis.ads.part3.jhoysbou;

import com.sun.jdi.event.StepEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class FifthExercise {

    private static class FastScanner {
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

    private static class Robot {
        int mainNumber;
        int addionalNumber;

        Robot(int mainNumber, int additionalNumber) {
            this.mainNumber = mainNumber;
            this.addionalNumber = additionalNumber;
        }

        public String toString() {
            return mainNumber + " " + addionalNumber;
        }
    }

    public static void main(String[] args) throws IOException {
        FastScanner scanner = new FastScanner(System.in);
        int length = scanner.nextInt();
        Robot[] robotsArray = new Robot[length];

        for (int i = 0; i < length; i++) {
            robotsArray[i] = new Robot(scanner.nextInt(), scanner.nextInt());

        }
        robotsArray = mergeSort(robotsArray);

        for (int i = 0; i < length; i++) {
            System.out.println(robotsArray[i].toString());
        }

    }

    private static Robot[] mergeSort(Robot[] robotsArray) {
        int length = robotsArray.length;

        if (length < 2) {
            return robotsArray;
        }

        int pivot = length / 2;

        Robot[] left = new Robot[pivot];
        Robot[] right = new Robot[length-pivot];

        System.arraycopy(robotsArray, 0, left, 0, pivot);

//        for (int i = 0; i < pivot; i++) {
//            left[i] = robotsArray[i];
//        }

        System.arraycopy(robotsArray, pivot, right, 0, length - pivot);
//        for (int i = pivot; i < length; i++) {
//            right[i - pivot] = robotsArray[i];
//        }

        left = mergeSort(left);
        right = mergeSort(right);

        return merge(left, right);
    }

    private static Robot[] merge(Robot[] left, Robot[] right) {
        Robot[] sorted = new Robot[left.length + right.length];
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            if (left[i].mainNumber <= right[j].mainNumber) {
                sorted[k++] = left[i++];
            } else {
                sorted[k++] = right[j++];
            }
        }

        while (i < left.length) {
            sorted[k++] = left[i++];
        }

        while (j < right.length) {
            sorted[k++] = right[j++];
        }

        return sorted;
    }

}
