package ru.mail.polis.ads.part4.nekobitlz;

import java.io.*;
import java.util.*;

public class Task3 {

    private static Queue<Integer> maxHeap;
    private static Queue<Integer> minHeap;

    private static void solve(final BufferedReader in, final PrintWriter out) throws IOException {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        String line = in.readLine();

        while (line != null) {
            int n = Integer.parseInt(line);
            addNum(n);

            out.println(findMedian());
            line = in.readLine();
        }

        out.close();
    }

    private static void addNum(int num) {
        if (maxHeap.size() == minHeap.size()) {
            minHeap.add(num);
            maxHeap.add(minHeap.remove());
        } else if (maxHeap.size() > minHeap.size()) {
            maxHeap.add(num);
            minHeap.add(maxHeap.remove());
        } // maxHeap will never be smaller size than minHeap
    }

    private static int findMedian() {
        if (maxHeap.isEmpty()) {
            return 0;
        } else if (maxHeap.size() == minHeap.size()) {
            return (maxHeap.peek() + minHeap.peek()) / 2;
        } else {
            return maxHeap.peek();
        }
    }

    public static void main(final String[] arg) {

        try {
            final BufferedReader in = new BufferedReader(new FileReader("input.txt"));
            PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream("output.txt")));
            solve(in, out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
