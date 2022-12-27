package likou;

public class a2027minimumMoves {
    public static void main(String[] args) {
        System.out.println(minimumMoves("XXX"));
    }

    public static int minimumMoves(String s) {
        int res = 0;
        int len = s.length();
        for (int i = 0; i < len; i++) {
            if ("X".equals(s.charAt(i)+"")){
                res++;
                i = i + 2;
            }
        }
        return res;
    }
}
