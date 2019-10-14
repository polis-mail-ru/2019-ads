package ru.mail.polis.ads.part2.kara111;

import java.util.Scanner;
import java.util.Stack;

public class NewBrackets {
    static boolean isSubsequence(String str){
        Stack<String> buffer = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                buffer.push("(");
            } else {
                if (!buffer.empty()) {
                    buffer.pop();
                } else return false;
            }
        }
        return (buffer.empty());
    }
}


class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        System.out.println(NewBrackets.isSubsequence(str) ? "YES" : "NO");
    }
}



