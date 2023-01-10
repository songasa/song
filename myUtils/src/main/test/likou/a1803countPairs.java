package likou;

import java.util.HashMap;
import java.util.Map;

public class a1803countPairs {
    public static void main(String[] args) {
        int[] nums = {1,4,2,7};
        int[] nums2 = {8,8,4,2,1};
        System.out.println(countPairs(nums, 2, 6));
        System.out.println(countPairs(nums2, 5, 14));
    }

    public static int countPairs(int[] nums, int low, int high) {
        int res = 0;
        Map<Integer,Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)){
                map.put(num,map.get(num)+1);
            }else {
                map.put(num,1);
            }
        }
        int skip = 0;

        for (Map.Entry<Integer, Integer> m1 : map.entrySet()) {
            int tmp = skip++;
        for (Map.Entry<Integer, Integer> m2 : map.entrySet()) {
            if(tmp-- >= 0){
                continue;
            }
            if ( (m1.getKey()^m2.getKey()) >= low && (m1.getKey()^m2.getKey()) <= high){
                res += m1.getValue() * m2.getValue();
            }
        }}
        return res;
    }
}
