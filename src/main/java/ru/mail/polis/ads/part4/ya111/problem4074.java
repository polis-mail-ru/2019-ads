import java.io.*;
import java.util.*;


public class problem4074 {
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
        final InputStreamReader input = new InputStreamReader(System.in);
        final BufferedReader in = new BufferedReader(input);
        int n;
        ArrayList<Integer> str=new ArrayList<Integer>();
        while (true) {
            try {
                n = Integer.parseInt(in.readLine());
            } catch (Exception e) {
                break;
            }
            boolean check = true;
            for (int i = 0; i < str.size(); ++i) {
                if (n < str.get(i)) {
                    str.add(i, n);
                    i=str.size();
                    check = false;
                }
            }
            if (check == true)
                str.add(n);
            //for (int i = 0; i< str.size(); ++i)
             //   System.out.println(str.get(i));
            if (str.size() % 2 == 0) {
                int f = str.get(str.size() / 2);
                int m = str.get(str.size() / 2 - 1);
                f = (f + m) / 2;
                System.out.println(f);
            }
            else {
                System.out.println (str.get(str.size() / 2 ));
            }
        }

    }
}
