package matchs;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class matchTest02 {
    public static void main(String[] args) {
//        String str = "5dog is cat";
//        String p = "[0-5]d.*";
//        boolean matches = Pattern.matches(p, str);
//
//        System.out.println(matches);




        String url="abcd2.class";
//        Pattern p=Pattern.compile("\\?*[0-9]$");
        Pattern p=Pattern.compile(".*\\d\\.class$");
        Matcher m=p.matcher(url);
        while(m.find()) {
            System.err.println("获取正则表达式匹配到的结果：" + m.group());
            url = url.replace(m.group(),"数字");
        }
        System.err.println("" + url);


        BigDecimal a = new BigDecimal("0.1");
        BigDecimal b = new BigDecimal("3");
        System.out.println(a.multiply(b));
        System.out.println(0.1*3);





    }
}
