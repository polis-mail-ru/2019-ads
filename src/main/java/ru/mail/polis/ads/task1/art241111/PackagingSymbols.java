package ru.mail.polis.ads.task1.art241111;

import java.util.Scanner;

/**
 Link to result: https://www.e-olymp.com/ru/submissions/5723841
 */

public class PackagingSymbols {

    private static byte f(byte i)
    {
        if ((i>=10) && (i<100)){return 4;}
        else if (i == 100) {return 5;}
        else return 3;
    }

    private static void fillEntireArray(byte[][] array){
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                array[i][j] = (byte) 0;
            }
        }
    }

    public static void main(String[] args) {
        final int ARRAY_LENGTH = 105;

        char[][][] r = new char[ARRAY_LENGTH][ARRAY_LENGTH][ARRAY_LENGTH];
        byte[][] l = new byte[ARRAY_LENGTH][ARRAY_LENGTH];


        fillEntireArray(l);

        Scanner in = new Scanner(System.in);
        char[] s = ("s" + in.nextLine()).toCharArray();

        int n = s.length - 1;

        for (int j = 1; j <= 4; j++) {
            for (int i = 1; i <= n - j + 1; i++) {
                System.arraycopy(s, i, r[i][j], 1, i + j - i);
                l[i][j] = (byte) j;
            }
        }

        for (int j = 5; j <= n; j++) {
            for (int i = 1; i <= n - j +1; i++) {
                int a = 0;
                for (int z = 1; z <= j-1; z++) {
                    if ((l[i][j] == 0) || (l[i][j] > (l[i][z] + l[i + z][j - z]))){
                        l[i][j] = (byte) (l[i][z]+l[i+z][j-z]);
                        a = z;
                    }
                }

                int b = 0;
                for (int z = 2; z <= j; z++) {
                    if(j%z == 0){
                        boolean bool = true;
                        for (int k = i; k <= i - 1 + j - j / z; k++) {
                            if(s[k] != s[k+j / z])
                            {
                                bool = false;
                                break;
                            }
                        }

                        if (bool){
                            if(l[i][j] >=f((byte) z) + l[i][j / z]){
                                l[i][j] = (byte) (f ((byte) z) + l[i][j / z]);
                                b = z;
                            }
                        }
                    }
                }

                if (b == 0){
                    if (l[i][a] >= 0) System.arraycopy(r[i][a], 1, r[i][j], 1, l[i][a]);
                    if (l[i + a][j - a] >= 0)
                        System.arraycopy(r[i + a][j - a], 1, r[i][j], 1 + l[i][a], l[i + a][j - a]);
                } else{
                    if(b < 10){
                        r[i][j][1] = (char) (b + '0');
                        r[i][j][2] = '(';

                        if (l[i][j / b] >= 0){
                            System.arraycopy(r[i][j / b], 1, r[i][j], 3, l[i][j / b]);
                        }

                        r[i][j][l[i][j/ b]+3] = ')';
                    } else if (b<100){
                        r[i][j][1] =(char) (b / 10 + '0');
                        r[i][j][2] =(char) (b % 10 + '0');
                        r[i][j][3] = '(';

                        if (l[i][j / b] >= 0){
                            System.arraycopy(r[i][j / b], 1, r[i][j], 4, l[i][j / b]);
                        }

                        r[i][j][l[i][j / b]+4] = ')';
                    } else{
                        r[i][j][1] = '1';
                        r[i][j][2] = '0';
                        r[i][j][3] = '0';
                        r[i][j][4] = '(';

                        if (l[i][j / b] >= 0) {
                            System.arraycopy(r[i][j / b], 1, r[i][j], 5, l[i][j / b]);
                        }

                        r[i][j][l[i][j / b]+5] = ')';
                    }
                }

            }
        }
        for (int i = 1; i <=  l[1][n]; i++) {
            System.out.print(r[1][n][i]);
        }
    }
}
