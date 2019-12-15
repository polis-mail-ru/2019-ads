import java.io.*;
import java.util.*;

public class t2 {
    private final static int WHITE = 0;
    private final static int GREY = 1;
    private final static int BLACK = 2;

    private static byte[] used;
    private static int[] previous;
    private static List<List<Integer>> graph;
    private static boolean[] inLoop;

    private static void dfs(final int node) {
        Stack<Integer> stack = new Stack<>();
        stack.add(node);

        while (!stack.empty()) {
            int curr = stack.peek();
            used[curr] = GREY;
            boolean count = false;

            for (Integer end :
                    graph.get(curr)) {
                if (previous[curr] == end)
                    continue;
                if (used[end] == BLACK)
                    continue;
                if (used[end] == WHITE) {
                    previous[end] = curr;
                    stack.add(end);
                    count = true;
                    break;
                } else if (used[end] == GREY) {

                    int it = curr;
                    inLoop[end] = true;
                    if (inLoop[it])
                        continue;
                    while (it != end) {
                        inLoop[it] = true;
                        it = previous[it];
                        if (inLoop[it])
                            break;
                    }

                }
            }
            if (!count) {
                stack.pop();
                used[curr] = BLACK;
            }
        }
    }

    private static void setMinimum() {
        for (int i = 1; i < graph.size(); i++) {
            if (used[i] == 0)
                dfs(i);

        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] query = in.readLine().split(" ");

        int n = Integer.parseInt(query[0]);
        int m = Integer.parseInt(query[1]);

        used = new byte[n + 1];

        previous = new int[n + 1];

        inLoop = new boolean[n + 1];

        graph = new ArrayList<>(n + 1);

        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>(10));
        }

        for (int i = 0; i < m; i++) {
            query = in.readLine().split(" ");
            int b = Integer.parseInt(query[0]);
            int e = Integer.parseInt(query[1]);
            graph.get(b).add(e);
            graph.get(e).add(b);
        }

        setMinimum();

        int minimum = graph.size();

        for (int i = 1; i < inLoop.length; i++) {
            if (inLoop[i] && minimum > i) {
                minimum = i;
            }
        }

        if (minimum == graph.size()) {
            System.out.println("No");
        } else {
            System.out.printf("Yes\n%d", minimum);
        }
    }
}
