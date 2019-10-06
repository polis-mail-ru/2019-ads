package ru.mail.polis.ads.valer1435.part1;
// https://www.e-olymp.com/ru/submissions/5789605
import java.util.Scanner;

public class  Problem1090{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        String[][] d = new String[s.length()][s.length()];
        if (s.length() == 0){
            System.out.println();
            return;
        }
        for (int j = 0; j < s.length(); j++){
            for (int i = j; i >= 0; i--) {
                String sub = s.substring(i, j+1);
                if (sub.length() <= 4){
                    d[i][j] = sub;
                }else{
                    int minLen = 9999;
                    String res1 = sub;
                    for (int k = i; k < j; k++){
                        int prevRes = (d[i][k]+d[k+1][j]).length();
                        if (prevRes <= minLen){
                            minLen = prevRes;
                            res1 = d[i][k]+d[k+1][j];
                        }
                    }
                    for (int k = 1; k<=sub.length()/2+1; k++) {
                        if (sub.length() % k != 0){
                            continue;
                        }
                        int cnt = 1;
                        boolean f = false;
                        String curr = d[i][i+k-1];
                        for (int l = k; i+l+k-1 <= j; l+=k){
                            if (curr.equals(d[i+l][i+l+k-1])){

                                cnt+=1;
                            }else{
                                f = true;
                                break;
                            }
                        }
                        if (!f && cnt != 1){
                            String minRes = cnt+"("+curr+")";
                            if (minRes.length() <= minLen){
                                minLen = minRes.length();
                                res1 = minRes;
                            }
                        }
                    }

                    d[i][j] = res1;
                }

            }
        }
        System.out.println(d[0][s.length()-1]);
    }
}