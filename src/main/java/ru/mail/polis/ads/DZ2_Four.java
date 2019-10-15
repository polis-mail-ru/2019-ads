package ru.mail.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

class DZ2_Four {
    public static void main(String[] args) throws IOException {
        Stack<Integer> stack = new Stack<>();
        BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
        String a;
        while (true){
            a = br.readLine();
            if (a.startsWith("push")) {
                int param = Integer.parseInt(a.substring(5)); //scan.nextInt();
                stack.push(param);
                System.out.println("ok");
            } else if ("pop".equals(a)) {
                try {
                    System.out.println(stack.pop());
                } catch (Exception e){
                    System.out.println("error");
                }
            } else if ("back".equals(a)) {
                try {
                    System.out.println(stack.get(stack.size()-1));
                } catch (Exception e){
                    System.out.println("error");
                }
            } else if ("size".equals(a)) {
                System.out.println(stack.size());
            } else if ("clear".equals(a)) {
                stack.clear();
                System.out.println("ok");
            } else if ("exit".equals(a)) {
                System.out.println("bye");
                System.exit(0);
            }
        }
    }
}
