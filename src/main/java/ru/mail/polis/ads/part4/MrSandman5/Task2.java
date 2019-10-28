package part4;

//https://www.e-olymp.com/ru/submissions/5966882

import java.io.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class Task2 {

    private static class Heap {
        private ArrayList<Long> items = new ArrayList<>();

        void insert(long item) {
            items.add(item);
            siftUp();
        }

        private void siftUp() {
            int k = items.size() - 1;
            while(k > 0) {
                int p = (k - 1) / 2;
                long child = items.get(k);
                long parent = items.get(p);

                if (child > parent) {
                    items.set(k, parent);
                    items.set(p, child);
                    k = p;
                } else break;
            }
        }

        private void siftDown() {
            int k = 0;
            int left = 1;

            while(left < items.size()) {
                int max = left;
                int right = left + 1;
                if(right < items.size()) {
                    if(items.get(right).compareTo(items.get(left)) > 0) {
                        max = right;
                    }
                }
                long parent = items.get(k);
                long child = items.get(max);

                if(parent < child) {
                    items.set(k, child);
                    items.set(max, parent);
                    k = max;
                    left = 2*k+1;
                }
                else break;
            }
        }

        long extract() throws NoSuchElementException {
            if(items.size() == 0) throw new NoSuchElementException();

            else if (items.size() == 1) return items.remove(0);

            long tmp = items.get(0);
            items.set(0, items.remove(items.size()-1));
            siftDown();
            return tmp;
        }

    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        Heap heap = new Heap();
        while (n > 0){
            int command = in.nextInt();
            if (command == 0){
                long value = in.nextLong();
                heap.insert(value);
            }
            else if (command == 1){
                out.println(heap.extract());
            }
            n--;
        }
    }

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
        }

        String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}

