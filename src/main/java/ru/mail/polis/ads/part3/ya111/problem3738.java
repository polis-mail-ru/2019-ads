import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public final class problem3738 {


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

    public static void mergeSort(int[] Array, int n) {
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        int[] left = new int[mid];
        int[] right = new int[n - mid];

        for (int i = 0; i < mid; i++) {
            left[i] = Array[i];
        }
        for (int i = mid; i < n; i++) {
            right[i - mid] = Array[i];
        }
        mergeSort(left, mid);
        mergeSort(right, n - mid);

        merge(Array, left, right, mid, n - mid);
    }

    public static void merge(
            int[] Array, int[] l, int[] r, int left, int right) {

        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i] <= r[j]) {
                Array[k++] = l[i++];
            }
            else {
                Array[k++] = r[j++];
            }
        }
        while (i < left) {
            Array[k++] = l[i++];
        }
        while (j < right) {
            Array[k++] = r[j++];
        }
    }


    public static void main(String[] args) throws IOException {
        final FastScanner sc = new FastScanner(System.in);
        int n = sc.nextInt();
        int [] Array = new int[n];
        for (int i = 0; i < n; ++i)
            Array[i] = sc.nextInt();

        mergeSort(Array, n);

        for (int i = 0; i < n; ++i)
            System.out.print(Array[i]+ " ");


    }
}