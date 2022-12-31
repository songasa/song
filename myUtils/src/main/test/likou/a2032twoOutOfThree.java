package likou;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class a2032twoOutOfThree {
    static Set<Integer> res = new HashSet<>();
    public static void main(String[] args) {
        int[] nums1 = {1,1,3,2};
        int[] nums2 = {3,2};
        int[] nums3 = {3};
        twoOutOfThree(nums1,nums2,nums3);
    }
    public static List<Integer> twoOutOfThree(int[] nums1, int[] nums2, int[] nums3) {
        fun(nums1, nums2);
        fun(nums1, nums3);
        fun(nums3, nums2);
        return (List<Integer>) res;
    }

    static void fun(int[] nums1, int[] nums2){
        int n1 = nums1.length;
        int n2 = nums2.length;
        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < n2; j++) {
                if (nums1[i]==nums2[j]){
                    res.add(nums1[i]);
                    break;
                }
            }
        }
    }
}
