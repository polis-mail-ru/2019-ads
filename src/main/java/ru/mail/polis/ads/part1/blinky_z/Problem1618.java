package ru.mail.polis.ads.part1.blinky_z;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Problem 1618.
 * <p>
 * Link: {@code https://www.e-olymp.com/ru/problems/1618}.
 * Tests: {@code https://www.e-olymp.com/ru/submissions/5736502}.
 */
public class Problem1618 {
    private Problem1618() {

    }

    /**
     * Subsequence struct.
     *
     * @implNote We store only size and last char entrance position for the subsequence as we do not need to access the whole subsequence
     * but only last position as we just want to know if we can insert a new element into this subsequence.
     * Also we need to know size of the subsequence to know if there's benefit from changing last entrance pos to the new one, that is we
     * update subsequence's last pos only and only if {@code (new entrance pos)} < {@code lastPos}.
     */
    private static final class Subsequence {
        /**
         * Size of the current subsequence.
         */
        final int size;

        /**
         * Last char entrance position in this subsequence.
         */
        int lastPos;

        Subsequence(int size, int lastPos) {
            this.size = size;
            this.lastPos = lastPos;
        }

        @Override
        public String toString() {
            return "Subsequence{" +
                    "size=" + size +
                    ", lastPos=" + lastPos +
                    '}';
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        LinkedList<Subsequence> subsequences = new LinkedList<>();

        List<Integer> arr1 = new ArrayList<>();
        List<Integer> arr2 = new ArrayList<>();
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            arr1.add(in.nextInt());
        }
        int m = in.nextInt();
        for (int i = 0; i < m; i++) {
            arr2.add(in.nextInt());
        }

        for (int i = 0; i < n; i++) {
            int c = arr1.get(i); // current char
            List<Integer> entrances = new ArrayList<>(); // all entrances of the current char in the other sequence
            for (int ii = 0; ii < arr2.size(); ii++) {
                if (arr2.get(ii) == c) {
                    entrances.add(ii);
                }
            }

            if (entrances.isEmpty()) {
                continue;
            }

            // this is a list with new subsequences we want to try to insert
            List<Subsequence> subsToAdd = new ArrayList<>();
            // we DO NOT want to create a new subsequence with a greater entrance pos than previous, if current size is the same as with
            // previous entrance
            // there's just no point of doing this
            // we want to create a new subsequence with this entrance only and only if size is greater, no matter how entrance pos big is
            int lastInsertedSize = -1;
            // why would we try to insert current char into the subsequence if we did it already with a smaller entrance pos of the same char?
            boolean[] isInsertedInCurrentSub = new boolean[subsequences.size()];
            for (int curEntrancePos : entrances) {
                for (int j = subsequences.size() - 1; j >= 0; --j) {
                    // is there already was insertion into this subsequence?
                    // if true, we don't want to try insert current entrance as it is greater than previous and there's no benefit from doing it
                    if (!isInsertedInCurrentSub[j]) {
                        Subsequence curSub = subsequences.get(j);
                        // as said we can insert a new element only and only if (new entrance pos) < lastPos
                        if (curSub.lastPos < curEntrancePos) {
                            int newSize = curSub.size + 1;
                            // okay, suppose we've inserted this element into this subsequence and got a new subsequence with a bigger size
                            // but have've got a subsequence of such size before? if true, it means that we're trying to harm ourselves as
                            // we get a subsequence of the same size but with the GREATER last pos
                            // as said, we want to create a new subsequence only and only if size is bigger
                            if (newSize > lastInsertedSize) {
                                Subsequence newSub = new Subsequence(newSize, curEntrancePos);
                                subsToAdd.add(newSub);
                                lastInsertedSize = newSize;
                                isInsertedInCurrentSub[j] = true;
                                break;
                            }
                        }
                    }
                }
            }

            // have we found a place to insert one of the entrances?
            // if not, this is a base case: there's just no subsequences present to look up for insertion. So we shall create a new
            // subsequence with the size of 1 and the smallest entrance pos
            if (lastInsertedSize < 0) {
                Subsequence sub = new Subsequence(1, entrances.get(0));
                insertNewSubsequence(subsequences, sub);
            } else {
                for (Subsequence subToAdd : subsToAdd) {
                    insertNewSubsequence(subsequences, subToAdd);
                }
            }
        }

        if (subsequences.size() != 0) {
//            subsequences.forEach(System.out::println);
            System.out.println(subsequences.getLast().size);
        } else {
            System.out.println(0);
        }
    }

    private static void insertNewSubsequence(LinkedList<Subsequence> subsequences, Subsequence newSub) {
        if (subsequences.size() == 0) {
            subsequences.add(newSub);
        }
        for (int i = 0; i < subsequences.size(); i++) {
            Subsequence curSub = subsequences.get(i);
            if (curSub.size == newSub.size) {
                if (newSub.lastPos < curSub.lastPos) {
                    subsequences.set(i, newSub);
                }
                return;
            }
        }
        // we have a subsequence with a greater size than all of the present
        subsequences.add(newSub);
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