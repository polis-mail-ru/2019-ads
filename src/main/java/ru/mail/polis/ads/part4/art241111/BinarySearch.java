package ru.mail.polis.ads.part4.art241111;

import java.io.*;
import java.util.StringTokenizer;

/**
 * Link is https://www.e-olymp.com/ru/submissions/5914770
 */
class Array{
    private int[] array;

    Array(int size){
        array = new int[size];
    }

    void add(int num, int position){
        array[position] = num;
    }


    boolean search(int num){

        int comparisonCount = 1;
        int first = 0;
        int last = array.length;


        int position = (first + last) / 2;

        while ((array[position] != num) && (first <= last)) {
            comparisonCount++;
            if (array[position] > num) {
                last = position - 1;
            } else {
                first = position + 1;
                if (first == array.length) return false;
            }
            position = (first + last) / 2;
        }
        if (first <= last) {
            return true;
        } else {
            return false;
        }

    }
}
public class BinarySearch {
    private static void solve(FastScanner in, final PrintWriter out) {
        int sizeArray = in.nextInt();
        int countSearch = in.nextInt();

        StringBuffer outString = new StringBuffer();
        Array array = new Array(sizeArray);

        for (int i = 0; i < sizeArray; i++) {
            array.add(in.nextInt(), i);
        }

        for (int i = 0; i < countSearch; i++) {
            if (array.search(in.nextInt())) {
                outString.append("YES").append("\n");
            } else {
                outString.append("NO").append("\n");
            } ;
        }
        out.print(outString);
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
