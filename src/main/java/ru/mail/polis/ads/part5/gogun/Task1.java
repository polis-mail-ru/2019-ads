package ru.mail.polis.ads.part5.gogun;

import java.io.*;

public class Task1 {

        final static double E = 0.000001;

        private static double f(double x) {
            return x*x + Math.sqrt(x);
        }

        private static void binSearch (double c) {
            double left = 0;
            double right = c;
            double x, y;
            do {
                x = (left + right) / 2;
                y = f(x);
                if (y > c) {
                    right = x;
                } else {
                    left = x;
                }
            } while (Math.abs(y - c) > E);
            System.out.println(String.format("%.6f", x));
        }


        private static void solve(BufferedReader input) {
            try {
                double c = Double.parseDouble(input.readLine());

                binSearch(c);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public static void main(String[] args) {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            solve(in);
        }
    }
