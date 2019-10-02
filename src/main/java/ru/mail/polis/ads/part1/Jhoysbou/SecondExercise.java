package ru.mail.polis.ads.part1.Jhoysbou;

import java.util.Scanner;

// Submission here https://www.e-olymp.com/ru/submissions/5765475

public class SecondExercise {
    private static String sequence;
    private static int min;
    public static void main(final String... args){
        final Scanner scanner = new Scanner(System.in);
        sequence = scanner.nextLine();
        if (sequence.isEmpty()){
            System.out.println(sequence);
            return;
        }

        final int length = sequence.length();
        int[][] dinamicArray = new int [length][length];
        int[][] split = new int[length][length];

        for (int j = 0; j < length; ++j){
            for (int i = j; i >= 0; --i) {
                if (i == j) {
                    dinamicArray[i][j] = 1;
                    continue;
                }
                min = Integer.MAX_VALUE;
                int splitMin = -1;
                if (sequence.charAt(i) == '(' && sequence.charAt(j) == ')'
                        || sequence.charAt(i) == '[' && sequence.charAt(j) == ']'){
                    min = dinamicArray[i+1][j-1];
                }
                splitMin = split(i, j, dinamicArray);
                dinamicArray[i][j] = min;
                split[i][j] = splitMin;
            }
        }

        restore(0, length-1, dinamicArray, split);
    }
    static int split(int i, int j, int[][] d) {
        for (int index = i; index < j; ++index){
            if (d[i][index] + d[index+1][j] < min){
                min = d[i][index] + d[index+1][j];
                return index;
            }
        }
        return 0;
    }


    static void restore(final int i, final int j,
                        final int[][] dinamicArray, final int[][] split) {
        if (i == j) {
            switch (sequence.charAt(i)) {
                case '(':
                case ')':
                    System.out.print("()");
                    break;
                case '[':
                case ']':
                    System.out.print("[]");
                    break;
                default:
                    return;
            }
            return;
        }
        if (dinamicArray[i][j] == 0) {
            System.out.print(sequence.substring(i, j + 1));
            return;
        }
        if (split[i][j] == -1) {
            System.out.print(sequence.charAt(i));
            restore(i + 1, j - 1, dinamicArray, split);
            System.out.print(sequence.charAt(j));
            return;
        }
        final int index = split[i][j];
        restore(i, index, dinamicArray, split);
        restore(index+1, j, dinamicArray, split);
    }
}

