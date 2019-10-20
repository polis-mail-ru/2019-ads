package ru.mail.polis.ads.part3.kokobox7;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

public class MergeSort {
    static class Robot {
        private int mainNumber;
        private int auxiliaryNumber;

        Robot(int mainNumber, int auxiliaryNumber) {
            this.mainNumber = mainNumber;
            this.auxiliaryNumber = auxiliaryNumber;
        }
    }

    private static class RobotComparator implements Comparator<Robot> {
        @Override
        public int compare(Robot r1, Robot r2) {
            if (r1.mainNumber == r2.mainNumber) {
                return 0;
            }
            return r1.mainNumber - r2.mainNumber > 0 ? 1 : -1;
        }
    }

    private static void solve(final BufferedReader in, final PrintWriter out) {
        try {
            int n = Integer.parseInt(in.readLine());
            Robot[] robots = new Robot[n];

            for (int i = 0; i < n; i++) {
                String[] inputs;
                inputs = in.readLine().split(" ");
                Robot input = new Robot(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]));
                robots[i] = input;
            }

            Arrays.sort(robots, new RobotComparator());

            StringBuilder stringBuilder = new StringBuilder(20);
            for (Robot r : robots) {
                stringBuilder.append(r.mainNumber).append(" ").append(r.auxiliaryNumber).append('\n');
            }
            out.println(stringBuilder.toString());
            out.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(final String[] arg) {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        solve(in, out);
    }
}
