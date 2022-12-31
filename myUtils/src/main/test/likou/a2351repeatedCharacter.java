package likou;

import java.util.HashMap;
import java.util.Map;

public class a2351repeatedCharacter {
    public static void main(String[] args) {

        System.out.println(repeatedCharacter("abccbaacz"));
    }
//    public static char repeatedCharacter(String s) {
//        char res = 'a';
//        int len = s.length();
//        Map<Character,Integer> map = new HashMap<>();
//        for (int i = 0; i < len; i++) {
//            if (map.containsKey(s.charAt(i))){
//                return s.charAt(i);
//            }else {
//                map.put(s.charAt(i),0);
//            }
//        }
//        return res;
//    }

    public static char repeatedCharacter(String s) {
        char res = 'a';
        int len = s.length();
        boolean[] boo = new boolean[26];
        for (int i = 0; i < len; i++) {
            if (boo[Integer.valueOf(s.charAt(i))-97]){
                return s.charAt(i);
            }else {
                boo[Integer.valueOf(s.charAt(i))-97] = true;
            }
        }
        return res;
    }
}
