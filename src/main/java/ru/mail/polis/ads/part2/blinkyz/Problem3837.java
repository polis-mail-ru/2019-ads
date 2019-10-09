package ru.mail.polis.ads.part2.blinkyz;

import java.util.*;

public class Problem3837 {
    private Problem3837() {
    }

    private static abstract class Expr {

    }

    private static final class BinaryOperation extends Expr {
        final char op;

        final Expr leftOperand;

        final Expr rightOperand;

        BinaryOperation(final char op, final Expr leftOperand, final Expr rightOperand) {
            this.op = op;
            this.leftOperand = leftOperand;
            this.rightOperand = rightOperand;
        }

        @Override
        public String toString() {
            return "BinaryOperation{" +
                    "op=" + op +
                    ", [" + op + "]left=" + leftOperand +
                    ", [" + op + "]right=" + rightOperand +
                    '}';
        }
    }

    private static final class LiteralOperand extends Expr {
        final char literal;

        LiteralOperand(final char literal) {
            this.literal = literal;
        }

        @Override
        public String toString() {
            return String.valueOf(literal);
        }
    }

    private static final class Converter {
        private final Map<Integer, String> res = new HashMap<>();

        private Expr calcUsingStack(final String s) {
            Deque<Expr> stack = new ArrayDeque<>();

            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                if (Character.isUpperCase(ch)) {
                    Expr a = stack.pop();
                    Expr b = stack.pop();
                    stack.push(new BinaryOperation(ch, b, a));
                    continue;
                }
                stack.push(new LiteralOperand(ch));
            }
            return stack.pop();
        }

        private synchronized void addToRes(int depth, char elem) {
            String oldRes = res.get(depth);
            if (oldRes == null) {
                oldRes = "";
            }
            res.put(depth, oldRes + elem);
        }

        private void treeTraversal(Expr root, int depth) {
            if (root instanceof LiteralOperand) {
                addToRes(depth, ((LiteralOperand) root).literal);
                return;
            }

            BinaryOperation binaryOperation = (BinaryOperation) root;
            addToRes(depth, binaryOperation.op);
            treeTraversal(binaryOperation.rightOperand, depth + 1);
            treeTraversal(binaryOperation.leftOperand, depth + 1);
        }

        Converter(final String s) {
            Expr binaryTreeRoot = calcUsingStack(s);
            treeTraversal(binaryTreeRoot, 0);
        }

        String getResult() {
            StringBuilder ans = new StringBuilder();
            for (int i = res.size() - 1; i >= 0; --i) {
                ans.append(res.get(i));
            }
            return ans.toString();
        }
    }

    private static String convert(final String s) {
        Converter converter = new Converter(s);
        return converter.getResult();
    }

    private static void solve() {
        final Scanner scanner = new Scanner(System.in);
        final int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            final String expr = scanner.next();
            System.out.println(convert(expr));
        }
    }

    public static void main(String[] args) {
        solve();
    }
}
