package likou;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class a2465distinctAverages {
    public static int distinctAverages(int[] nums) {
        Arrays.sort(nums);
        int end = nums.length - 1;
        Set set = new LinkedHashSet();
        for (int i = 0; i < nums.length/2; i++) {
            set.add((nums[i]+nums[end--])*1.0/2);
        }
        return set.size();
    }

    public static void main(String[] args) {
        int[] nums = {9,5,7,8,7,9,8,2,0,7};
        distinctAverages(nums);
    }
}
