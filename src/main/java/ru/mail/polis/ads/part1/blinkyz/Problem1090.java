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

            int i = 0;
            while (i < source.length()) {
                if (i == startIndex) {
                    res.append(maxRepeats + 1).append('(').append(rep).append(')');
                    resSmaller.append(maxRepeats).append('(').append(rep).append(')');
                    resSmaller.append(rep);
                    i = i + replaceLen - 1;
                    continue;
                }
                res.append(source.charAt(i));
                resSmaller.append(source.charAt(i));
                ++i;
            }
            return new Result(res.toString(), resSmaller.toString());
        }

        final StringBuilder res = new StringBuilder();
        int i = 0;
        while (i < source.length()) {
            if (i == startIndex) {
                res.append(maxRepeats + 1).append('(').append(rep).append(')');
                i = i + replaceLen - 1;
                continue;
            }
            res.append(source.charAt(i));
            ++i;
        }
        return new Result(res.toString(), null);
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
                final String cur = sub.substring(j);
                if (!containsAlphanumerics(cur)) {
                    break;
                }
                from = i;
                repeats = 0;
                while (s.substring(from, Math.min(from + cur.length(), s.length())).equals(cur)) {
                    repeats++;
                    from = from + cur.length();
                }

                if (repeats == 0) {
                    continue;
                }
                final int curLen = (repeats + 1) * cur.length();
                if (curLen > 4 && curLen > maxLen) {
                    maxLen = curLen;
                    startIndex = i - cur.length();
                    rep = cur;
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
        for (String curStringToTry : stringsToTry) {
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
