package ru.mail.polis.ads.part3.gogun;

import java.io.*;
import java.util.*;

public class Task5 {

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

    private static class Robots {
        int mainNumber;
        int addNumber;
    }

    private static void sort(ArrayList<Robots> array, int n) {
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        ArrayList<Robots> leftPart = new ArrayList<>();
        ArrayList<Robots> rightPart = new ArrayList<>();

        for (int i = 0; i < mid; i++) {
            leftPart.add(i, array.get(i));
        }
        for (int i = mid; i < n; i++) {
            rightPart.add(i-mid, array.get(i));
        }
        sort(leftPart, mid);
        sort(rightPart, n - mid);

        merge(array, leftPart, rightPart, mid, n - mid);
    }

    public static void merge(ArrayList<Robots> array, ArrayList<Robots> leftPart, ArrayList<Robots> rightPart, int left, int right) {
        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (leftPart.get(i).mainNumber <= rightPart.get(j).mainNumber) {
                array.set(k++, leftPart.get(i++));
            }
            else {
                array.set(k++, rightPart.get(j++));
            }
        }
        while (i < left) {
            array.set(k++, leftPart.get(i++));
        }
        while (j < right) {
            array.set(k++, rightPart.get(j++));
        }
    }

    private static void solve(FastScanner input, PrintWriter output) {

            int robotsNumber = input.nextInt();

            ArrayList<Robots> array = new ArrayList<Robots>();

            for (int i = 0; i < robotsNumber; ++i) {
                Robots robot = new Robots();
                robot.mainNumber = input.nextInt();
                robot.addNumber = input.nextInt();
                array.add(robot);
            }

            sort(array, array.size());

            for (int i = 0; i < robotsNumber; ++i) {
                StringBuilder sb = new StringBuilder();
                sb.append(array.get(i).mainNumber);
                sb.append(" ");
                sb.append(array.get(i).addNumber);
                output.println(sb);
            }


    }

    public static void main(String[] args) {
        FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}