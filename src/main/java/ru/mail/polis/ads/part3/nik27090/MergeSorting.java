package ru.mail.polis.ads.part3.nik27090;


import java.io.*;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.TreeSet;

//Задача: "Сортировка слиянием"
//Решение: https://www.e-olymp.com/ru/submissions/5893382

public class MergeSorting {
    private MergeSorting() {
        // Should not be instantiated
    }

    static class Robot{
        int mainNum;
        int supNum;
        private Robot(int mainNum, int supNum){
            this.mainNum=mainNum;
            this.supNum=supNum;
        }
    }

    static class RobotComparator implements Comparator<Robot>{
        public int compare(Robot a, Robot b){
            if(a.mainNum==b.mainNum){
                return 1;
            }
            return a.mainNum-b.mainNum;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        RobotComparator rcomp=new RobotComparator();
        TreeSet<Robot> robots = new TreeSet<>(rcomp);
        for (int i = 0; i < n ; i++) {
            robots.add(new Robot(in.nextInt(),in.nextInt()));
        }
        for (Robot r: robots) {
            out.print(r.mainNum+" "+r.supNum);
            out.println();
        }
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