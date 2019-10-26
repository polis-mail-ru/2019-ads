package ru.mail.polis.ads.part4.nik27090;

import java.io.*;
import java.util.StringTokenizer;

//Задача: Хипуй!
//Решение: https://www.e-olymp.com/ru/submissions/5954607

public class Hipui {
    private Hipui() {
        // Should not be instantiated
    }

    static class Heap{
        static int n=0;
        static int[] elements = new int[100000];

        void insert(int num){
            elements[++n]=num;
            swim(n);
        }

        int delMax(){
            int max = elements[1];
            swap(1,n--);
            sink(1);
            return max;
        }

        void swim(int k){
            while(k>1 && elements[k]> elements[k/2]){
                swap(k,k/2);
                k=k/2;
            }
        }

        void swap(int fist, int second){
            int tmp = elements[fist];
            elements[fist]=elements[second];
            elements[second]=tmp;
        }

        void sink(int k){
            while (2*k<=n){
                int j=2*k;
                if(j<n && elements[j] < elements[j+1])
                    j++;
                if(elements[k]>=elements[j])
                    break;
                swap(k,j);
                k=j;
            }

        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        Heap heap = new Heap();
        for (int i = 0; i < n; i++) {
            if (in.nextInt()==1){
                out.println(heap.delMax());
            } else {
                heap.insert(in.nextInt());
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

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
