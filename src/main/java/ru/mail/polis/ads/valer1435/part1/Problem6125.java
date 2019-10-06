package ru.mail.polis.ads.valer1435.part1;
// https://www.e-olymp.com/ru/submissions/5789709
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem6125{
    public static void main(String[] args){
        Queue q = new Queue();
        FastScanner in = new FastScanner();
        while (true) {
            String c = in.next();
            switch (c) {
                case "push": {
                    System.out.println(q.push(in.nextInt()));
                    break;
                }
                case "pop": {
                    System.out.println(q.pop());
                    break;
                }
                case "front": {
                    System.out.println(q.front());
                    break;
                }
                case "size": {
                    System.out.println(q.size());
                    break;
                }
                case "clear": {
                    System.out.println(q.clear());
                    break;
                }
                case "exit": {
                    System.out.println(q.bye());
                    return;
                }
            }
        }
    }
    private static class Queue {

        private int[] queue = new int[101];
        private int head = 0;
        private int tall = 0;
        private int count = 0;

        private String push(int i){
            queue[tall] = i;
            tall+=1;
            if (tall > 100){
                tall = 0;
            }
            count++;
            return "ok";
        }

        private int pop(){
            int a = queue[head];
            queue[head] = 0;
            head+=1;
            if (head > 100){
                head = 0;
            }
            count--;
            return a;
        }

        private int front(){
            return queue[head];
        }

        private int size(){
            return count;
        }

        private String clear(){
            while (head != tall){
                queue[head] = 0;
                count--;
                head++;
                if (head > 100){
                    head = 0;
                }
            }
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

    }
}