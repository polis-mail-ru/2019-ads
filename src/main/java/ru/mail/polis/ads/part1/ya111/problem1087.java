import java.util.Scanner;
//https://www.e-olymp.com/ru/submissions/5788810
public class problem1087 {
    private problem1087(){
            }

    static void restore (int i, int j, String str, int[][] d, int [][]s) {
        if (i == j) {
            switch (str.charAt(i)) {
                case '(':
                case ')':
                    System.out.print("()");
                    break;
                case '[':
                case ']':
                    System.out.print("[]");
                    break;
                default:
                    break;
            }
            return;
        }
        if (d[i][j] == 0) {
            System.out.print(str.substring(i, j + 1));
            return;
        }
        if (s[i][j] == -1) {
            System.out.print(str.charAt(i));
            restore(i + 1, j - 1, str, d, s);
            System.out.print(str.charAt(j));
            return;
        }
        final int kk = s[i][j];
        restore(i, kk, str, d, s);
        restore(kk + 1, j, str, d ,s);
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        if (str.isEmpty())
        {
            System.out.println();
            return;
        }
        int n = str.length();
        int [][] d = new int[n][n];
        int [][] s = new int[n][n];

        for (int j = 0; j < n; ++j)
            for (int i = j; i >= 0; i--) {
                if (i == j) {
                    d[i][j] = 1;
                    continue;
                }
                int min = Integer.MAX_VALUE;
                int sMin = -1;
                if (str.charAt(i) == '(' && str.charAt(j) == ')' || str.charAt(i) == '[' && str.charAt(j) == ']') {
                    min = d[i + 1][j - 1];
                }
                for (int f = i; f < j; ++f) {
                    if (d[i][f] + d[f + 1][j] < min) {
                        min = d[i][f] + d[f + 1][j];
                        sMin = f;
                    }
                }
                d[i][j] = min;
                s[i][j] = sMin;
            }

        restore(0, n - 1, str, d, s);


    }
}