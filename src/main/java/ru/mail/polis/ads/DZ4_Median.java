package ru.mail.polis.ads;

import java.io.BufferedOutputStream;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class DZ4_Median {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return - Integer.compare(o1,o2);
            }
        });

        int count, number;
        int max = 999999999;
        maxHeap.add(-max);
        minHeap.add(max);
        count = 0;

        while (true) {
            try {
                number = scan.nextInt();
            } catch (Exception e){
                break;
            }
            if (number >= minHeap.peek()) {
                minHeap.add(number);
            } else {
                maxHeap.add(number);
            }

            if (maxHeap.size() > minHeap.size()) {
                int temp = maxHeap.remove();
                minHeap.add(temp);
            } else if (maxHeap.size() + 1 < minHeap.size()) {
                int temp = minHeap.remove();
                maxHeap.add(temp);
            }

            if (count % 2 == 0) {
                out.println(minHeap.peek());
            }
            else {
                out.println((minHeap.peek() + maxHeap.peek()) / 2);
            }
            count++;
        }
        out.flush();
    }
}