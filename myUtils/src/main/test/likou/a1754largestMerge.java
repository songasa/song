package likou;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class a1754largestMerge {
    public static String largestMerge(String word1, String word2) {
        String res = "";

        return res;
    }

    public static void main(String[] args) {

        Set<String> set = new TreeSet<String>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.parseInt(o1)-Integer.parseInt(o2);
            }
        });
        for (int i = 20; i > -1; i--) {
            set.add(i+"");
        }
        

        System.out.println(set.toString());

    }

    public static String[] fun(String word1, String word2){
        String[] res = new String[3];
        int len1 = word1.length();
        int len2 = word2.length();
        if(len1<=len2){
            for (int i = 0; i < len1; i++) {
        }
        }else {

        }

        return res;
    }
}
