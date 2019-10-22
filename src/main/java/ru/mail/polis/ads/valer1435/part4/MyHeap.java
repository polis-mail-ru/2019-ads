package ru.mail.polis.ads.valer1435.part4;

import java.util.ArrayList;
import java.util.Collections;

public class MyHeap {
    ArrayList<Integer> arrHeap;
    MyHeap(ArrayList<Integer> arr){
        arrHeap = arr;
        arrHeap.add(0, -1);
    }
    public void swim(int n) {
        while (n/2 != 0 && arrHeap.get(n) > arrHeap.get(n / 2)) {
            Collections.swap(arrHeap, n, n / 2);
            n = n / 2;
        }
    }
        public void sink(int n){
            while ( n < (arrHeap.size()-1)/2 && arrHeap.get(n) < Math.min(arrHeap.get(2*n), arrHeap.get(2*n+1))){
                if (arrHeap.get(2*n) > arrHeap.get(2*n+1)){
                Collections.swap(arrHeap, n, 2*n);
                n = 2*n;
                }else{
                    Collections.swap(arrHeap, n, 2*n+1);
                    n = 2*n+1;
                }

            }
    }
    public void add(int value){
        arrHeap.add(value);
        Collections.swap(arrHeap, 1, arrHeap.size()-1);
        sink( 1);
        swim(arrHeap.size()-1);
    }

    public void pop(){
        Collections.swap(arrHeap, 1, arrHeap.size()-1);
        arrHeap.remove(arrHeap.size()-1);
        sink(1)
;    }
    public String toString(){
        return arrHeap.toString();
    }

    }
