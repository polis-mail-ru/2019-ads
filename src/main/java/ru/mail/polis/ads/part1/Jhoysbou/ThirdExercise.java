package ru.mail.polis.ads.part1.Jhoysbou;


import java.util.Scanner;
//Submission here https://www.e-olymp.com/ru/submissions/5722724
public class ThirdExercise {
    public static void main(String... arg){
        Scanner scanner = new Scanner(System.in);
        String sequence = scanner.nextLine();
        int length = sequence.length();

        String[][] result = new String[length][length];

        for (int len = 1; len <= length; ++len){
            for (int left = 0; left + len -1 < length; ++left){

                int right = left + len-1;
                String min = sequence.substring(left, right + 1);
                if (len > 4){
                    for (int innerRight = left; innerRight < right; ++innerRight){
                        int innerLeft = innerRight + 1;
                        String temp = result[left][innerRight] + result[innerLeft][right];
                        if (temp.length() < min.length()){
                            min = temp;
                        }
                    }
                    for (int factor = 1; factor < len; ++factor){
                        if (len % factor == 0){
                            boolean isPeriodic = true;
                            for (int i = left + factor; i <= right; ++i){
                                if (sequence.charAt(i) != sequence.charAt(i - factor)){ isPeriodic = false; break; }
                            }
                            if (isPeriodic){
                                String temp = len / factor + "(" + result[left][left + factor -1] + ")";
                                if (temp.length() < min.length()){
                                    min = temp;
                                }
                            }
                        }
                    }

                }

                result[left][right] = min;
                }
            }
        System.out.println(result[0][length - 1]);

        }
}

