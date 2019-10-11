package ru.mail.polis.ads.task3.art241111;

import java.io.*;
import java.util.StringTokenizer;

/**
 Link to result: https://www.e-olymp.com/ru/submissions/5834217
 */

public class MergeSort {
    private static class Robot{
        private int mainNumber;
        private int auxiliaryNumber;


        Robot(int mainNumber, int auxiliaryNumber) {
            this.mainNumber = mainNumber;
            this.auxiliaryNumber = auxiliaryNumber;
        }

        // Getters and setters
        int getMainNumberOrAuxiliaryNumberByIndex(){
            return this.mainNumber;
        }
        public int getMainNumber() {
            return mainNumber;
        }
        public void setMainNumber(int mainNumber) {
            this.mainNumber = mainNumber;
        }

        public int getAuxiliaryNumber() {
            return auxiliaryNumber;
        }
        public void setAuxiliaryNumber(int auxiliaryNumber) {
            this.auxiliaryNumber = auxiliaryNumber;
        }

        @Override
        public String toString() {
            return mainNumber + " " + auxiliaryNumber;
        }
    }

    private static StringBuffer print(Robot[] arrayToPrint, int length){
        StringBuffer out = new StringBuffer();
        for (int i = 0; i < length; i++) {
            out.append(arrayToPrint[i].toString()).append("\n");
        }
        return out;
    }

    private static void merge(
            Robot[] arrayForSort, Robot[] l, Robot[] r, int left, int right) {

        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i].getMainNumber() <= r[j].getMainNumber()) {
                arrayForSort[k++] = l[i++];
            }
            else {
                arrayForSort[k++] = r[j++];
            }
        }
        while (i < left) {
            arrayForSort[k++] = l[i++];
        }
        while (j < right) {
            arrayForSort[k++] = r[j++];
        }
    }

    private static void mergeSort(Robot[] arrayForSort, int n) {
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        Robot[] l = new Robot[mid];
        Robot[] r = new Robot[n - mid];

        System.arraycopy(arrayForSort, 0, l, 0, mid);
        System.arraycopy(arrayForSort, mid, r, 0, n - mid);
        mergeSort(l, mid);
        mergeSort(r, n - mid);

        merge(arrayForSort, l, r, mid, n - mid);
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int countN = in.nextInt();

       Robot[] arrayOfRobots = new Robot[countN];

      for (int i = 0; i < countN; i++) {
            arrayOfRobots[i] = new Robot(in.nextInt(),in.nextInt());
      }
        mergeSort(arrayOfRobots,countN);

      out.print(print(arrayOfRobots,countN));
      out.flush();

    }

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

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
