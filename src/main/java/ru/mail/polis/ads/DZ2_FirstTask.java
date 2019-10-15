package ru.mail.polis.ads;

import java.util.Scanner;

class DZ2_FirstTask {
    public static void main(final String[] arg) {
        Scanner in = new Scanner(System.in);
        int a = in.nextInt();
        int b = in.nextInt();
        int[][] matrix = new int[a + 1][b + 1];
        for (int i = 1; i < a + 1; i++){
            for (int j = 0; j < b; j++){
                int d = in.nextInt();
                matrix[i][j] = d;
            }
        }
        search(matrix, a, b);
    }

    static void search(int[][] matrix, int a, int b){
        String path = "";
        int stepA = a;
        int stepB = 0;
        while (true){
            if (matrix[stepA - 1][stepB] > matrix[stepA][stepB + 1]){
                path += "F";
                stepA--;
            } else if (matrix[stepA - 1][stepB] == matrix[stepA][stepB + 1]) {
                try {
                    int i = matrix[stepA - 2][stepB];
                    path += "F";
                    stepA--;
                } catch (Exception e){
                    path += "R";
                    stepB++;
                }
            } else {
                path += "R";
                stepB++;
            }

            if (stepA == 1 && stepB == b - 1){
                System.out.println(path);
                break;
            }
        }
    }
}
