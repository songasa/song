package likou;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class a2042areNumbersAscending {
    public static void main(String[] args) {
        System.out.println(areNumbersAscending("1 a 2 cd 3 qw 14 14"));
    }

    public static boolean areNumbersAscending(String s) {
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(s);
        int t = Integer.MIN_VALUE;
        while(m.find()) {
            if (Integer.valueOf(m.group()) <= t){
                return false;
            }else {
                t = Integer.valueOf(m.group());
            }
//            System.err.println("获取正则表达式匹配到的结果：" + m.group());

        }
        return true;
    }
}
