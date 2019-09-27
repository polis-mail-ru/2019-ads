package ru.mail.polis.ads.task1.art241111;

import java.io.PrintWriter;
import java.util.Scanner;

/**
 Link to result: https://www.e-olymp.com/ru/submissions/5720246
 */

public class GreatestCommonSubsequence {

     public static void main(String[] args)
        {
            // Initialization some instruments
            Scanner in = new Scanner(System.in);
            PrintWriter out = new PrintWriter(System.out);

            // Reading data
            // first line
            int howNumberFirstLine = in.nextInt();
            int[] firstLine = new int[howNumberFirstLine+1];

            for(int i = 1; i <= howNumberFirstLine; i++){
                firstLine[i] = in.nextInt();
            }

            // second line
            int howNumberSecondLine = in.nextInt();
            int[] secondLine = new int[howNumberSecondLine+1];

            for(int i = 1; i <= howNumberSecondLine; i++){
                secondLine[i] = in.nextInt();
            }

            // Analyzation lines
            int[][] arrayForWork = new int[2][howNumberSecondLine+1];

            for(int i = 1; i <= howNumberFirstLine; i++){
                for(int j = 1; j <= howNumberSecondLine; j++){
                    if (firstLine[i] == secondLine[j])
                        arrayForWork[i%2][j] = 1 + arrayForWork[(i-1)%2][j-1];

                    else
                        arrayForWork[i%2][j] = Math.max(arrayForWork[(i-1)%2][j],arrayForWork[i%2][j-1]);
                }
            }



            out.println(arrayForWork[howNumberFirstLine%2][howNumberSecondLine]);
            out.flush();
            in.close();
            out.close();

        }

}

