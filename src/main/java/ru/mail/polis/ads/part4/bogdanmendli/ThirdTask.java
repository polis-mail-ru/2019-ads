package ru.mail.polis.ads.part4.bogdanmendli;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class ThirdTask {

    private static long[] leftHeap;
    private static long[] rightHeap;
    private static int leftIndex;
    private static int rightIndex;
    private static long mid;

    private static void solve() throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("input.txt"));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream("output.txt")));
        leftHeap = new long[500_001];
        rightHeap = new long[500_001];
        leftIndex = 0;
        leftHeap[1] = Integer.MIN_VALUE;
        rightHeap[1] = Integer.MAX_VALUE;
        rightIndex = 0;
        int index = 0;
        String input = in.readLine();
        while (input != null) {
            index++;
            int current = Integer.parseInt(input);
            if (index % 2 == 0) {
                if (current > mid) {
                    leftHeap[++leftIndex] = mid;
                    swimLeftHeap(leftIndex);
                    rightHeap[++rightIndex] = current;
                    swimRightHeap(rightIndex);
                } else {
                    leftHeap[++leftIndex] = current;
                    swimLeftHeap(leftIndex);
                    rightHeap[++rightIndex] = mid;
                    swimRightHeap(rightIndex);
                }
                long result = (rightHeap[1] + leftHeap[1]) / 2;
                out.println(result);
            } else {
                if (current < leftHeap[1]) {
                    mid = leftHeap[1];
                    leftHeap[1] = current;
                    sinkLeftHeap(1);
                } else if (current > rightHeap[1]) {
                    mid = rightHeap[1];
                    rightHeap[1] = current;
                    sinkRightHeap(1);
                } else {
                    mid = current;
                }
                out.println(mid);
            }
            input = in.readLine();
        }

        in.close();
        out.close();
    }

    private static void swap(int indexMass, int first, int second) {
        if (indexMass == 0) {
            long temp = leftHeap[first];
            leftHeap[first] = leftHeap[second];
            leftHeap[second] = temp;
            return;
        }
        long temp = rightHeap[first];
        rightHeap[first] = rightHeap[second];
        rightHeap[second] = temp;
    }

    private static void sinkLeftHeap(int k) {
        while (2 * k <= leftIndex) {
            int j = 2 * k;
            if (j < leftIndex && leftHeap[j] < leftHeap[j + 1]) {
                j++;
            }
            if (leftHeap[k] >= leftHeap[j]) {
                break;
            }
            swap(0, k, j);
            k = j;
        }
    }

    private static void sinkRightHeap(int k) {
        while (2 * k <= rightIndex) {
            int j = 2 * k;

            if (j < rightIndex && rightHeap[j] > rightHeap[j + 1]) {
                j++;
            }

            if (rightHeap[k] <= rightHeap[j]) {
                break;
            }
            swap(1, k, j);
            k = j;
        }
    }

    private static void swimLeftHeap(int k) {
        while (k > 1 && leftHeap[k] > leftHeap[k / 2]) {
            swap(0, k, k / 2);
            k /= 2;
        }
    }

    private static void swimRightHeap(int k) {
        while (k > 1 && rightHeap[k] < rightHeap[k / 2]) {
            swap(1, k, k / 2);
            k /= 2;
        }
    }

    public static void main(String[] args) {
        try {
            solve();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}