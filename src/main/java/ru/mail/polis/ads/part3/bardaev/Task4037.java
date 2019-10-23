package ru.mail.polis.ads.part3.bardaev;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Task4037 {
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
    public static void solve(FastScanner in, PrintWriter out) {
//        Scanner scan = new Scanner(System.in);
//        int n = scan.nextInt();
        int n = in.nextInt();
        Bot[] bot = new Bot[n];

        for (int i = 0; i < n; i++) {
            bot[i] = new Bot(in.nextInt(), in.nextInt());
        }

        sortArray(0, bot.length, bot);

        for (int i = 0; i < bot.length; i++) {
            out.println(bot[i].getNums());
        }
        out.flush();
    }

    public static void sortArray(int start, int end, Bot[] arr) {
        if (end - start < 2) return;

        int mid = (start + end) / 2;

        sortArray(start, mid, arr);
        sortArray(mid, end, arr);
        mergeArray(start, mid, end, arr);
    }

    public static void mergeArray(int start, int mid, int end, Bot[] arr) {
        Bot[] left = null, right = null;
        left = Arrays.copyOfRange(arr, start, mid);
        right = Arrays.copyOfRange(arr, mid, end);
        int posLeft = 0;
        int posRight = 0;

        for (int i = start; i < end; i++) {
            if (posLeft == left.length) {
                arr[i] = right[posRight];
                posRight++;
            } else if (posRight == right.length) {
                arr[i] = left[posLeft];
                posLeft++;
            } else if (left[posLeft].n1 <= right[posRight].n1) {
                arr[i] = left[posLeft];
                posLeft++;
            } else {
                arr[i] = right[posRight];
                posRight++;
            }
        }
    }

    private static class Bot {
        int n1;
        int n2;

        Bot(int a, int b) {
            this.n1 = a;
            this.n2 = b;
        }

        public String getNums() {
            return n1 + " " + n2;
        }
    }
}
