package song_test;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class test05 {
    public static void main(String[] args) {

        List list = new ArrayList();
//        list.stream().filter()
        String ss = MessageFormat.format("A{0}", "SS");
        System.out.println(ss);
    }
}
