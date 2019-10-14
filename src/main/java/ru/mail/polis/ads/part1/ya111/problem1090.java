package ru.mail.polis.ads.part1.ya111;

import java.util.Scanner;
//https://www.e-olymp.com/ru/submissions/5857677
public final class problem1090 {
    private problem1090(){
    }

    public static void main(final String[] argc) {
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            String str = sc.next();
            String[][] shortstr = new String[str.length()][str.length()];
            for (int i = 1; i <=str.length(); ++i) {
                for (int left = 0; left < str.length() - i +1; ++left) {
                    int right = left + i -1;
                    String min = str.substring(left, right +1);
                    if (i > 4) {
                        for (int j = left; j < right; ++j) {
                            String curshortstr = shortstr[left][j] + shortstr[j + 1][right];
                            if (curshortstr.length() < min.length()) {
                                min = curshortstr;
                            }
                        }
                        for (int period = 1; period <= i / 2; ++period) {
                            if (i % period == 0) {
                                boolean isPeriod = true;
                                for (int k = left + period; k <= right; ++k) {
                                    if (str.charAt(k) != str.charAt(k - period)) {
                                        isPeriod = false;
                                        break;
                                    }
                                }
                                if (isPeriod) {
                                    String curshortstr = (i / period) + "(" + shortstr[left][left + period - 1] + ")";
                                    if (curshortstr.length() < min.length()) {
                                        min = curshortstr;
                                    }
                                }
                            }
                        }
                    }
                    shortstr[left][right] = min;
                }
            }
            System.out.println(shortstr[0][str.length() - 1]);


        }
    }
}
