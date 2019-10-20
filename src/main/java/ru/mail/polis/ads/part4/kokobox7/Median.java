package ru.mail.polis.ads.part4.kokobox7;

import java.io.*;

public class Median {
    private static class Heap {
        private long[] array;
        //points to where new element should be added
        private int pointer;
        boolean max;

        //boolean max - shows if heap is descending or ascending in path to root
        Heap(int size, boolean max) {
            this.max = max;
            array = new long[size + 1];
            pointer = 1;
        }

        Heap(int size) {
            max = true;
            array = new long[size + 1];
            pointer = 1;
        }

        void insert(final long x) {
            array[pointer] = x;
            swim(pointer);
            pointer++;
        }

        long extract() {
            swap(--pointer, 1);
            int indx = sink();
            swim(indx);
            return array[pointer];
        }

        private void swim(int index) {
            if (max) {
                while (index != 1 && array[index] > array[index / 2]) {
                    swap(index, index / 2);
                    index /= 2;
                }
            } else {
                while (index != 1 && array[index] < array[index / 2]) {
                    swap(index, index / 2);
                    index /= 2;
                }
            }
        }

        private void swap(int index1, int index2) {
            final long tmp = array[index2];
            array[index2] = array[index1];
            array[index1] = tmp;
        }

        //sinks first element
        private int sink() {
            int index = 1;
            int where = index * 2;
            while (where < pointer - 1) {
                if (max) {
                    if (array[where] < array[where + 1]) {
                        where++;
                    }
                } else {
                    if (array[where] > array[where + 1]) {
                        where++;
                    }
                }
                swap(index, where);
                index = where;
                where *= 2;
            }
            if (where == pointer - 1) {
                swap(index, where);
                index = where;
            }
            return index;
        }

        long peek(){
            return array[1];
        }
    }

    public static void main(String[] args) {
        final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try (PrintWriter out = new PrintWriter(System.out)) {
            Heap maxHeap = new Heap((int) (Math.pow(10,6) / 2), true);
            Heap minHeap = new Heap((int) (Math.pow(10,6) /2), false);

            long median = Long.parseLong(in.readLine());;
            out.println(median);

            boolean stepIsEven = false;
            while (true) {
                final long newElement = Long.parseLong(in.readLine());
                if (stepIsEven) {
                    final long maxLeft = maxHeap.peek();
                    final long minRight = minHeap.peek();
                    if (newElement > minRight) {
                        median = minHeap.extract();
                        minHeap.insert(newElement);
                    } else if (newElement > maxLeft) {
                        median = newElement;

                    } else {
                        median = maxHeap.extract();
                        maxHeap.insert(newElement);
                    }
                    out.println(median);
                } else {
                    if (newElement > median) {
                        minHeap.insert(newElement);
                        maxHeap.insert(median);
                    } else {
                        minHeap.insert(median);
                        maxHeap.insert(newElement);
                    }
                    out.println((maxHeap.peek() + minHeap.peek()) / 2);
                }
                stepIsEven = !stepIsEven;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
