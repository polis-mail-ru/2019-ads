package ru.mail.polis.ads.part3.jhoysbou;


import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

// Submission here https://www.e-olymp.com/ru/submissions/5850907

public class FourthExercise {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        final int k = Integer.parseInt(scanner.nextLine());
        String[] array = scanner.nextLine().trim().split(" ");
        Arrays.sort(array, new myComparator());
        System.out.println(array[array.length - k]);

    }

    public static class myComparator implements Comparator<String> {

        @Override
        public int compare(String firstNumber, String secondNumber) {
            return firstNumber.length() > secondNumber.length() ? 1 :
                    firstNumber.length() < secondNumber.length() ? -1 :
                            firstNumber.compareTo(secondNumber);
        }

    }

}
