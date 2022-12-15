package song_test;

import com.myUtils.DateUtil;

public class test03 {
    public static void main(String[] args) {
        String curDateTime = DateUtil.getCurDateTime("yyyyMMdd");
        System.out.println(curDateTime);
    }
}
