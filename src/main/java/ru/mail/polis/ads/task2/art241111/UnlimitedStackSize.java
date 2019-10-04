package ru.mail.polis.ads.task2.art241111;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
    Link to result: https://www.e-olymp.com/ru/problems/6124
 **/

class StackInt {
    private List<Integer> stack;

    StackInt() {
        stack = new ArrayList<>();
    }

    void push(int newValue){
        stack.add(newValue);
    }

    String pop(){
        if(!stack.isEmpty()){
            String returnValue = String.valueOf(stack.get(stack.size() - 1));
            stack.remove(stack.size() - 1);
            return returnValue;
        } else{
            return "error";
        }

    }

    String back(){
        if(!stack.isEmpty()){
            return String.valueOf(stack.get(stack.size() - 1));
        } else{
            return "error";
        }

    }

    int size(){
        return stack.size();
    }

    void clear(){
        stack = new ArrayList<>();
    }
}

public class UnlimitedStackSize
{
    private static int getIntFromString(String addElement){
        return Integer.parseInt(addElement.split(" ")[1]);
    }

    public static void main (String[] args)
    {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        String inString;
        StackInt stack = new StackInt();

        do{
            inString = in.nextLine();
            switch (inString.split(" ")[0]) {
                case ("push"):
                    stack.push(getIntFromString(inString));
                    out.println("ok");
                    break;

                case "pop":
                    out.println(stack.pop());
                    break;

                case "back":
                    out.println(stack.back());
                    break;

                case "size":
                    out.println(stack.size());
                    break;

                case "clear":
                    stack.clear();
                    out.println("ok");
                    break;
            }
        }while(!inString.equals("exit"));
        out.println("bye");

        // Write result
        out.flush();
    }
}
