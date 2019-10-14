package ru.mail.polis.ads.part.second;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/**
 * @author v.ivlev
 * https://www.e-olymp.com/ru/submissions/5858020
 */
public class TaskThree {

    private static void solve(FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            out.println(StackToQueue(in.next()));
        }
    }

    private static String StackToQueue(String string) {
        StringBuilder stringBuilder = new StringBuilder();
        ArrayDeque<Component> arrayDeque = new ArrayDeque<>();
        for (char c : string.toCharArray()) {
            if (Character.isUpperCase(c)) {
                Component component = new Component(c);
                component.setRightComponent(arrayDeque.poll());
                component.setLeftComponent(arrayDeque.poll());
                arrayDeque.push(component);
            } else {
                arrayDeque.push(new Component(c));
            }
        }
        while (!arrayDeque.isEmpty()) {
            Component component = arrayDeque.pollFirst();
            stringBuilder.insert(0, component.getOperator());
            if (component.getRightComponent() != null) {
                arrayDeque.addLast(component.getLeftComponent());
                arrayDeque.addLast(component.getRightComponent());
            }
        }
        return stringBuilder.toString();
    }

    public static void main(final String[] arg) {
        FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
        }

        String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    private static class Component {
        private char operator;
        private Component leftComponent;
        private Component rightComponent;

        public Component(char operator) {
            this.operator = operator;
        }

        public char getOperator() {
            return this.operator;
        }

        public Component getLeftComponent() {
            return this.leftComponent;
        }

        public void setLeftComponent(Component component) {
            this.leftComponent = component;
        }

        public Component getRightComponent() {
            return this.rightComponent;
        }

        public Component setRightComponent(Component component) {
            return this.rightComponent = component;
        }
    }
}
