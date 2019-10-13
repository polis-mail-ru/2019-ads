    package ru.mail.polis.ads.part2;

    import java.io.*;

    /**
     * Problem solution template.
     */

    public final class Task3 {

        private static int index;

        private Task3() {
            // Should not be instantiated
        }

        private static void getLevels(char[] expr, int depth, StringBuilder[] answer) {
            answer[depth].append(expr[index]);
            index--;
            if (Character.isUpperCase(expr[index + 1])) {
                getLevels(expr, depth + 1, answer);
                getLevels(expr, depth + 1, answer);
            }
        }

        private static void solve(final BufferedReader in, final BufferedWriter out) throws IOException {
            int n = Integer.parseInt(in.readLine());

            for (int k = 0; k < n; k++) {
                String str = in.readLine();
                char[] expr = str.toCharArray();
                StringBuilder[] answer = new StringBuilder[expr.length];
                for (int i = 0; i < expr.length; i++) {
                    answer[i] = new StringBuilder();
                }

                index = expr.length - 1;
                getLevels(expr, 0, answer);
                for (int j = expr.length - 1; j >= 0; j--) {
                    out.write(answer[j].toString());
                }
                out.write("\n");
            }
        }

        public static void main(final String[] arg) {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out))) {
                solve(in, out);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
