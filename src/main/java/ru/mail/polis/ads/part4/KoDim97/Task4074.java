package ru.mail.polis.ads.part4.KoDim97;

import java.io.*;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */

class BinaryHeapMax {
    private int[] elements;
    private int count;
    public BinaryHeapMax(){
        this.elements = new int[1000000];
        count = 0;
    }
    public void add(int element) {
        elements[count] = element;
        siftUp(count);
        count++;
    }
    public int swapMax(int element) {
        int prevMax = elements[0];
        elements[0] = element;
        return prevMax;
    }
    public int extractMax() {
        int result = elements[0];
        elements[0] = elements[count - 1];
        deleteLast();
        if (!isEmpty()) {
            siftDown(0);
        }
        return result;
    }

    public int getMax() {
        return elements[0];
    }

    public boolean isEmpty() {
        return count == 0;
    }

    private void deleteLast() {
        count--;
    }

    private void siftDown(int i) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int largest = i;
        if (left < count && elements[left] > elements[i]) {
            largest = left;
        }
        if (right < count && elements[right] > elements[largest]) {
            largest = right;
        }
        if (largest != i) {
            int temp = elements[i];
            elements[i] = elements[largest];
            elements[largest] = temp;
            i = largest;
            siftDown(largest);
        }
    }
    private void siftUp(int i) {
        while (i > 0) {
            int parent = (i - 1) / 2;
            if (elements[i] < elements[parent])
                return;
            int temp = elements[i];
            elements[i] = elements[parent];
            elements[parent] = temp;
            i = parent;
        }
    }
    int getCount(){
        return count;
    }
}
class BinaryHeapMin {
    private int[] elements;
    private int count;
    public BinaryHeapMin(){
        this.elements = new int[1000000];
        count = 0;
    }
    public void add(int element) {
        elements[count] = element;
        siftUp(count);
        count++;
    }
    public int swapMin(int element) {
        int prevMin = elements[0];
        elements[0] = element;
        return prevMin;
    }
    public int extractMin() {
        int result = elements[0];
        elements[0] = elements[count - 1];
        deleteLast();
        if (!isEmpty()) {
            siftDown(0);
        }
        return result;
    }

    public int getMin() {
        return elements[0];
    }

    public boolean isEmpty() {
        return count == 0;
    }

    private void deleteLast() {
        count--;
    }

    private void siftDown(int i) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int largest = i;
        if (left < count && elements[left] < elements[i]) {
            largest = left;
        }
        if (right < count && elements[right] < elements[largest]) {
            largest = right;
        }
        if (largest != i) {
            int temp = elements[i];
            elements[i] = elements[largest];
            elements[largest] = temp;
            i = largest;
            siftDown(largest);
        }
    }
    private void siftUp(int i) {
        while (i > 0) {
            int parent = (i - 1) / 2;
            if (elements[i] > elements[parent])
                return;
            int temp = elements[i];
            elements[i] = elements[parent];
            elements[parent] = temp;
            i = parent;
        }
    }
    int getCount(){
        return count;
    }
}

public final class Task4074 {
    private Task4074() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        BinaryHeapMax max = new BinaryHeapMax();
        BinaryHeapMin min = new BinaryHeapMin();
        try {
            Integer median = in.nextInt();
            out.println(median);
            int curNum;
            while (true){
                curNum = in.nextInt();
                if (median != null){
                    if (median > curNum){
                        max.add(curNum);
                        min.add(median);
                    }
                    else{
                        min.add(curNum);
                        max.add(median);
                    }
                    median = null;
                    out.println((min.getMin() + max.getMax()) / 2);
                }
                else{
                    if (min.getMin() < curNum){
                        median = min.extractMin();
                        min.add(curNum);
                    }
                    else if (max.getMax() > curNum){
                        median = max.extractMax();
                        max.add(curNum);
                    }
                    else{
                        median = curNum;
                    }
                    out.println(median);
                }
            }
        }
        catch (Exception e){
            return;
        }
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
        long nextLong() {
            return Long.parseLong(next());
        }
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out))) {
            solve(in, out);
        }
    }
}

