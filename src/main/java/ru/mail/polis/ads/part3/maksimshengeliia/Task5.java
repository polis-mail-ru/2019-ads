package ru.mail.polis.ads.part3.maksimshengeliia;

import java.io.*;
import java.util.StringTokenizer;

/*
*   https://www.e-olymp.com/ru/submissions/5897385
* */
public class Task5 {
    private Task5() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        Pair[] list = new Pair[n];
        for (int i = 0; i < n; i++) {
            Pair p = new Pair(in.nextInt(), in.nextInt(), i);
            list[i] = p;
        }

        mergeSort(list);
        for (int i = 0; i < n; i++) {
            out.println(list[i].first + " " + list[i].second);
        }

    }

    private static void mergeSort(Pair[] list)
    {
        //If list is empty; no need to do anything
        if (list.length <= 1) {
            return;
        }

        //Split the array in half in two parts
        Pair[] first = new Pair[list.length / 2];
        Pair[] second = new Pair[list.length - first.length];
        System.arraycopy(list, 0, first, 0, first.length);
        System.arraycopy(list, first.length, second, 0, second.length);

        //Sort each half recursively
        mergeSort(first);
        mergeSort(second);

        //Merge both halves together, overwriting to original array
        merge(first, second, list);
    }

    private static void merge(Pair[] first, Pair[] second, Pair[] result)
    {
        //Index Position in first array - starting with first element
        int iFirst = 0;

        //Index Position in second array - starting with first element
        int iSecond = 0;

        //Index Position in merged array - starting with first position
        int iMerged = 0;

        //Compare elements at iFirst and iSecond,
        //and move smaller element at iMerged
        while (iFirst < first.length && iSecond < second.length)
        {
            if (first[iFirst].compareTo(second[iSecond]) < 0)
            {
                result[iMerged] = first[iFirst];
                iFirst++;
            }
            else
            {
                result[iMerged] = second[iSecond];
                iSecond++;
            }
            iMerged++;
        }
        //copy remaining elements from both halves - each half will have already sorted elements
        System.arraycopy(first, iFirst, result, iMerged, first.length - iFirst);
        System.arraycopy(second, iSecond, result, iMerged, second.length - iSecond);
    }


    public static class Pair implements Comparable<Pair> {
        int first;
        int second;
        int index;

        public Pair(int first, int second, int index) {
            this.first = first;
            this.second = second;
            this.index = index;
        }

        @Override
        public int compareTo(Pair o) {
            if (this.first - o.first != 0) return this.first - o.first;
            else return this.index - o.index;
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
