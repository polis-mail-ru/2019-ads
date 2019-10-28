package ru.mail.polis.ads.part4.KateMoreva;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

//e-olymp problem 4039 "Хипуй!"

public class Task2 {
    private Task2(){
    }
    private static class Heap {
    private int[] heapArray;
    private int pointer;

    Heap(final int size) {
        heapArray = new int[size + 1];
        pointer = 1;
    }

    void insert(final int x) {
        heapArray[pointer] = x;
        swim(pointer);
        ++pointer;
    }

    int extract() {
        --pointer;
        int tmp = heapArray[1];
        heapArray[1] = heapArray[pointer];
        heapArray[pointer] = tmp;
        swim(sinkFirst());
        return heapArray[pointer];
    }

    private void swim(int index) {
        while (index != 1 && heapArray[index / 2] < heapArray[index]) {
            final int tmp = heapArray[index / 2];
            heapArray[index / 2] = heapArray[index];
            heapArray[index] = tmp;
            index /= 2;
        }
    }

    private int sinkFirst() {
        int index = 1;
        int direction = index * 2;
        while (direction < pointer - 1) {
            if (heapArray[direction] < heapArray[direction + 1]) {
                direction++;
            }
            final int tmp = heapArray[direction];
            heapArray[direction] = heapArray[index];
            heapArray[index] = tmp;
            index = direction;
            direction *= 2;
        }
        if (direction == pointer - 1) {
            final int tmp = heapArray[direction];
            heapArray[direction] = heapArray[index];
            heapArray[index] = tmp;
            index = direction;
        }
        return index;
    }
}

    private static void solve() throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter output = new PrintWriter(System.out);
        int size = Integer.parseInt(in.readLine());
        Heap heap = new Heap(size);
        String str;
        int space;
        for (int i = 0; i < size; ++i) {
            str = in.readLine();
            space = str.indexOf(" ");
            if (space == -1) {
                space = str.length();
            }
            switch (str.substring(0, space)) {
                case "0": {
                    heap.insert(Integer.parseInt(str.substring(str.indexOf(" ") + 1)));
                    break;
                }
                case "1": {
                    output.println(heap.extract());
                    break;
                }
            }
        }
        output.close();

    }

    public static void main(String[] args){
        try {
            solve();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
