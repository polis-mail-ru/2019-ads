import java.io.*;
import java.util.StringTokenizer;

class Part1_3 {

    private static void solve(final FastScanner in, final PrintWriter out) {
        String sequence = in.next();
        int n = sequence.length();
        String[][] d = new String[n][n];
        for (int j = 0; j < n; j++) {
            for (int i = j; i >= 0; i--) {
                packSubsequence(sequence, i, j, d);
            }
        }
        out.println(d[0][n - 1]);
    }

    private static void packSubsequence(String sequence, int i, int j, String[][] d) {
        if (j - i + 1 <= 4) {
            d[i][j] = sequence.substring(i, j+1);
            return;
        }
        String sub = sequence.substring(i, j+1);
        int minLength = Integer.MAX_VALUE;
        String minSequence = "";
        for (int k = i; k < j; k++) {
            if ((d[i][k].length() + d[k+1][j].length()) <= minLength) {
                minSequence = d[i][k] + d[k+1][j];
                minLength = minSequence.length();
            }
        }
        String repeated = getRepeated(sequence, sub, i, j, d);
        d[i][j] = minSequence.length() < repeated.length() ? minSequence : repeated;
    }

    private static String getRepeated(String sequence, String substring, int begin, int end, String[][] d) {
        int counter;
        String packed;
        int minLength = Integer.MAX_VALUE;
        String minSequence = substring;
        for (int j = 1; j <= substring.length() / 2; j++) {
            counter = substring.length() / j;
            if (substring.length() % j == 0) {
                for (int i = begin; i <= end - j; i++) {
                    if (sequence.charAt(i) != sequence.charAt(i + j)) {
                        counter = 0;
                        break;
                    }
                }
            } else {
                counter = 0;
            }
            if (counter > 0) {
                packed = getPacking(d[begin][begin + j - 1], substring.length() / j);
                if (packed.length() < minLength) {
                    minSequence = packed;
                    minLength = minSequence.length();
                }
            }
        }
        return minSequence;
    }

    private static String getPacking(String s, int strLength) {
        if (strLength == 1) {
            return s;
        }
        StringBuilder builder = new StringBuilder();
        builder.append(strLength);
        builder.append('(');
        builder.append(s);
        builder.append(')');
        return builder.toString();
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
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}