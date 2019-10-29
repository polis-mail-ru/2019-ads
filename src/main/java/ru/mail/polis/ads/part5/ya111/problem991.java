import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String first = sc.next();
        String second = sc.next();
        char[] pattern;
        char[] word;
        if (first.contains("*") || first.contains("?")) {
            pattern = first.toCharArray();
            word = second.toCharArray();
        } else {
            pattern = second.toCharArray();
            word = first.toCharArray();
        }
        boolean[][] d = new boolean[word.length + 1][pattern.length + 1];
        d[0][0] = true;
        for (int i = 1; i <= word.length; ++i) {
            for (int j = 1; j <= pattern.length; ++j) {
                if (word[i - 1] == pattern[j - 1] || pattern[j - 1] == '?') {
                    d[i][j] = d[i - 1][j - 1];
                } else if (pattern[j - 1] == '*') {
                    d[i][j] = d[i - 1][j] || d[i - 1][j - 1] || d[i][j - 1];
                } else {
                    d[i][j] = false;
                }
            }
        }
        System.out.println(d[word.length][pattern.length] ? "YES" : "NO");

    }

}