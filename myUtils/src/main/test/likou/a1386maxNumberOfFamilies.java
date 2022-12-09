package likou;

public class a1386maxNumberOfFamilies {
    public static int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        if (n > 1000000000){
            return -1;
        }
        boolean[][] arrs = new boolean[n][10];
        int count = 0;
        for (int[] reservedSeat : reservedSeats) {
            arrs[reservedSeat[0]-1][reservedSeat[1]-1] = true;
        }
        for (boolean[] arr : arrs) {
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

//        System.out.println(count);
        return count;
    }

    public static void main(String[] args) {
        int[][] nums = {{1,2},{1,3},{1,8},{2,6},{3,1},{3,10}};
        maxNumberOfFamilies(3,nums);
    }
}
