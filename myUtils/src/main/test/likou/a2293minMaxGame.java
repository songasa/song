package likou;

import java.util.Map;

public class a2293minMaxGame {
    public static void main(String[] args) {
        int[] nums = {1,3,5,2,4,8,2,2};
        System.out.println(minMaxGame(nums));
    }

    public static int minMaxGame(int[] nums) {
        int res = nums[0];
        int len = nums.length;
        if (len == 1) return res;
        int tmp = -1;
        int[] fun = nums;
        while (tmp != 1){
            fun = fun(len, fun);
            tmp = fun.length;
            res = fun[0];
        }
        return res;
    }

    public static int[] fun(int len,int[] nums){
        int[] res = new int[len/2];
        boolean flag = false;
        int count = 0;
        for (int i = 0; i < len/2; i++) {
            if(!flag){
                res[i] = Math.min(nums[count],nums[count+1]);
                flag= true;
            }else {
                res[i] = Math.max(nums[count],nums[count+1]);
                flag= false;
            }
            count+=2;
        }
        return res;
    }
}
