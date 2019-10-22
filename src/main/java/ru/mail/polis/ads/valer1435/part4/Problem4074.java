package ru.mail.polis.ads.valer1435.part4;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Problem4074 {
    public static void main(String[] args) throws IOException {
        PrintWriter out = new PrintWriter(System.out);
        FastLineScanner in = new FastLineScanner();

        String g = in.next();
        if (g == null || g.equals("")){
            return;
        }
        int a = Integer.parseInt(g);
        out.println(a);
        String h = in.next();
        if (h == null || h.equals("")){
            return;
        }
        int b = Integer.parseInt(h);
        int median = (a+b)/2;
        out.println(median);
        if (a < b){
            int c = a;
            a = b;
            b = c;
        }
        MyHeapMax hmax = new MyHeapMax(b);
        MyHeapMin hmin = new MyHeapMin(a);

        while (true){

          //  out.println(hmax.add_index);
         //   out.println(hmin.add_index);
            try {
                String u = in.next();
                if (u == null || u.equals("")){
                    return;
                }
                int d = Integer.parseInt(u);
                if (d < hmax.firstEl()){
                    hmax.add(d);
                }else{
                    hmin.add(d);
                }
                while(Math.abs(hmax.add_index-hmin.add_index) > 1){
                    if (hmax.add_index > hmin.add_index){
                        int p = hmax.pop();

                        hmin.add(p);
                    }
                    else {
                        int p = hmin.pop();
                        hmax.add(p);
                    }
                }
                if (hmax.add_index == hmin.add_index){
                    median = (hmax.firstEl()+hmin.firstEl())/2;
                    out.println(median);

                }else if(hmax.add_index > hmin.add_index){
                    out.println(hmax.firstEl());
                }else{
                    out.println(hmin.firstEl());
                }
                out.flush();
            }catch (IOException e){
                return;
            }

        }


    }
    public static class MyHeapMax {
        int[] arrHeap = new int[10000002];
        int add_index = 0;
        MyHeapMax(int a){
            arrHeap[add_index]=a;
            add_index++;
        }
        public void swim(int n) {
            while (n > 0 ) {
                if (arrHeap[n] < arrHeap[(n-1) / 2]){
                    break;
                }
                int temp = arrHeap[n];
                arrHeap[n] = arrHeap[(n-1) / 2];
                arrHeap[(n-1)/2] = temp;
                n = (n-1) / 2;
            }
        }
        public void sink(int n){
            int leftChild = 2*n+1;
            while (leftChild < add_index-1) {
                int max = leftChild;
                int rightChild = leftChild + 1;
                if (rightChild < add_index-1) {
                    if (arrHeap[rightChild] > arrHeap[leftChild]) {
                        ++max;
                    }
                }
                if (arrHeap[n] < arrHeap[max]) {
                    int temp = arrHeap[n];
                    arrHeap[n] = arrHeap[max];
                    arrHeap[max] = temp;
                    leftChild = 2*max+1;
                    n = max;
                }
                else break;
            }
        }
        public void add(int value){
            arrHeap[add_index] = value;
            swim(add_index);
            add_index++;
        }

        public int pop(){
            int a = arrHeap[0];
            arrHeap[0] = arrHeap[add_index-1];
            arrHeap[add_index-1] = 0;
            sink(0);
            add_index--;
            return a;
        }
        public int firstEl(){
            return arrHeap[0];
        }
        public String toString(){
            int[] t = new int[10];
            for (int i=0; i < 10; i++){
                t[i] = arrHeap[i];
            }
            return Arrays.toString(t);
        }

    }
    public static class MyHeapMin {
        int[] arrHeap = new int[10000002];
        int add_index = 0;
        MyHeapMin(int a){
            arrHeap[add_index]=a;
            add_index++;
        }
        public void swim(int n) {
            while (n > 0 ) {
                if (arrHeap[n] > arrHeap[(n-1) / 2]){
                    break;
                }
                int temp = arrHeap[n];
                arrHeap[n] = arrHeap[(n-1) / 2];
                arrHeap[(n-1)/2] = temp;
                n = (n-1) / 2;
            }
        }
        public void sink(int n){
            int leftChild = 2*n+1;
            while (leftChild < add_index-1) {
                int max = leftChild;
                int rightChild = leftChild + 1;
                if (rightChild < add_index-1) {
                    if (arrHeap[rightChild] < arrHeap[leftChild]) {
                        ++max;
                    }
                }
                if (arrHeap[n] > arrHeap[max]) {
                    int temp = arrHeap[n];
                    arrHeap[n] = arrHeap[max];
                    arrHeap[max] = temp;
                    leftChild = 2*max+1;
                    n = max;
                }
                else break;
            }
        }
        public void add(int value){
            arrHeap[add_index] = value;
            swim(add_index);
            add_index++;
        }

        public int pop(){
            int a = arrHeap[0];

            arrHeap[0] = arrHeap[add_index-1];
            arrHeap[add_index-1] = 0;
            //System.out.println(this.toString());
            sink(0);

            add_index--;

            return a;

        }
        public int firstEl(){
            return arrHeap[0];
        }
        public String toString(){
            int[] t = new int[10];
            for (int i=0; i < 10; i++){
                t[i] = arrHeap[i];
            }
            return Arrays.toString(t);
        }

    }
    private static class FastLineScanner {
        BufferedReader br;
        StringTokenizer st;

        public FastLineScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (st == null || !st.hasMoreElements()) {
                String b = br.readLine();
                if (b == null || b.equals("")){
                    return null;
                }
                st = new StringTokenizer(b);

            }
            return st.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        long nextLong() throws IOException {
            return Long.parseLong(next());
        }

    }
}