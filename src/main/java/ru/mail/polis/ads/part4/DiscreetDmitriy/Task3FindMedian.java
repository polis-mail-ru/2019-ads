package ru.mail.polis.ads.part4.DiscreetDmitriy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Task3FindMedian {

    private static long[] leftHeap;
    private static long[] rightHeap;
    private static int leftI;
    private static int rightI;
    private static long middle;

    private static void solve(final BufferedReader in, final PrintWriter out) throws IOException {
        leftHeap = new long[500_001];
        rightHeap = new long[500_001];

        leftI = 0;
        rightI = 0;

        leftHeap[1] = Integer.MIN_VALUE;
        rightHeap[1] = Integer.MAX_VALUE;

        int index = 0;
        String input = in.readLine();

        while (input != null) {
            index++;

            int current = Integer.parseInt(input);

            if (index % 2 == 0) {
                if (current > middle) {
                    leftHeap[++leftI] = middle;
                    swimLeftHeap(leftI);

                    rightHeap[++rightI] = current;
                    swimRightHeap(rightI);
                } else {
                    leftHeap[++leftI] = current;
                    swimLeftHeap(leftI);

                    rightHeap[++rightI] = middle;
                    swimRightHeap(rightI);
                }

                long result = (rightHeap[1] + leftHeap[1]) / 2;

                out.println(result);

            } else {
                if (current < leftHeap[1]) {
                    middle = leftHeap[1];
                    leftHeap[1] = current;
                    sinkLeftHeap();
                } else if (current > rightHeap[1]) {
                    middle = rightHeap[1];
                    rightHeap[1] = current;
                    sinkRightHeap();
                } else {
                    middle = current;
                }

                out.println(middle);
            }

            input = in.readLine();
        }

        in.close();
        out.close();
    }

    private static void sinkLeftHeap() {
        int k = 1;
        while (2 * k <= leftI) {
            int j = 2 * k;
            if (j < leftI && leftHeap[j] < leftHeap[j + 1]) {
                j++;
            }
            if (leftHeap[k] >= leftHeap[j]) {
                break;
            }
            swap(true, k, j);
            k = j;
        }
    }

    private static void sinkRightHeap() {
        int k = 1;
        while (2 * k <= rightI) {
            int j = 2 * k;

            if (j < rightI && rightHeap[j] > rightHeap[j + 1]) {
                j++;
            }

            if (rightHeap[k] <= rightHeap[j]) {
                break;
            }
            swap(false, k, j);
            k = j;
        }
    }

    private static void swimLeftHeap(int k) {
        while (k > 1 && leftHeap[k] > leftHeap[k / 2]) {
            swap(true, k, k / 2);
            k /= 2;
        }
    }

    private static void swimRightHeap(int k) {
        while (k > 1 && rightHeap[k] < rightHeap[k / 2]) {
            swap(false, k, k / 2);
            k /= 2;
        }
    }

    private static void swap(boolean isLeft, int first, int second) {
        if (isLeft) {
            long temp = leftHeap[first];
            leftHeap[first] = leftHeap[second];
            leftHeap[second] = temp;
        }
        else {
            long temp = rightHeap[first];
            rightHeap[first] = rightHeap[second];
            rightHeap[second] = temp;
        }
    }

    public static void main(String[] args) {
        final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}