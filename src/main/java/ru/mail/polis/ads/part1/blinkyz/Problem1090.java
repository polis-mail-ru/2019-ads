package ru.mail.polis.ads.part1.blinkyz;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Problem1090 {
    private Problem1090() {

    }

    private static boolean containsAlphanumerics(final String s) {
        for (int i = 0; i < s.length(); i++) {
            if (Character.isLetterOrDigit(s.charAt(i))) return true;
        }
        return false;
    }

    private static final class Result {
        final String stringWithMaxRepeat;

        final String stringWithSmallerRepeat;

        Result(final String stringWithMaxRepeat, final String stringWithSmallerRepeat) {
            this.stringWithMaxRepeat = stringWithMaxRepeat;
            this.stringWithSmallerRepeat = stringWithSmallerRepeat;
        }

        @Override
        public String toString() {
            return "Result{"
                    + "sWithMaxRepeat='" + stringWithMaxRepeat + '\''
                    + ", sWithSmallerRepeat='" + stringWithSmallerRepeat + '\''
                    + '}';
        }
    }

    private static Result buildResult(final int maxRepeats, final int startIndex,
                                      final String source, final String rep) {
        final int replaceLen = (maxRepeats + 1) * rep.length();

        if (maxRepeats != 1) {
            final StringBuilder res = new StringBuilder();
            final StringBuilder resSmaller = new StringBuilder();

            int index = 0;
            while (index < source.length()) {
                if (index == startIndex) {
                    res.append(maxRepeats + 1).append('(').append(rep).append(')');
                    resSmaller.append(maxRepeats).append('(').append(rep).append(')');
                    resSmaller.append(rep);
                    index = index + replaceLen - 1;
                    continue;
                }
                res.append(source.charAt(index));
                resSmaller.append(source.charAt(index));
                ++index;
            }
            return new Result(res.toString(), resSmaller.toString());
        }

        final StringBuilder res = new StringBuilder();
        int index = 0;
        while (index < source.length()) {
            if (index == startIndex) {
                res.append(maxRepeats + 1).append('(').append(rep).append(')');
                index = index + replaceLen - 1;
                continue;
            }
            res.append(source.charAt(index));
            ++index;
        }
        return new Result(res.toString(), null);
    }

    private static int countRepeats(final String source, final String rep, final int startPos) {
        int from = startPos;
        int repeats = 0;
        final int sourceLength = source.length();
        final int repLength = rep.length();
        while (source.substring(from, Math.min(from + repLength, sourceLength)).equals(rep)) {
            repeats++;
            from = from + repLength;
        }
        return repeats;
    }

    private static Result findMaxRepeat(final String s) {
        int repeats;
        int maxRepeats = 0;
        int maxLen = 0;
        int startIndex = -1;
        String rep = "";
        for (int i = 1; i < s.length(); i++) {
            final String sub = s.substring(0, i);
            int from;
            for (int j = 0; j < sub.length(); j++) {
                final String curRep = sub.substring(j);
                if (!containsAlphanumerics(curRep)) {
                    break;
                }
                from = i;
                repeats = countRepeats(s, curRep, from);
                if (repeats == 0) {
                    continue;
                }

                final int curLen = (repeats + 1) * curRep.length();
                if (curLen > 4 && curLen > maxLen) {
                    maxLen = curLen;
                    startIndex = i - curRep.length();
                    rep = curRep;
                    maxRepeats = repeats;
                }
            }
        }

        return buildResult(maxRepeats, startIndex, s, rep);
    }

    private static String tryCurrentS(final String s) {
        final List<String> stringsToTry = new ArrayList<>();

        String s1 = s;

        while (true) {
            final Result result = findMaxRepeat(s1);
            final String curRes = result.stringWithMaxRepeat;
            if (curRes.equals(s1)) {
                break;
            }
            if (result.stringWithSmallerRepeat != null) {
                stringsToTry.add(result.stringWithSmallerRepeat);
            }

            s1 = curRes;
        }

        String minLenRes = s1;
        for (final String curStringToTry : stringsToTry) {
            final String curRes = tryCurrentS(curStringToTry);
            if (curRes.length() < minLenRes.length()) {
                minLenRes = curRes;
            }
        }

        return minLenRes;
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        out.println(tryCurrentS(in.next()));
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
