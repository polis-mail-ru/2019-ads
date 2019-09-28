package ru.mail.polis.ads.part1.Kokobox7;

import java.io.*;
import java.util.StringTokenizer;

public final class Task2 {

    private static void solve(final FastScanner in, final PrintWriter out) {
        String input = in.next();
        if (input.isEmpty())
            out.print("");
        //index of the encountered opening parenthesis - (
        int ixOpeningParenthesis = -1;
        int ixOpeningBracket = -1;
        StringBuilder outputBuilder = new StringBuilder();
        outputBuilder.append(input);

        char cur_char;
        int offset = 1;
        for (int i = 0; i < outputBuilder.length(); i++) {
            cur_char = outputBuilder.charAt(i);
            switch (cur_char) {
                case '(':
                    if (ixOpeningParenthesis == -1) {
                        ixOpeningParenthesis = i;
                        //outputBuilder.append('(');
                    } else {
                        outputBuilder.insert(ixOpeningParenthesis + 1, ")");
                        //outputBuilder.insert(outputBuilder.lastIndexOf("(") + 1, ")");
                        //offset += 1;
                        //outputBuilder.append('(');
                        ixOpeningParenthesis = i;
                    }
                    break;
                case ')':
                    if (ixOpeningParenthesis == -1) {
                        //outputBuilder.append("(");
                        outputBuilder.insert(i, "(");
                    } else {
                        //outputBuilder.append(')');
                        ixOpeningParenthesis = -1;
                    }
                    break;
                case '[':
                    if (ixOpeningBracket == -1) {
                        ixOpeningBracket = i;
                        outputBuilder.append('[');
                    } else {
                        outputBuilder.insert(ixOpeningBracket + 1, "]");
                        //outputBuilder.insert(outputBuilder.indexOf("[", i) + 1, "]");
                        offset += 1;
                        outputBuilder.append('(');
                        ixOpeningBracket = i;
                    }
                    break;
                case ']':
                    if (ixOpeningBracket == -1) {
                        //outputBuilder.append("[]");
                        outputBuilder.insert(i, "[");
                    } else {
                        //outputBuilder.append(']');
                        ixOpeningBracket = -1;
                    }
                    break;
            }
        }
        if (ixOpeningParenthesis != -1) {
            outputBuilder.insert(outputBuilder.lastIndexOf("(") + 1, ")");
        }
        if (ixOpeningBracket != -1) {
            outputBuilder.insert(outputBuilder.lastIndexOf("[") + 1, "]");
        }
        out.print(outputBuilder.toString());
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
