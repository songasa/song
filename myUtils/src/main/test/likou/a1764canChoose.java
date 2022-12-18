package likou;

import java.util.Arrays;

public class a1764canChoose {
    public static boolean canChoose(int[][] groups, int[] nums) {
        int len = groups.length;
        int l = nums.length;

        System.out.println(Arrays.toString(groups));
        System.out.println(Arrays.toString(nums));
        return true;
    }

    public static void main(String[] args) {
        int[][] groups = {{10,-2},{1,2,3,4}};
        int[]   nums   = {1,2,3,4,10,-2};
        System.out.println(canChoose(groups, nums));
    }
}
