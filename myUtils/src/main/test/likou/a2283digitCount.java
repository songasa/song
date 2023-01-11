package likou;

public class a2283digitCount {
    public static boolean digitCount(String num) {
        boolean res = true;
        int len = num.length();
        int[] tmp = new int[11];
        for (int i = 0; i < len; i++) {
            tmp[Integer.parseInt(num.charAt(i)+"")] += 1;
        }
        for (int i = 0; i < len; i++) {
            if (Integer.parseInt(num.charAt(i)+"") != tmp[i]){
                return false;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(digitCount("030"));
    }
}
