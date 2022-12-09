package likou;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class a1386maxNumberOfFamilies_new2 {
    public static  int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        int count = 0;
        Map<Integer,boolean[]> map = new HashMap<>();
        for (int[] reservedSeat : reservedSeats) {
            if (map.containsKey(reservedSeat[0]-1)){
                boolean[] booleans = map.get(reservedSeat[0]-1);
                booleans[reservedSeat[1]-1] = true;
                map.put(reservedSeat[0]-1,booleans);
            }else {
                boolean[] tmp = new boolean[10];
                tmp[reservedSeat[1]-1] = true;
                map.put(reservedSeat[0]-1,tmp);
            }
        }

        Set<Integer> integers = map.keySet();
        for (Integer integer : integers) {
            boolean[] arr = map.get(integer);
            if (!arr[1] && !arr[2] && !arr[3] && !arr[4]) {
                arr[1] = !arr[1];
                arr[2] = !arr[2];
                arr[3] = !arr[3];
                arr[4] = !arr[4];
                count++;
            }
            if (!arr[3] && !arr[4] && !arr[5] && !arr[6]) {
                arr[6] = !arr[6];
                arr[5] = !arr[5];
                arr[3] = !arr[3];
                arr[4] = !arr[4];
                count++;
            }
            if (!arr[5] && !arr[6] && !arr[7] && !arr[8]) {
                count++;
            }
        }
        count += (n-map.size())*2;

//        System.out.println(count);
        return count;
    }

    public static void main(String[] args) {
        int[][] nums = {{4,3},{1,4},{4,6},{1,7}};
        maxNumberOfFamilies(4,nums);
    }
}
