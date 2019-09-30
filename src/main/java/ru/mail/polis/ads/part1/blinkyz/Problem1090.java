package ru.mail.polis.ads.part1.blinkyz;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Problem1090 {
    private Problem1090() {

    }

    private static boolean containsAlphanumerics(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (Character.isLetterOrDigit(s.charAt(i))) return true;
        }
        return false;
    }

    private static final class Result {
        final String sWithMaxRepeat;

        final String sWithSmallerRepeat;

        Result(String sWithMaxRepeat, String sWithSmallerRepeat) {
            this.sWithMaxRepeat = sWithMaxRepeat;
            this.sWithSmallerRepeat = sWithSmallerRepeat;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "sWithMaxRepeat='" + sWithMaxRepeat + '\'' +
                    ", sWithSmallerRepeat='" + sWithSmallerRepeat + '\'' +
                    '}';
        }
    }

    private static Result findMaxRepeat(final String s) {
        int repeats = 0, maxRepeats = 0;
        int maxLen = 0;
        int startIndex = -1;
        String rep = "";
        for (int i = 1; i < s.length(); i++) {
            String sub = s.substring(0, i);
            int from;
            for (int j = 0; j < sub.length(); j++) {
                String cur = sub.substring(j, sub.length());
                if (!containsAlphanumerics(cur)) {
                    break;
                }
                from = i;
                repeats = 0;
                while (s.substring(from, Math.min(from + cur.length(), s.length())).equals(cur)) {
                    repeats++;
                    from = from + cur.length();
                }
                if (repeats != 0) {
                    int curLen = (repeats + 1) * cur.length();
                    if (curLen > 4 && curLen > maxLen) {
                        maxLen = curLen;
                        startIndex = i - cur.length();
                        rep = cur;
                        maxRepeats = repeats;
                    }
                }
            }
        }

        if (maxRepeats != 1) {
            StringBuilder res = new StringBuilder();
            StringBuilder resSmaller = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                if (i == startIndex) {
                    res.append(maxRepeats + 1).append('(').append(rep).append(')');
                    resSmaller.append(maxRepeats).append('(').append(rep).append(')');
                    resSmaller.append(rep);
                    i = i + maxLen - 1;
                    continue;
                }
                res.append(s.charAt(i));
                resSmaller.append(s.charAt(i));
            }
            return new Result(res.toString(), resSmaller.toString());
        } else {
            StringBuilder res = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                if (i == startIndex) {
                    res.append(maxRepeats + 1).append('(').append(rep).append(')');
                    i = i + maxLen - 1;
                    continue;
                }
                res.append(s.charAt(i));
            }
            return new Result(res.toString(), null);
        }
    }

    private static String tryCurrentS(String s) {
        List<String> stringsToTry = new ArrayList<>();

        String s1 = s;

        while (true) {
            Result result = findMaxRepeat(s1);
            String curRes = result.sWithMaxRepeat;
            if (curRes.equals(s1)) {
                break;
            }
            if (result.sWithSmallerRepeat != null) {
                stringsToTry.add(result.sWithSmallerRepeat);
            }

            s1 = curRes;
        }

        String minLenRes = s1;
        for (String curStringToTry : stringsToTry) {
            String curRes = tryCurrentS(curStringToTry);
            if (curRes.length() < minLenRes.length()) {
                minLenRes = curRes;
            }
        }

        return minLenRes;
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        System.out.println(tryCurrentS(in.next()));
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
