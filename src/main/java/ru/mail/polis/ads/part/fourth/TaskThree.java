package ru.mail.polis.ads.part.fourth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * @author v.ivlev
 * https://www.e-olymp.com/ru/submissions/5974495
 */
public class TaskThree {

    private static int c = 0;

    private static void solve(PrintWriter out) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        Heap min = new Heap(Integer.MIN_VALUE, true);
        Heap max = new Heap(Integer.MAX_VALUE, false);
        String inputData;
        while ((inputData = in.readLine()) != null) {
            out.println(calcMedian(Integer.parseInt(inputData), max, min));
        }
    }

    private static int calcMedian(int num, Heap max, Heap min) {
        if (num >= min.top()) {
            min.insert(num);
        } else {
            max.insert(num);
        }

        if (max.n > min.n) {
            min.insert(max.delMax());
        } else if (max.n + 1 < min.n) {
            max.insert(min.delMin());
        }
        if (c % 2 == 0) {
            c++;
            return min.top();
        } else {
            c++;
            return (min.top() + max.top()) / 2;
        }
    }

    private static class Heap {
        protected int n = -1;
        private int[] elements = new int[500001];
        private boolean min;

        public Heap(int num, boolean value) {
            this.min = value;
            insert(num);
        }

        public int top() {
            return elements[1];
        }

        public void insert(int num) {
            elements[++n] = num;
            if (min) {
                swimMin(n);
            } else {
                swimMax(n);
            }
        }

        public int delMin() {
            int min = elements[1];
            swap(1, n--);
            sinkMin(1);
            return min;
        }

        public int delMax() {
            int max = elements[1];
            swap(1, n--);
            sinkMax(1);
            return max;
        }

        public void swimMin(int k) {
            while (k > 1 && elements[k] < elements[k / 2]) {
                swap(k, k / 2);
                k = k / 2;
            }
        }

        public void swimMax(int k) {
            while (k > 1 && elements[k] > elements[k / 2]) {
                swap(k, k / 2);
                k = k / 2;
            }
        }

        public void swap(int fist, int second) {
            int tmp = elements[fist];
            elements[fist] = elements[second];
            elements[second] = tmp;
        }

        public void sinkMin(int k) {
            while (2 * k <= n) {
                int j = 2 * k;
                if (j < n && elements[j] > elements[j + 1]) {
                    j++;
                }
                if (elements[k] <= elements[j]) {
                    break;
                }
                swap(k, j);
                k = j;
            }
        }

        public void sinkMax(int k) {
            while (2 * k <= n) {
                int j = 2 * k;
                if (j < n && elements[j] < elements[j + 1]) {
                    j++;
                }
                if (elements[k] >= elements[j]) {
                    break;
                }
                swap(k, j);
                k = j;
            }
        }
    }

    public static void main(final String[] arg) throws IOException {
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(out);
        }
    }
}
