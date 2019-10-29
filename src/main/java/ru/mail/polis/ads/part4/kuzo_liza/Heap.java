package ru.mail.polis.ads.part4.kuzo_liza;

import java.io.*;
import java.util.StringTokenizer;

public class Heap {

    static int[] heap = new int[100000];
    static int list = 0;

    public static void main(String[] args) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }

    public static void insert(int element){
        heap[++list] = element;
        swim(list);
    }

    public static void swim(int list){
        while((list > 1) && (heap[list] > heap[list / 2])){
            swap(list,list / 2);
            list = list / 2;
        }
    }

    public static void swap(int first, int second){
        int tmp = heap[first];
        heap[first] = heap[second];
        heap[second] = tmp;
    }

    public static int extract(){
        int max = heap[1];
        swap(1, list--);
        sink();
        return max;
    }

    public static void sink(){
        int k = 1;
        while (2 * k <= list){
            int j = 2 * k;

            if(j < list && heap[j] < heap[j + 1]){
                j++;
            }

            if(heap[k] >= heap[j]){
                break;
            }

            swap(k, j);
            k = j;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();

        for (int i = 0; i < n; i++) {
            if (in.nextInt() == 1){
                out.println(extract());
            } else {
                insert(in.nextInt());
            }
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
    }

}
