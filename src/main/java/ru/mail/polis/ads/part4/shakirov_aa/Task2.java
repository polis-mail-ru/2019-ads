package ru.mail.polis.ads.part4.shakirov_aa;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Task2 {
    public static void main(String[] args) {
        FastScanner fs = new FastScanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        final int N = fs.nextInt();
        List<Integer> list = new ArrayList<>();
        list.add(-1);
        int n1, n2, count = 1;
        for (int i = 1; i <= N; i++) {
            n1 = fs.nextInt();
            if (n1 == 0) {
                n2 = fs.nextInt();
                insert(list, n2);
                count++;
            } else {
                out.println(extract(list));
                count--;
            }
        }

        out.close();
    }

    static int extract(List<Integer> list) {
        int max = list.get(1);
        int size = list.size();
        swap(list, 1, size - 1);
        list.remove(size - 1);
        sink(list ,1);
        return max;
    }

    static void insert(List<Integer> list, int x) {
        list.add(x);
        swim(list, list.size() - 1);
    }

    static void swim(List<Integer> list, int k) {
        while (k > 1 && list.get(k) > list.get(k/2)) {
            swap(list, k, k/2);
            k = k/2;
        }
    }

    static void sink(List<Integer> list, int k) {
        int n = list.size() - 1;
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && list.get(j) < list.get(j+1)) {
                j++;
            }

            if (list.get(k) >= list.get(j)) {
                break;
            }

            swap(list, k, j);
            k = j;
        }
    }

    static void swap(List<Integer> list, int a, int b) {
        int buff = list.get(a);
        list.set(a, list.get(b));
        list.set(b, buff);
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
    }
}
