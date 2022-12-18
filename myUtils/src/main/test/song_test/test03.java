package song_test;

import com.myUtils.DateUtil;

public class test03 {
    public static void main(String[] args) {
        String curDateTime = DateUtil.getCurDateTime("yyyyMMdd");
        System.out.println(curDateTime);

        if (1==2){
            System.out.println(1);
        }else if (1==1){
            System.out.println(2);
        }else {

        }
    }
}
