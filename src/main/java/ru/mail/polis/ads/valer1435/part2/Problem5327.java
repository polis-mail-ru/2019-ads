package ru.mail.polis.ads.valer1435.part2;
// https://www.e-olymp.com/ru/submissions/5777890
import java.util.Scanner;
import java.util.Stack;

public class Problem5327 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        Stack<Character> stack = new Stack<>();
        boolean f = false;
        for (char i: s.toCharArray()) {
            if ((i == '(') || (i == '[')) {
                stack.push(i);
            } else if (i == ']') {
                if (!stack.isEmpty() && stack.lastElement() == '[') {
                    stack.pop();
                } else {
                    f = true;
                    break;
                }
            } else if (i == ')') {
                if (!stack.isEmpty() && stack.lastElement() == '(') {
                    stack.pop();
                } else {
                    f = true;
                    break;
                }
            }
        }
        if (!stack.isEmpty()){
            f = true;
        }
        if (f){
            System.out.println("NO");
        }else {
            System.out.println("YES");
        }
    }
}