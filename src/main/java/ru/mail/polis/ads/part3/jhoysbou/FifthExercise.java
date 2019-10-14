package ru.mail.polis.ads.part3.jhoysbou;

import java.io.*;
import java.util.StringTokenizer;

// Submission here https://www.e-olymp.com/ru/submissions/5855257

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
        int additionalNumber;

        Robot(int mainNumber, int additionalNumber) {
            this.mainNumber = mainNumber;
            this.additionalNumber = additionalNumber;
        }

        public String toString() {
            return mainNumber + " " + additionalNumber;
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

        PrintWriter out = new PrintWriter(System.out);
        for (int i = 0; i < length; i++) {
            out.println(robotsArray[i].toString());
        }

        out.flush();

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
        System.arraycopy(robotsArray, pivot, right, 0, length - pivot);

        left = mergeSort(left);
        right = mergeSort(right);

        return merge(left, right);
    }

    private static Robot[] merge(Robot[] left, Robot[] right) {
        int rLength = right.length;
        int lLength = left.length;
        Robot[] sorted = new Robot[lLength + rLength];
        int i = 0, j = 0, k = 0;

        while (i < lLength && j < rLength) {
            if (left[i].mainNumber <= right[j].mainNumber) {
                sorted[k++] = left[i++];
            } else {
                sorted[k++] = right[j++];
            }
        }

        while (i < lLength) {
            sorted[k++] = left[i++];
        }

        while (j < rLength) {
            sorted[k++] = right[j++];
        }

        return sorted;
    }

}
