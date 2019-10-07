package ru.mail.polis.ads.part2.jhoysbou;

import java.util.Scanner;

// Submission here https://www.e-olymp.com/ru/submissions/5787322

public class FirstExercise {
        private static final Scanner scanner = new Scanner(System.in);
        private static int length;
        private static int width;

        public static void main(String[] args) {
            String temp = scanner.nextLine();
            length = Integer.parseInt(temp.split(" ")[0]);
            width = Integer.parseInt(temp.split(" ")[1]);

            String[][] inputArray = new String[length][width];
            int[][] dynamicArray = new int[length+1][width+1];
            for (int i = length-1; i >= 0; i--) {
                inputArray[i] = scanner.nextLine().split(" ");
            }

            for (int i = 0; i <= length; i++) {
                for (int j = 0; j <= width; j++) {
                    if (j == 0 || i == 0) {
                        dynamicArray[i][j] = -1;
                    } else {
                        dynamicArray[i][j] = Integer.parseInt(inputArray[i-1][j-1]);
                    }

                }
            }
            dynamicArray = getMaxSeeds(dynamicArray);
            System.out.println(getRoot(dynamicArray)); // Noncompliant
        }

        private static int[][] getMaxSeeds(int[][] dinamicArray) {
            dinamicArray[0][1] = 0;
            for (int i = 1; i <= length; i++) {
                for (int j = 1; j <= width; j++) {
                    dinamicArray[i][j] = Math.max(dinamicArray[i-1][j], dinamicArray[i][j-1]) + dinamicArray[i][j];
                }
            }
            return dinamicArray;
        }

        private static StringBuilder getRoot(int[][] maxSeedArray) {
            StringBuilder root = new StringBuilder();
            int i = length, j = width;
            while (i + j > 2) {
                if (maxSeedArray[i-1][j] > maxSeedArray[i][j-1]) {
                    root.append('F');
                    i--;
                } else {
                    root.append('R');
                    j--;
                }
            }

            return root.reverse();
        }


    }


