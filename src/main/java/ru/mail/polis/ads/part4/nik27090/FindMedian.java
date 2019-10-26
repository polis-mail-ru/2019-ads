package ru.mail.polis.ads.part4.nik27090;

import java.io.*;

//Задача: Найти медиану-2
//Решение: https://www.e-olymp.com/ru/submissions/5955337

public class FindMedian {

    static int c=0;
    static class MinHeap{
        static int n=-1;
        static int[] elements = new int[500001];

        int size(){
            return n;
        }

        void insert(int num){
            elements[++n]=num;
            swim(n);
        }

        void swim(int k){
            while(k>1 && elements[k]< elements[k/2]){
                swap(k,k/2);
                k=k/2;
            }
        }

        void swap(int fist, int second){
            int tmp = elements[fist];
            elements[fist]=elements[second];
            elements[second]=tmp;
        }

        int top(){
            return elements[1];
        }

        int delMin(){
            int max = elements[1];
            swap(1,n--);
            sink(1);
            return max;
        }

        void sink(int k){
            while (2*k<=n){
                int j=2*k;
                if(j<n && elements[j] > elements[j+1])
                    j++;
                if(elements[k]<=elements[j])
                    break;
                swap(k,j);
                k=j;
            }

        }
    }

    static class MaxHeap{
        static int n=-1;
        static int[] elements = new int[500001];

        int size(){
            return n;
        }

        int top(){
            return elements[1];
        }

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

    static int calcMedian(int num, MaxHeap max, MinHeap min){
        if (num >= min.top())
            min.insert(num);
        else max.insert(num);

        if (max.size()>min.size())
            min.insert(max.delMax());
        else if (max.size()+1<min.size())
            max.insert(min.delMin());
        if (c%2==0){
            c++;
            return min.top();}
        else {
            c++;
            return (min.top() + max.top()) / 2;
        }
    }

    public static void solve(BufferedReader in, PrintWriter out) throws IOException{
        MinHeap min = new MinHeap();
        MaxHeap max = new MaxHeap();
        max.insert(Integer.MIN_VALUE);
        min.insert(Integer.MAX_VALUE);

        String input;
        while((input=in.readLine())!=null){
            out.write(calcMedian(Integer.parseInt(input),max,min)+"\n");
        }
    }

    public static void main(String[] args) {
       try{
           BufferedReader  in = new BufferedReader(new FileReader
                   ("input.txt"));
           PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream
                   ("output.txt")));
           solve(in,out);

           in.close();
           out.close();
       } catch (IOException e){
           e.printStackTrace();
       }
    }
}