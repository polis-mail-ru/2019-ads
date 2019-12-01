package ru.mail.polis.ads.part9;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


/**
 * 1948
 * submission - https://www.e-olymp.com/ru/submissions/6245013
 */
public class Problem1 {

    private enum NodeState {
        NOT_VISITED,
        IN_STACK,
        VISITED
    }

    private static class Node {

        private int id;
        private NodeState state;
        private List<Node> adjacent;

        private Node(int id) {
            this.id = id;
            this.state = NodeState.NOT_VISITED;
            this.adjacent = new LinkedList<>();
        }

    }

    private static Deque<Node> sorted;

    private static void solve() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        Node[] nodes = new Node[n + 1];
        for (int i = 1; i < n + 1; i++) {
            nodes[i] = new Node(i);
        }
        for (int i = 0; i < m; i++) {
            int first = sc.nextInt();
            int second = sc.nextInt();
            nodes[first].adjacent.add(nodes[second]);
        }
        sorted = new ArrayDeque<>();
        for (Node node : nodes) {
            if (node != null) {
                if (!sort(node)) {
                    System.out.println(-1);
                    return;
                }
            }
        }
        sorted.forEach((node) -> System.out.print(node.id + " "));
    }

    private static boolean sort(Node node) {
        if (node.state == NodeState.VISITED) {
            return true;
        }
        if (node.state == NodeState.IN_STACK) {
            return false;
        }
        node.state = NodeState.IN_STACK;
        for (Node adjacent : node.adjacent) {
            if (!sort(adjacent)) {
                return false;
            }
        }
        node.state = NodeState.VISITED;
        sorted.push(node);
        return true;
    }

    public static void main(String[] args) {
        solve();
    }
}
