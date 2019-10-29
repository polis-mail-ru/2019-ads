package part4;

//https://www.e-olymp.com/ru/submissions/5978313

import java.io.*;

public class Task3 {

    private static int c = 0;
    static class MinHeap{
        int n=-1;
        long[] heap = new long[100001];

        MinHeap() {
            insert(Long.MAX_VALUE);
        }

        int size(){
            return n;
        }

        void insert(long num){
            heap[++n] = num;
            swim(n);
        }

        void swim(int k){
            while(k > 1 && heap[k] < heap[k / 2]){
                swap(k, k / 2);
                k /= 2;
            }
        }

        void swap(int first, int second){
            long tmp = heap[first];
            heap[first] = heap[second];
            heap[second] = tmp;
        }

        long top(){
            return heap[1];
        }

        long delMin(){
            long max = heap[1];
            swap(1,n--);
            sink(1);
            return max;
        }

        void sink(int k){
            while (2 * k <= n){
                int j = 2 * k;
                if(j < n && heap[j] > heap[j+1])
                    j++;
                if(heap[k] <= heap[j])
                    break;
                swap(k,j);
                k = j;
            }

        }
    }

    static class MaxHeap{
        int n=-1;
        long[] heap = new long[100001];

        MaxHeap() {
            insert(Long.MIN_VALUE);
        }

        int size(){
            return n;
        }

        long top(){
            return heap[1];
        }

        void insert(long num){
            heap[++n] = num;
            swim(n);
        }

        long delMax(){
            long max = heap[1];
            swap(1, n--);
            sink(1);
            return max;
        }

        void swim(int k){
            while(k > 1 && heap[k] > heap[k/2]){
                swap(k, k/2);
                k /= 2;
            }
        }

        void swap(int first, int second){
            long tmp = heap[first];
            heap[first] = heap[second];
            heap[second] = tmp;
        }

        void sink(int k){
            while (2*k <= n){
                int j = 2*k;
                if(j < n && heap[j] < heap[j+1])
                    j++;
                if(heap[k] >= heap[j])
                    break;
                swap(k,j);
                k = j;
            }
        }
    }

    private static void solve(final BufferedReader in, final PrintWriter out) {
        MinHeap minHeap = new MinHeap();
        MaxHeap maxHeap = new MaxHeap();

        String input;
        try {
            while ((input = in.readLine()) != null) {
                out.write(findMedian(Long.parseLong(input), maxHeap, minHeap) + "\n");
            }
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }

    private static long findMedian(long num, MaxHeap maxHeap, MinHeap minHeap){
        if (num >= minHeap.top())
            minHeap.insert(num);
        else maxHeap.insert(num);

        if (maxHeap.size() > minHeap.size())
            minHeap.insert(maxHeap.delMax());
        else if (maxHeap.size() + 1 < minHeap.size())
            maxHeap.insert(minHeap.delMin());
        if (c % 2==0){
            c++;
            return minHeap.top();
        }
        else {
            c++;
            return (minHeap.top() + maxHeap.top()) / 2;
        }
    }

    public static void main(final String[] arg) {
        try{
            final BufferedReader  in = new BufferedReader(new FileReader
                    ("input.txt"));
            final PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream
                    ("output.txt")));
            solve(in, out);
            in.close();
            out.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}