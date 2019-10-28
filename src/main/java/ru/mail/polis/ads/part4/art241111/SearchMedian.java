package ru.mail.polis.ads.part4.art241111;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Link is https://www.e-olymp.com/ru/submissions/5965906
 */
class Heap {
    private boolean reversed;
    private int[] array;
    private int lastIndex;

    Heap(boolean reversed) {
        this.array = new int[500001];
        lastIndex = 0;

        this.reversed = reversed;
    }

    void insert(int num) {
        lastIndex++;
        array[lastIndex] = num;
        siftUp(lastIndex);
    }

    int extract() {
        int res = array[1];
        array[1] = array[lastIndex];
        lastIndex--;
        if (lastIndex > 0) {
            siftDown(1);
        }
        return res;
    }

    int peek() {
        return array[1];
    }

    private int compare(int a, int b) {
        if (reversed) {
            return (b - a);
        } else {
            return (a - b);
        }
    }

    private void siftUp(int index) {
        while (index / 2 > 0 && compare(array[index / 2], array[index]) < 0) {
            swap(index / 2, index);
            index = index / 2;
        }
    }

    private void siftDown(int index) {
        while (index <= lastIndex / 2) {
            int maxIndex = index * 2;
            maxIndex +=
                    index * 2 + 1 <= lastIndex && compare(array[index * 2 + 1], array[index * 2]) > 0
                            ? 1
                            : 0;
            if (compare(array[index], array[maxIndex]) < 0) {
                swap(index, maxIndex);
                index = maxIndex;
            } else {
                break;
            }
        }
    }

    private void swap(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}

public class SearchMedian {
        private static void solve() throws IOException {
            Heap leftHeap = new Heap(false);
            Heap rightHeap = new Heap(true);

            int median = 0;
            boolean isSet = false;
            boolean isEven = false;
            try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
                 BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))
            ) {
                String inText = reader.readLine();
                while (inText != null) {
                    int num = Integer.parseInt(inText);
                    if (isEven) {
                        if (num < leftHeap.peek()) {
                            int temp = leftHeap.extract();
                            leftHeap.insert(num);
                            num = temp;
                        }
                        if (num > rightHeap.peek()) {
                            int temp = rightHeap.extract();
                            rightHeap.insert(num);
                            num = temp;
                        }
                        median = num;
                        isEven = false;
                    } else if (!isSet) {
                        median = num;
                        isSet = true;
                    } else {
                        if (num < median) {
                            leftHeap.insert(num);
                            rightHeap.insert(median);
                        } else {
                            leftHeap.insert(median);
                            rightHeap.insert(num);
                        }
                        isEven = true;
                        median = (leftHeap.peek() + rightHeap.peek()) / 2;
                    }
                    writer.write(median + "\n");
                    inText = reader.readLine();
                }
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
