package ru.mail.polis.ads.part5.jhoysbou;

import java.util.Scanner;

// Submission here https://www.e-olymp.com/ru/submissions/5977805

public class Exercise_4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String test = scanner.next();

        char[] pattern;
        char[] str;

        if(test.contains("*")){
            pattern = test.toCharArray();
            str = scanner.next().toCharArray();
        } else{
            pattern = scanner.next().toCharArray();;
            str = test.toCharArray();
        }

        int sLength = str.length;
        int pLength = pattern.length;

        boolean [][] d=new boolean[sLength+1][pLength+1];
        d[0][0]=true;

        for (int i = 1; i <= sLength; i++) {
            for (int j = 1; j <= pLength ; j++) {
                if (str[i-1]==pattern[j-1] || pattern[j-1]=='?'){
                    d[i][j]=d[i-1][j-1];
                } else if (pattern[j-1]=='*'){
                    d[i][j]=d[i-1][j] || d[i-1][j-1]||d[i][j-1];
                } else{
                    d[i][j]=false;
                }
            }
        }

        System.out.print(d[sLength][pLength] ? "YES": "NO");
    }


}
