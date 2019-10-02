package ru.mail.polis.ads.part1.Jhoysbou;

import java.util.Scanner;

// Submission here https://www.e-olymp.com/ru/submissions/5748699

public class FourthExercise {
    public static void main(final String... args){
        final int maxSubsequence = getMaxSubsequence();
        System.out.println(maxSubsequence);
    }

    private FourthExercise() {
        throw new UnsupportedOperationException();
    }

    /** Вычисляет максимальную длину общей подпоследоватльности.
     * @return Integer с макисмлаьной длиной такой подпоследовательности.
     */
    public static int getMaxSubsequence(){
        final Scanner scanner = new Scanner(System.in);

        final int length_1 = scanner.nextInt();
        int[] firstSequence = new int[length_1 + 1];
        for (int i = 1; i <= length_1; ++i){
            firstSequence[i] = scanner.nextInt();
        }

        final int length_2 = scanner.nextInt();
        int[] secondSequence = new int[length_2 + 1];
        for (int i = 1; i <= length_2; ++i){
            secondSequence[i] = scanner.nextInt();
        }

        int[][] subSequence = new int[2][length_2 + 1];

        for (int i = 1; i <= length_1; ++i){
            for (int j = 1; j <= length_2; ++j){
                if(firstSequence[i] == secondSequence[j]) {
                    subSequence[i % 2][j] = 1 + subSequence[(i - 1) % 2][j - 1];
                } else {
                    subSequence[i%2][j] = Math.max(subSequence[(i-1)%2][j], subSequence[i%2][j-1]);
                }
            }
        }
        return subSequence[length_1 % 2][length_2];
    }
}
