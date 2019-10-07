package ru.mail.polis.ads.part2.jhoysbou;

import java.util.Scanner;
import java.util.Stack;

// Submission here https://www.e-olymp.com/ru/submissions/5784988

public class FourthExercise {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String sequence = scanner.nextLine();
        int length = sequence.length();
        if (sequence.charAt(0) == ')' || sequence.charAt(length-1) == '(') {
            System.out.println("NO");
            return;
        }
        Stack<Character> brackets = new Stack<>();
        for (int i = 0; i < length; i++) {
            switch (sequence.charAt(i)) {
                case '(':
                    brackets.push('(');
                    break;
                case ')':
                    if (brackets.empty()) {
                        System.out.println("NO");
                        return;
                    }
                    brackets.pop();
                    break;
            }
        }

        if (brackets.empty()) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }

    }
}
