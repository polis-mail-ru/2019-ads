package ru.mail.polis.ads.valer1435.part2;
//https://www.e-olymp.com/ru/submissions/5788281
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Promlem6124 {
    public static void main(String[] args){
        MyStack s= new MyStack();
        FastScanner in = new FastScanner();
        while (true) {
            String c = in.next();
            switch (c) {
                case "push": {
                    System.out.println(s.push(in.nextInt()));
                    break;
                }
                case "pop": {
                    s.pop();
                    break;
                }
                case "back": {
                    s.back();
                    break;
                }
                case "size": {
                    System.out.println(s.size());
                    break;
                }
                case "clear": {
                    System.out.println(s.clear());
                    break;
                }
                case "exit": {
                    System.out.println(s.bye());
                    return;
                }
            }
        }
    }
    private static class MyStack {

        private LinkedList<Integer> lst = new LinkedList<>();

        private int count = 0;


        private String push(int i){
            lst.add(i);
            count++;
            return "ok";
        }

        private void pop(){
            if (this.size() == 0){
                System.out.println("error");
                return;
            }
            int a = lst.remove(count-1);
            count--;
            System.out.println(a);

        }

        private void back(){
            if (this.size() == 0){
                System.out.println("error");
                return;
            }
            System.out.println(lst.get(count-1));
        }

        private int size(){
            return count;
        }

        private String clear(){
            lst.clear();
            count = 0;
            return "ok";
        }

        private String bye(){
            return "bye";
        }
    }
    private static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        public FastScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

    }
}