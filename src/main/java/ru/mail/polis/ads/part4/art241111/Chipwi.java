import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Link is https://www.e-olymp.com/ru/submissions/5914742
 */
class Heap{
    private List<Long> list = new ArrayList<>();

    int heapSize(){
        return this.list.size();
    }

    void add(long value)
    {
        list.add(value);
        int i = heapSize() - 1;
        int parent = (i - 1) / 2;

        while (i > 0 && list.get(parent) <= list.get(i))
        {
            long temp = list.get(i);
            list.set(i, list.get(parent));
            list.set(parent, temp);

            i = parent;
            parent = (i - 1) / 2;
        }

    }

    void heapify(int i)
    {
        int leftChild;
        int rightChild;
        int largestChild;

        for (; ; )
        {
            leftChild = 2 * i + 1;
            rightChild = 2 * i + 2;
            largestChild = i;

            if (leftChild < heapSize() && list.get(leftChild) > list.get(largestChild))
            {
                largestChild = leftChild;
            }

            if (rightChild < heapSize() && list.get(rightChild) > list.get(largestChild))
            {
                largestChild = rightChild;
            }

            if (largestChild == i)
            {
                break;
            }

            long temp = list.get(i);
            list.set(i, list.get(largestChild));
            list.set(largestChild, temp);
            i = largestChild;
        }
    }

    long getMax()
    {
        long result = list.get(0);
        list.set(0, list.get(heapSize() - 1));
        list.remove(heapSize() - 1);
        heapify(0);
        return result;
    }

}

public class Chipwi {


    private static void solve(final FastScanner in, final PrintWriter out) {
        int countLine = in.nextInt();
        Heap heap = new Heap();
        for (int i = 0; i < countLine; i++) {
            int comand = in.nextInt();
            switch (comand){
                case 0:
                    heap.add(in.nextInt());
                    break;
                case 1:
                    out.println(heap.getMax());
                    break;
            }
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
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}