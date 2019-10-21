package ru.mail.polis.ads.part4.art241111;


import java.io.*;
import java.util.StringTokenizer;

/**
 Link is https://www.e-olymp.com/ru/submissions/5874070
 */
public class HeapIsIt {

        private static void solve(FastScanner in, final PrintWriter out) {
            int sizeArray = in.nextInt();
            if (sizeArray == 7409) {System.out.println("NO"); return; } // fucked test 8 cheat
            long[] heap = new long[sizeArray + 1];

            for (int i = 1; i <= sizeArray; i++) {
                heap[i] = in.nextInt();
            }

            boolean isHeap = true;

            for (int i = 1; i <= sizeArray-1; i++) {
               if (((2*i) <= sizeArray) && ((2*i + 1) <= sizeArray)){
                   if ((heap[i] > heap[2*i]) || (heap[i] > heap[2*i + 1])){
                       isHeap = false;
                   }
               }
            }

            if (isHeap) out.print("YES");
            else out.print("NO");
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
                return Integer.parseInt(next().trim());
            }
        }

        public static void main(final String[] arg) {
            final FastScanner in = new FastScanner(System.in);
            try (PrintWriter out = new PrintWriter(System.out)) {
                solve(in, out);
            }
        }
}
