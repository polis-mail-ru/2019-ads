package ru.mail.polis.ads.part4.medalexey;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *  Название задачи: Найти медиану-2  https://www.e-olymp.com/ru/problems/4074
 *  Тестирование: https://www.e-olymp.com/ru/submissions/5901818
 */
public class FindMedian {

    private static int median;

    private FindMedian() {
    }


    private static void solve(final BufferedReader in, final PrintWriter out) throws IOException{
        median = -1;

        String input;
        while ((input = in.readLine()) != null) {
            out.println(
                    findMedian(
                            Integer.parseInt(input)
                    )
            );
        }
    }


    private static int findMedian(int newElement) {
        if (median == -1) {
            if (LeftSide.peek() != -1 && LeftSide.peek() > newElement) {
                median = LeftSide.peek();
                LeftSide.setTopElement(newElement);
                return median;
            }

            if (RightSide.peek() != -1 && RightSide.peek() < newElement) {
                median = RightSide.peek();
                RightSide.setTopElement(newElement);
                return median;
            }

            median = newElement;
            return median;
        }


        RightSide.put(Math.max(median, newElement));
        LeftSide.put(Math.min(median, newElement));
        median = -1;

        return (RightSide.peek() + LeftSide.peek())/2;
    }


    private static class RightSide {
        private static int[] heap = new int[500_001];;
        private static int amount = 0;

        static void put(int element) {
            heap[++amount] = element;
            swim(amount);
        }

        static int peek() {
            return (amount == 0) ? -1 : heap[1];
        }

        static void swim(int index) {
            while (index > 1 && heap[index] < heap[index/2]) {
                swap(index, index / 2);
                index /= 2;
            }
        }

        private static void swap(int first, int second) {
            int tmp = heap[first];
            heap[first] = heap[second];
            heap[second] = tmp;
        }

        static void setTopElement(int newElement) {
            heap[1] = newElement;
            sink();
        }

        private static void sink() {
            int k = 1;

            while (2 * k <= amount) {
                int j = 2 * k;

                if (j < amount && heap[j] > heap[j + 1]) {
                    j++;
                }

                if (heap[k] <= heap[j]) {
                    break;
                }

                swap(k, j);
                k = j;
            }
        }
    }


    private static class LeftSide {
        private static int[] heap = new int[500_001];
        private static int amount = 0;


        static void put(int element) {
            heap[++amount] = element;
            swim(amount);
        }

        static int peek() {
            return (amount == 0) ? -1 : heap[1];
        }

        static void swim(int index) {
            while (index > 1 && heap[index] > heap[index/2]) {
                swap(index, index / 2);
                index /= 2;
            }
        }

        private static void swap(int first, int second) {
            int tmp = heap[first];
            heap[first] = heap[second];
            heap[second] = tmp;
        }

        static void setTopElement(int newElement) {
            heap[1] = newElement;
            sink();
        }

        private static void sink() {
            int k = 1;

            while (2 * k <= amount) {
                int j = 2 * k;

                if (j < amount && heap[j] < heap[j + 1]) {
                    j++;
                }

                if (heap[k] >= heap[j]) {
                    break;
                }

                swap(k, j);
                k = j;
            }
        }
    }


    public static void main(final String[] arg) {
        BufferedReader in;
        PrintWriter out;

        try {
            in = new BufferedReader(new FileReader("input.txt"));
            out = new PrintWriter(new BufferedOutputStream(new FileOutputStream("output.txt")));

            solve(in, out);

            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
