package song_test;

import com.myUtils.DateUtil;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import static javafx.scene.input.KeyCode.L;

public class test15 {
    public static void main(String[] args) {
        System.out.println(TimeZone.getDefault());
        Date date = new Date();
        System.out.println(date);
        String yyyy_mm_dd =
                new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss", Locale.CHINA)
                        .format(date);
        System.out.println(yyyy_mm_dd);

    }
}
