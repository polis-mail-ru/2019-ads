package ru.mail.polis.ads.valer1435.part4;
// https://www.e-olymp.com/ru/submissions/5875176
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Problem4039 {
    public static void main(String[] args){
        MyHeap h = new MyHeap(new ArrayList<Integer>());
        PrintWriter out = new PrintWriter(System.out);
        FastScanner in = new FastScanner();
        int cnt = in.nextInt();
        for(int i=0; i < cnt; i++) {
            int c = in.nextInt();
            switch (c) {
                case 0: {
                    h.add(in.nextInt());
                    break;
                }
                case 1: {
                    out.println(h.firstEl());
                    h.pop();
                    break;
                }
            }
        }
        out.flush();
    }
    public static class MyHeap {
        ArrayList<Integer> arrHeap;

        MyHeap(ArrayList<Integer> arr){
            arrHeap = arr;
        }
        public void swim(int n) {
            while (n > 0 ) {
                if (arrHeap.get(n) < arrHeap.get((n-1) / 2)){
                    break;
                }
                Collections.swap(arrHeap, n, (n-1) / 2);
                n = (n-1) / 2;
            }
        }
        public void sink(int n){
            int leftChild = 2*n+1;
            while (leftChild < arrHeap.size()) {
                int max = leftChild;
                int rightChild = leftChild + 1;
                if (rightChild < arrHeap.size()) {
                    if (arrHeap.get(rightChild).compareTo(arrHeap.get(leftChild)) > 0) {
                        ++max;
                    }
                }
                if (arrHeap.get(n).compareTo(arrHeap.get(max)) < 0) {
                    Collections.swap(arrHeap, n, max);
                    leftChild = 2*max+1;
                    n = max;
                }
                else break;
            }
        }
        public void add(int value){
            arrHeap.add(value);
            swim(arrHeap.size()-1);
        }

        public void pop(){
            Collections.swap(arrHeap, 0, arrHeap.size()-1);
            arrHeap.remove(arrHeap.size()-1);
            sink(0);
        }
        public int firstEl(){
            return arrHeap.get(0);
        }
        public String toString(){
            return arrHeap.toString();
        }

    }
    private static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        public FastScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

    }
}

