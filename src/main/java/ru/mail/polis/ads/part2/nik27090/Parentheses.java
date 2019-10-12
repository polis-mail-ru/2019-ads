package ru.mail.polis.ads.part2.nik27090;

import java.util.Scanner;
import java.util.Stack;

//Задача: "Скобочные последовательности"
//Решение: https://www.e-olymp.com/ru/submissions/5842507

public class Parentheses {
    public static void main(String[] args){
        Scanner sc =new Scanner(System.in);
        Stack<Character> stack = new Stack<>();
        String str = sc.nextLine();

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == '(') {
                stack.push(ch);
            } else{
                if (stack.size()==0){
                    exit();
                }else if (stack.pop()!='('){
                    exit();
                }
            }
        }
        if (stack.size()!=0){
            exit();
        }
        System.out.println("YES");
    }

    static void exit(){
        System.out.println("NO");
        System.exit(0);
    }
}
