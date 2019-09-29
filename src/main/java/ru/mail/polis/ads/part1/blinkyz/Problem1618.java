package ru.mail.polis.ads.part1.blinkyz;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Problem 1618.
 * Link: {@code https://www.e-olymp.com/ru/problems/1618}.
 * Tests: {@code https://www.e-olymp.com/ru/submissions/5736502}.
 */
public class Problem1618 {
    private Problem1618() {

    }

    /**
     * Subsequence struct.
     *
     * @implNote We store only size and last char entrance position for the subsequence as we do not need to access
     * the whole subsequence but only last position as we just want to know if we can insert a new element
     * into this subsequence.
     * Also we need to know size of the subsequence to know if there's benefit from changing last
     * entrance pos to the new one, that is we update subsequence's last pos only and
     * only if {@code (new entrance pos)} < {@code lastPos}.
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

        Subsequence(final int size, final int lastPos) {
            this.size = size;
            this.lastPos = lastPos;
        }

        @Override
        public String toString() {
            return "Subsequence{"
                    + "size=" + size
                    + ", lastPos=" + lastPos
                    + '}';
        }
    }

    /**
     * This function attempts to insert every entrance into all subsequences.
     * We can insert entrance if and only if last pos in this subsequence is smaller than current entrance pos.
     * More over, we don't want to insert entrance into the subsequence, if we got subsequence of the same size as
     * in previous insertions, because entrances are sorted in ascending order and we want to support last pos of
     * subsequence as small as possible.
     *
     * @param subsequences subsequences
     * @param entrances    entrances of the current char
     * @return new subsequences in which we've inserted one of the entrances
     */
    private static List<Subsequence> insert(final LinkedList<Subsequence> subsequences,
                                            final List<Integer> entrances) {
        final List<Subsequence> subsToAdd = new ArrayList<>();

        // we DO NOT want to create a new subsequence with a greater entrance pos than previous, if current size is
        // the same as with previous entrance
        // there's just no point of doing this
        // we want to create a new subsequence with this entrance only and only if size is greater,
        // no matter how entrance pos big is
        int lastInsertedSize = -1;
        // why would we try to insert current char into the subsequence if we did it already with a smaller entrance
        // pos of the same char?
        final boolean[] isInsertedInCurrentSub = new boolean[subsequences.size()];
        for (final int curEntrancePos : entrances) {
            for (int j = subsequences.size() - 1; j >= 0; --j) {
                // is there already was insertion into this subsequence?
                // if true, we don't want to try insert current entrance as it is greater than previous and there's
                // no benefit from
                // doing it
                if (!isInsertedInCurrentSub[j]) {
                    final Subsequence curSub = subsequences.get(j);
                    // as said we can insert a new element only and only if new entrance pos < lastPos
                    if (curSub.lastPos < curEntrancePos && (curSub.size + 1) > lastInsertedSize) {
                        final int newSize = curSub.size + 1;
                        final Subsequence newSub = new Subsequence(newSize, curEntrancePos);
                        subsToAdd.add(newSub);
                        lastInsertedSize = newSize;
                        isInsertedInCurrentSub[j] = true;
                        break;
                    }
                }
            }
        }

        return subsToAdd;
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        final LinkedList<Subsequence> subsequences = new LinkedList<>();

        final List<Integer> arr1 = new ArrayList<>();
        final List<Integer> arr2 = new ArrayList<>();
        final int nSize = in.nextInt();
        for (int i = 0; i < nSize; i++) {
            arr1.add(in.nextInt());
        }
        final int mSize = in.nextInt();
        for (int i = 0; i < mSize; i++) {
            arr2.add(in.nextInt());
        }

        for (int i = 0; i < nSize; i++) {
            final int curChar = arr1.get(i); // current char
            final List<Integer> entrances = new ArrayList<>(); // all char entrances in the other sequence
            for (int ii = 0; ii < arr2.size(); ii++) {
                if (arr2.get(ii) == curChar) {
                    entrances.add(ii);
                }
            }

            if (entrances.isEmpty()) {
                continue;
            }

            // this is a list with new subsequences we want to try to insert
            final List<Subsequence> subsToAdd = insert(subsequences, entrances);

            // have we found a place to insert one of the entrances?
            // if not, this is a base case: there's just no subsequences present to look up for insertion
            // so we shall create a new subsequence with the size of 1 and the smallest entrance pos
            if (subsToAdd.isEmpty()) {
                subsToAdd.add(new Subsequence(1, entrances.get(0)));
            }

            for (Subsequence subToAdd : subsToAdd) {
                insertNewSubsequence(subsequences, subToAdd);
            }
        }

        if (!subsequences.isEmpty()) {
            out.println(subsequences.getLast().size);
            return;
        }

        out.println(0);
    }

    private static void insertNewSubsequence(LinkedList<Subsequence> subsequences, Subsequence newSub) {
        for (int i = 0; i < subsequences.size(); i++) {
            final Subsequence curSub = subsequences.get(i);
            if (curSub.size == newSub.size) {
                if (newSub.lastPos < curSub.lastPos) {
                    subsequences.set(i, newSub);
                }
                return;
            }
        }
        // we have a subsequence with a greater size than all of the present or we have empty subsequences list
        subsequences.add(newSub);
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}