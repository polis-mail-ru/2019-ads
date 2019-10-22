package ru.mail.polis.ads.valer1435.part4;

import java.util.ArrayList;
import java.util.Arrays;

public class HeapTest {
    public static  void main(String[] args){
        ArrayList<Integer> arr = new ArrayList<>();
        int[] a = new int[]{-1, 50, 40, 30, 20, 15, 10, 5};
        for (int i:a
             ) {
            arr.add(i);
        }
        MyHeap h = new MyHeap(arr);
        h.pop();
        h.add(37);
        System.out.println(h.toString());




    }
}
