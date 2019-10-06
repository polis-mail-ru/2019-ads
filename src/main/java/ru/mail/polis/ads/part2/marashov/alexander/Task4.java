package ru.mail.polis.ads.part2.marashov.alexander;

import java.util.Scanner;
import java.util.Stack;

public class Task4 {

    private Task4() {

    }

    public static void main(String[] args) {
        final Stack<Character> stack = new Stack<>();
        final Scanner in = new Scanner(System.in);
        final String str = in.nextLine();
        for (int i = 0; i < str.length(); ++i) {
            char ch = str.charAt(i);
            if (ch == '(') {
                stack.push(ch);
            } else {
                boolean isCorrect = false;
                while (!stack.isEmpty()) {
                    if (stack.pop() == '(') {
                        isCorrect = true;
                        break;
                    }
                }
                if (!isCorrect) {
                    System.out.println("NO");
                    return;
                }
            }
        }
        if (stack.isEmpty()) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

}
