package ru.mail.polis.ads.part4.zvladn7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Task3 {
  private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
  private static PrintWriter out = new PrintWriter(System.out);
  private static Heap min = new Heap(500001, false);
  private static Heap max = new Heap(500001, true);
  private static int tmp;

  static private class Heap {
    private int[] heap;
    private int count = 0;
    private final boolean isMax;

    Heap(final int size, final boolean isMax) {
      heap = new int[size + 1];
      this.isMax = isMax;
    }

    final void insert(final int elem) {
      heap[++count] = elem;
      siftUp(count);
    }

    final void siftUp(int index) {
      int higher = index / 2;
      while (index != 1 && (heap[index] > heap[higher] && isMax || !isMax && heap[index] < heap[higher])) {
          tmp = heap[index];
          heap[index] = heap[higher];
          heap[higher] = tmp;
          index = higher;
          higher = index / 2;
      }
    }

    final void siftDown(int index) {
      int max = index;
      while (index <= count) {
        if (2 * index <= count && heap[2 * index] > heap[max] && isMax ||
                2 * index <= count && heap[2 * index] < heap[max] && !isMax ) {
          max = 2 * index;
        }
        if (2 * index + 1 <= count && heap[2 * index + 1] > heap[max] && isMax ||
                2 * index + 1 <= count && heap[2 * index + 1] < heap[max] && !isMax) {
          max = 2 * index + 1;
        }
        if (max == index) {
          break;
        }
        tmp = heap[index];
        heap[index] = heap[max];
        heap[max] = tmp;
        index = max;
      }
    }
  }

  private static void solve() throws IOException{
    int tmp;
    int mid = -1;
    int next;
    boolean first = false;
    while (true) {
      next = Integer.parseInt(in.readLine());
      if (mid == -1 && min.count == max.count) {
        mid = next;
        if (first && mid > min.heap[1]) {
          tmp = min.heap[1];
          min.heap[1] = mid;
          min.siftDown(1);
          mid = tmp;
        } else if (mid < max.heap[1]) {
          tmp = max.heap[1];
          max.heap[1] = mid;
          max.siftDown(1);
          mid = tmp;
        }
        first = true;
        out.println(mid);
      } else {
        if (next > mid) {
          max.insert(mid);
          min.insert(next);
        } else {
          min.insert(mid);
          max.insert(next);
        }
        mid = -1;
        out.println((max.heap[1] + min.heap[1]) / 2);
      }
      if (!in.ready()) {
        break;
      }
    }
  }

  public static void main(String[] args) {
    try {
      solve();
      out.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}