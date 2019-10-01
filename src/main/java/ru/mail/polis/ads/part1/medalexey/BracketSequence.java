package ru.mail.polis.ads.part1.medalexey;

import java.io.PrintWriter;
import java.util.Scanner;


/**
 *  Название задачи: Скобочная последовательность
 *  Тестирование: https://www.e-olymp.com/ru/submissions/5740807
 */
public final class BracketSequence {

    private BracketSequence() {

    }

    private static void solve(final Scanner in, final PrintWriter out) {
        final String input = in.nextLine();

        if (input.trim().equals("")) {
            out.println(input);
            return ;
        }

        int[][] numberOfBracketsToAdd = new int[input.length()][input.length()];
        String[][] resultSequence = new String[input.length()][input.length()];

        // перебираем все возможные длины подстрок входной строки
        for (int len = 1; len <= input.length(); len++) {
            // проходим по всем подстрокам данной длины
            for (int left = 0; left + len -1 < input.length(); left++) {
                final int right = left + len - 1;
                int minNumberOfBrackets = -1;
                String curSequence = "";

                // т.к. к единичной скобке можно добавить пару
                if (left == right) {
                    numberOfBracketsToAdd[left][right] = 1;
                    resultSequence[left][right] = addBracketToSequence(input,left);
                    continue;
                }


                // если текущая подстрока правильно закрыта
                if (bracketsArePair(input.charAt(left),input.charAt(right))) {
                    minNumberOfBrackets = numberOfBracketsToAdd[left+1][right-1];
                    curSequence = getSequence(resultSequence, input, left+1, right-1);
                }


                for (int right1 = left; right1 < right; right1++) {
                    final int left2 = right1 + 1;

                    if (minNumberOfBrackets == -1
                            || numberOfBracketsToAdd[left][right1]
                            + numberOfBracketsToAdd[left2][right] < minNumberOfBrackets) {
                        minNumberOfBrackets = numberOfBracketsToAdd[left][right1] + numberOfBracketsToAdd[left2][right];
                        curSequence = concatSequences(
                                input,
                                resultSequence[left][right1],
                                resultSequence[left2][right]
                        );
                    }
                }

                numberOfBracketsToAdd[left][right] = minNumberOfBrackets;
                resultSequence[left][right] = curSequence;
            }
        }

        out.println(resultSequence[0][input.length()-1]);
    }


    // являются ли скобки парой
    private static boolean bracketsArePair(final char left, final char right) {
        if (left == '(') {
            return right == ')';
        } else if (left == '[') {
            return right == ']';
        }

        return false;
    }

    //добавляет подходящую скобку
    private static String addBracketToSequence(final String sequence, final int bracketIndex) {
        String result = sequence;
        switch (sequence.charAt(bracketIndex)) {
            case '(':
                result = sequence.substring(0,bracketIndex + 1) + ')' + sequence.substring(bracketIndex + 1);
                break;
            case '[':
                result = sequence.substring(0,bracketIndex + 1) + ']' + sequence.substring(bracketIndex + 1);
                break;
            case ')':
                result = sequence.substring(0,bracketIndex) + '(' + sequence.substring(bracketIndex);
                break;
            case ']':
                result = sequence.substring(0,bracketIndex) + '[' + sequence.substring(bracketIndex);
                break;
            default:
                break;
        }

        return result;
    }

    private static String getSequence(final String[][] matrix, final String string, final int i, final int j) {
        return (matrix[i][j] == null) ? string : matrix[i][j];
    }


    private static String concatSequences(
            final String mainString,
            final String firstSequence,
            final String secondSequence) {
        final StringBuilder result = new StringBuilder();
        int mainStringPointer = 0;
        int firstSequencePointer = 0;
        int secondSequencePointer = 0;

        while (mainStringPointer < mainString.length()) {
            if (mainString.charAt(mainStringPointer) == firstSequence.charAt(firstSequencePointer) &&
                    mainString.charAt(mainStringPointer) == secondSequence.charAt(secondSequencePointer)) {
                result.append(mainString.charAt(mainStringPointer));
                mainStringPointer++;
                firstSequencePointer++;
                secondSequencePointer++;
            } else if (mainString.charAt(mainStringPointer) == firstSequence.charAt(firstSequencePointer) &&
                    mainString.charAt(mainStringPointer) != secondSequence.charAt(secondSequencePointer)) {
                result.append(secondSequence.charAt(secondSequencePointer));
                secondSequencePointer++;
            } else if (mainString.charAt(mainStringPointer) == secondSequence.charAt(secondSequencePointer) &&
                    mainString.charAt(mainStringPointer) != firstSequence.charAt(firstSequencePointer)) {
                result.append(firstSequence.charAt(firstSequencePointer));
                firstSequencePointer++;
            }

        }


        if (firstSequencePointer != firstSequence.length()) {
            result.append(firstSequence.charAt(firstSequencePointer));
        }
        if (secondSequencePointer != secondSequence.length()) {
            result.append(secondSequence.charAt(secondSequencePointer));
        }

        return result.toString();
    }



    public static void main(final String[] arg) {
        final Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        solve(in, out);
        out.close();
        in.close();
    }

}
