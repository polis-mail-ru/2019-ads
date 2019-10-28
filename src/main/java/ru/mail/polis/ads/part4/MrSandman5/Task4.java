package part4;

// https://www.e-olymp.com/ru/submissions/5959570

import java.io.*;
import java.util.StringTokenizer;

public class Task4 {

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        long[] array = new long[50001];
        for (int i = 0; i < n; i++) {
            array[i] = in.nextLong();
        }
        out.println(getInvCount(array, n));
    }

    private static int getInvCount(long[] arr, int array_size)
    {
        long[] temp = new long[array_size];
        return mergeSort(arr, temp, 0, array_size - 1);
    }

    private static int mergeSort(long[] arr, long[] temp, int left, int right)
    {
        int mid, inv_count = 0;
        if (right > left) {
            mid = (right + left) / 2;

            inv_count = mergeSort(arr, temp, left, mid);
            inv_count += mergeSort(arr, temp, mid + 1, right);

            inv_count += merge(arr, temp, left, mid + 1, right);
        }
        return inv_count;
    }

    private static int merge(long[] arr, long[] temp, int left, int mid, int right)
    {
        int i, j, k;
        int inv_count = 0;

        i = left;
        j = mid;
        k = left;
        while ((i <= mid - 1) && (j <= right)) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            }
            else {
                temp[k++] = arr[j++];

                inv_count = inv_count + (mid - i);
            }
        }

        while (i <= mid - 1)
            temp[k++] = arr[i++];

        while (j <= right)
            temp[k++] = arr[j++];

        for (i = left; i <= right; i++)
            arr[i] = temp[i];

        return inv_count;
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
