package likou;

import java.util.Date;

public class a1802maxValue {
    public static void main(String[] args) {
        Long t1 = System.currentTimeMillis();
        System.out.println(maxValue(4, 2, 6));//2
        System.out.println(maxValue(6, 1, 10));//3
        System.out.println(maxValue(7, 3, 16));//4
        System.out.println(maxValue(3, 2, 18));//7
        System.out.println(maxValue(9, 3, 16));//3
        System.out.println(maxValue(1, 0, 780055968));//780055968
        System.out.println(maxValue(10, 8, 710639317));//71063935
        Long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1 +"毫秒");
    }

    public static int maxValue(int n, int index, int maxSum) {//0.5秒就完成了，leetcode说超时？
        int res = 2;
        int maxSum2 = maxSum - n - 1;
        int left = index;
        int right = n - index -1;
        int count1 = 1;
        int count2 = 1;
        boolean b1 = false;
        boolean b2 = false;
        while (maxSum2 > 0){
            if (b1 && b2){
                res += maxSum2 % (left+right+1);
                break;
            }
            if (left >= count1){
                maxSum2 -= count1;
                count1++;
            }else {
                b1 = true;
                maxSum2 -= left;
            }
            if (right >= count2){
                maxSum2 -= count2;
                count2++;
            }else {
                b1 = true;
                maxSum2 -= right;
            }
            maxSum2--;
            res++;
        }
        return maxSum2<0?--res:res;
    }

    public static int maxValue_outTime(int n, int index, int maxSum) {
        int res = 2;
        int maxSum2 = maxSum - n - 1;
        int left = index;
        int right = n - index -1;
        int count1 = 1;
        int count2 = 1;
        while (maxSum2 > 0){
            if (left >= count1){
                maxSum2 -= count1;
                count1++;
            }else {
                maxSum2 -= left;
            }
            if (right >= count2){
                maxSum2 -= count2;
                count2++;
            }else {
                maxSum2 -= right;
            }
            maxSum2--;
            res++;
        }
        return maxSum2<0?--res:res;
    }
}
