package likou;

import java.util.HashMap;
import java.util.Map;

public class a5longestPalindrome {

    public static String longestPalindrome(String s) {
        Map<Integer,String> map = new HashMap<>();
        map.put(1,s.charAt(0)+"");
        String res = "";
        return res;
    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome("babad"));
    }
}
