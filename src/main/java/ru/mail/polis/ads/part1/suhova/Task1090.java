package ru.mail.polis.ads.part1.suhova;

import java.io.*;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class Task1090 {
    //1090
    private static void solve(final Task1090.FastScanner in, final PrintWriter out) {
        // Write me
        String str = in.next();
        out.println(wrap(str));
        out.flush();
    }

    public static void main(final String[] arg) {
        final Task1090.FastScanner in = new Task1090.FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }

    private static String wrap(String str) {
        String newstr=str;
        for (int i=str.length() / 2 ; i>1; i--) {
            for (int j = 0; j < str.length() - i-1; j++) {
                str= wrapper(str,str.substring(j,j+i));
                if (str.length()<newstr.length()){
                    newstr=str;
                }
            }
        }
        return newstr;
    }

    private static String wrapper(String str, String sub) {
        String min = str;
        for (int n = str.length() / sub.length(); n > 1; n--) {
            if(str.contains(repeat(sub,n))) {
                String newsub=sub;
                for (int i=str.length() / 2 ; i>1; i--) {
                    for (int j = 0; j <sub.length() - i; j++) {
                        newsub= wrapper(sub,sub.substring(j,j+i));
                        String newstr = str.replaceAll( Pattern.quote(repeat(sub,n)),n + "(" + sub + ")");
                         System.out.println(newstr);
                        if (min.length()<newstr.length()){
                            System.out.println("!!!!!!!!!!!!!!!!!!!!");
                            min=newstr;
                        }
                    }
                }

            }
        }
        return min;
    }
    private static String repeat(String str, int n){
        String res="";
        for(int i=0;i<n;i++) res+=str;
        return res;
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
}
