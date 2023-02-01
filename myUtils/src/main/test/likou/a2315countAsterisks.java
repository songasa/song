package likou;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class a2315countAsterisks {
    public static void main(String[] args) {
        System.out.println(countAsterisks("ab**c|ab*|ex*z|c*d|asa"));
        System.out.println(countAsterisks2("ab**c|ab*|ex*z|c*d|asa"));
    }
    public static int countAsterisks(String s) {
        int res = 0;
        int len = s.length();
        boolean flag = true;
        for (int i = 0; i < len; i++) {
            if ("|".equals(s.charAt(i)+"")) {
                flag = !flag;
                continue;
            }
            if (flag && "*".equals(s.charAt(i)+"")){
                res++;
            }
        }
        return res;
    }

    public static int countAsterisks2(String s) {
        int res = 0;
        Pattern pattern = Pattern.compile("\\|[a-z,*]*\\|");
        Matcher matcher = pattern.matcher(s);
        String s1 = matcher.replaceAll("");
        System.out.println(s1);
        Pattern pattern1 = Pattern.compile("\\*");
        Matcher matcher1 = pattern1.matcher(s1);
        while (matcher1.find()) res++;
        return res;
    }
}
