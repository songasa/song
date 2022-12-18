package likou;

import org.omg.CORBA.MARSHAL;

import java.util.Arrays;

public class a1785minElements {
    public static int minElements(int[] nums, int limit, int goal) {
        long n = 0;
        for (int num : nums) {
            n += num;
        }
//        long sum = Math.abs(Arrays.stream(nums).sum()-goal);
        long sum = Math.abs(n-goal);
        return (int) ((sum + limit - 1) / limit);
    }

    public static void main(String[] args) {
        int[] nums = {1,-1,1};
        System.out.println(minElements(nums, 3, -4));
    }
}
