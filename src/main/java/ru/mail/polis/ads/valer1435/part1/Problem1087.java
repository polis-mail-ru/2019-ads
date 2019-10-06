package ru.mail.polis.ads.valer1435.part1;
// https://www.e-olymp.com/ru/submissions/5789518
import java.util.Scanner;

public class Problem1087 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        int[][] d = new int[s.length()][s.length()];
        int[][] d_split = new int[s.length()][s.length()];
        if (s.length() == 0){
            System.out.println();
            return;
        }
        for (int i = 0; i < s.length(); i++){
            d[i][i] = 1;
        }
        for (int j = 0; j < s.length(); j++){
            for (int i = j; i >= 0; i--) {
                int minIns = 9999;
                if ((s.charAt(i) == '(') && (s.charAt(j) == ')') || (s.charAt(i) == '[') && (s.charAt(j) == ']')){
                    d[i][j] = d[i+1][j-1];
                    minIns = d[i][j];
                }
                int minK = -1;
                if (i != j){
                    for (int k = i; k < j; k++){
                        int prevRes = d[i][k]+d[k+1][j];
                        if (prevRes < minIns){
                            minIns = prevRes;
                            minK = k;
                        }
                    }
                    d[i][j] = minIns;
                    d_split[i][j] = minK;
                }
            }
        }
        restore(0, s.length()-1, s, d, d_split);
    }

    private static void restore(int i, int j, String s, int[][] d, int[][] d_split){
        if (i == j){
            if (s.charAt(i) == '(' || s.charAt(i)==')'){
                System.out.print("()");
            }else if (s.charAt(i) == '[' || s.charAt(i)==']'){
                System.out.print("[]");
            }
            return;
        }
        if (d[i][j] == 0){
            System.out.print(s.substring(i, j+1));
            return;
        }

        if (d_split[i][j] == -1) {
            System.out.print(s.charAt(i));
            restore(i + 1, j - 1, s, d, d_split);
            System.out.print(s.charAt(j));
            return;
        }

        restore(i, d_split[i][j], s, d, d_split);
        restore(d_split[i][j]+1, j, s, d, d_split);


    }
}