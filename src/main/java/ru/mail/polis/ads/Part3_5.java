import java.io.*;
import java.util.StringTokenizer;
//https://www.e-olymp.com/ru/submissions/5914245
class Part3_5 {

    private static void solve(final FastScanner in, final PrintWriter out) {
        int count = in.nextInt();
        ToothBrush[] arr = new ToothBrush[count];
        for (int i = 0; i < count; i++) {
            arr[i] = new ToothBrush();
            arr[i].mainNumber = in.nextInt();
            arr[i].secondNumber = in.nextInt();
        }
        mergeSort(arr, count);
        for (int i = 0; i < count; i++) {
            out.println(arr[i].mainNumber + " " + arr[i].secondNumber);
        }
    }

    private static class ToothBrush {
        private int mainNumber;
        private int secondNumber;
    }

    private static void mergeSort(ToothBrush[] arr, int arrLength) {
        if (arrLength < 2) {
            return;
        }
        int mid = arrLength / 2;
        ToothBrush[] lArr = new ToothBrush[mid];
        ToothBrush[] rArr = new ToothBrush[arrLength - mid];
        for (int i = 0; i < mid; i++) {
            lArr[i] = arr[i];
        }
        for (int i = mid; i < arrLength; i++) {
            rArr[i - mid] = arr[i];
        }
        mergeSort(lArr, mid);
        mergeSort(rArr, arrLength - mid);

        merge(arr, lArr, rArr, mid, arrLength - mid);
    }

    public static void merge(ToothBrush[] arr, ToothBrush[] lArr, ToothBrush[] rArr, int left, int right) {

        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (lArr[i].mainNumber <= rArr[j].mainNumber) {
                arr[k++] = lArr[i++];
            } else {
                arr[k++] = rArr[j++];
            }
        }
        while (i < left) {
            arr[k++] = lArr[i++];
        }
        while (j < right) {
            arr[k++] = rArr[j++];
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