package ru.mail.polis.ads.task2.art241111;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
        Link to result: https://www.e-olymp.com/ru/problems/5327
 **/

class Stack {
    private List<Character> stack;

    Stack() {
        stack = new ArrayList<>();
    }

    void push(char newValue){
        stack.add(newValue);
    }

    void delete(){
        if(!stack.isEmpty()){
            stack.remove(stack.size() - 1);
        }
    }

    char back(){
        if(!stack.isEmpty()){
            return stack.get(stack.size() - 1);
        }
        return 0;
    }

    int size(){
        if(!stack.isEmpty()){
            return stack.size();
        }
        return 0;
    }
}

public class BracketSequences {
    public static void main(String[] args) {
        Stack stack = new Stack();
        boolean fCorrectSequence = true;

        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        char[] sequenceOfTheLetters = in.nextLine().toCharArray();
        char[] sequenceOptions = "([)]".toCharArray();
        for (char sequenceOfTheLetter : sequenceOfTheLetters) {
            if ((sequenceOfTheLetter == sequenceOptions[0]) || (sequenceOfTheLetter == sequenceOptions[1])) {
                stack.push(sequenceOfTheLetter);
            } else if ((sequenceOfTheLetter == sequenceOptions[2])) {
                if (stack.back() == sequenceOfTheLetters[0]) {
                    stack.delete();
                } else fCorrectSequence = false;
            } else if ((sequenceOfTheLetter == sequenceOptions[3])) {
                if (stack.back() == sequenceOfTheLetters[1]) {
                    stack.delete();
                } else fCorrectSequence = false;
            }
        }
        if ((fCorrectSequence) && (stack.size() == 0)) {
            out.print("YES");
        } else out.print("NO");
        out.flush();
    }
}
