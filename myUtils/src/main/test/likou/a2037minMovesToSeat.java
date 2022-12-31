package likou;

public class a2037minMovesToSeat {
    public static void main(String[] args) {

    }

    public static int minMovesToSeat(int[] seats, int[] students) {
        int res = 0;
        int len = seats.length;
        for (int i = 0; i < len; i++) {
            res += seats[i] - students[i];
        }
        return Math.abs(res);
    }
}
