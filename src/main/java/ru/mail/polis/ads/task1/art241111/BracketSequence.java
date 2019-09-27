package ru.mail.polis.ads.task1.art241111;

import java.util.Scanner;
import static java.lang.Math.min;

/**
 Link to result: https://www.e-olymp.com/ru/submissions/5721219
 */

public class BracketSequence {
    // Initialization some const
    private static final int MAX_VALUE = Integer.MAX_VALUE;
    private static final int LENGTH_ARRAY = 110;

    //Initialization arrays
    private static char[] textIn = new char[LENGTH_ARRAY];
    private static int[][] leastNumberOfCharacters = new int[LENGTH_ARRAY][LENGTH_ARRAY];
    private static int[][] arrayToRestoreValue = new int[LENGTH_ARRAY][LENGTH_ARRAY];


    private static int searchLeastNumberOfCharacters(int left, int right){
        if (left == right) return 1;
        if (left > right) return 0;

        if (leastNumberOfCharacters[left][right] != MAX_VALUE) {
            return leastNumberOfCharacters[left][right];
        }

        if ((textIn[left] == '(' && textIn[right] == ')') || (textIn[left] == '[' && textIn[right] == ']')){
            leastNumberOfCharacters[left][right] =
                    min(leastNumberOfCharacters[left][right], searchLeastNumberOfCharacters(left + 1,right-1));
        }

        for (int i = left; i < right; i++){
            int temp = searchLeastNumberOfCharacters(left,i) + searchLeastNumberOfCharacters(i+1,right);
            if (temp < leastNumberOfCharacters[left][right]) {
                arrayToRestoreValue[left][right] = i;
                leastNumberOfCharacters[left][right] = temp;
            }
        }
        return leastNumberOfCharacters[left][right];
    }

    private static void print(int left, int right)  {
        if (left > right) return;

        if (left == right) {
            if (textIn[left] == '(' || textIn[left] == ')')
                System.out.print("()");
            else
                System.out.print("[]");
        } else if (arrayToRestoreValue[left][right] == -1)  {
            System.out.print(textIn[left]);
            print(left + 1, right - 1);
            System.out.print(textIn[right]);
        } else  {
            print(left, arrayToRestoreValue[left][right]);
            print(arrayToRestoreValue[left][right] + 1, right);
        }
    }

    private static void fillEntireArray(int[][] array,int num){
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                array[i][j] = num;
            }
        }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        textIn = in.nextLine().toCharArray();
        int lenS = textIn.length;

        fillEntireArray(leastNumberOfCharacters,MAX_VALUE);
        fillEntireArray(arrayToRestoreValue,-1);


        searchLeastNumberOfCharacters(0, lenS - 1);
        print(0, lenS - 1);
    }
}
