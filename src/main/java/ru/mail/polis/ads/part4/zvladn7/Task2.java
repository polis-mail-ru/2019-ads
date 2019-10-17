package ru.mail.polis.ads.part4.zvladn7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Task2 {
  private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
  private static PrintWriter out = new PrintWriter(System.out);

  static private class Heap {
    private int[] heap;
    private int count = 0;
    Heap(final int size) {
      heap = new int[size + 1];
    }

    void insert(final int elem) {
      heap[++count] = elem;
      siftUp(count);
    }

    int extract() {
      int max = heap[1];
      heap[1] = heap[count--];
      siftDown(1);
      return max;
    }

    void siftDown(int index) {
      int max = index;
      while (index <= count) {
        if (2 * index <= count && heap[2 * index] > heap[max]) {
          max = 2 * index;
        }
        if (2 * index + 1 <= count && heap[2 * index + 1] > heap[max]) {
          max = 2 * index + 1;
        }
        if (max == index) {
          break;
        }
        int tmp = heap[index];
        heap[index] = heap[max];
        heap[max] = tmp;
        index = max;
      }
    }

    void siftUp(int index) {
      int higher = index / 2;
      while (index != 1) {
        if (heap[index] > heap[higher]) {
          int tmp = heap[index];
          heap[index] = heap[higher];
          heap[higher] = tmp;
          index = higher;
          higher = index / 2;
        } else {
          break;
        }
      }
    }
  }

  public static void solve() throws IOException{
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
          heap.insert(Integer.parseInt(str.substring(str.indexOf(" ") + 1, str.length())));
          break;
        }
        case "1": {
          System.out.println(heap.extract());
          break;
        }
      }
    }
  }

  public static void main(final String[] args) {
    try {
      solve();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
