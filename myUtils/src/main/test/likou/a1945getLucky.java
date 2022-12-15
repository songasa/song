package likou;

import org.omg.PortableInterceptor.INACTIVE;

public class a1945getLucky {

    public static int getLucky(String s, int k) {
        int len = s.length();
        String t = "";
        for (int i = 0; i < len; i++) {
            t = t + (Integer.valueOf(s.charAt(i)) -96) + "";
        }
        for (int i = 0; i < k; i++) {
            t = fun(t) + "";
        }
        return Integer.parseInt(t);
    }

    public static int fun(String s){
        int res = 0;
        int len = s.length();
            for (int i = 0; i < len; i++) {
                res = res + Integer.parseInt(s.charAt(i)+"");
            }
        return res;
    }

    public static void main(String[] args) {
        getLucky("iiii",1);
    }
}
