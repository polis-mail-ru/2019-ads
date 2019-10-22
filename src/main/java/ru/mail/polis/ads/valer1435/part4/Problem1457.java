package ru.mail.polis.ads.valer1435.part4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Problem1457 {
    public static void main(String[] args){
        PrintWriter out = new PrintWriter(System.out);
        FastScanner in = new FastScanner();
        int cnt = in.nextInt();
        int m = in.nextInt();
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < cnt; i++){
            arr.add(in.nextInt());
        }
//        MyHeapMin h = new MyHeapMin(arr);



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
    public static class MyHeapMin {
        ArrayList<Integer> arrHeap = new ArrayList<>();
        int m;
        MyHeapMin(ArrayList<Integer> a, int m){
            arrHeap = a;
            this.m = m;
        }
        public void swim(int n) {
            while (n > 0 ) {

                if (arrHeap.get(n) > arrHeap.get((n-1) / 2)){
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
                    if (arrHeap.get(rightChild).compareTo(arrHeap.get(leftChild)) < 0) {
                        ++max;
                    }
                }
                if (arrHeap.get(n).compareTo(arrHeap.get(max)) > 0) {
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

        public int pop(){
            int a = arrHeap.get(0);
            Collections.swap(arrHeap, 0, arrHeap.size()-1);
            arrHeap.remove(arrHeap.size()-1);
            sink(0);
            return a;
        }
        public int firstEl(){
            return arrHeap.get(0);
        }
        public String toString(){
            return arrHeap.toString();
        }

    }
//    public static boolean isWay(int[] arr, int l, int r){
//        int[] buf = Arrays.copyOfRange(arr, l, r);
//        for (int i = r-1; i <= l; i--){
//            if
//        }
//    }
}
