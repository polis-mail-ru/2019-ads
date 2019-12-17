import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class Part9_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] numbers;
        numbers = bf.readLine().split(" ");

        int n = Integer.parseInt(numbers[0]);
        int k = Integer.parseInt(numbers[1]);

        List<Integer> edgesList[] = new LinkedList[n + 1];
        for (int i = 1; i <= n; i++) {
            edgesList[i] = new LinkedList<>();
        }

        for (int i = 0; i < k; i++) {
            numbers = bf.readLine().split(" ");
            int a = Integer.parseInt(numbers[0]);
            int b = Integer.parseInt(numbers[1]);
            edgesList[a].add(b);
            edgesList[b].add(a);
        }

        boolean isBlack[] = new boolean[n + 1];

        int answer = cycleSearch(n, edgesList, isBlack);
        if (answer > 0) {
            System.out.println("Yes");
            System.out.println(answer);
        } else {
            System.out.println("No");
        }
    }

    private static int cycleSearch(int n, List<Integer> edgesList[], boolean isBlack[]) {
        for (int i = 1; i <= n; i++) {
            boolean isCycle = dfsCycle(0, i, i, edgesList, isBlack.clone(), 0);
            if (isCycle) {
                return i;
            }
        }

        return -1;
    }

    private static boolean dfsCycle(int prev, int current, int endV, List<Integer> edgesList[], boolean isBlack[], int count) {
        count++;
        if (current != endV) {
            isBlack[current] = true;
        } else if (count > 1) {
            return true;
        }

        for (Integer w : edgesList[current]) {
            if (isBlack[w] == false && w != prev) {
                boolean isCycle = dfsCycle(current, w, endV, edgesList, isBlack, count);
                if (isCycle) {
                    return true;
                }
            }
        }


        return false;
    }
}
