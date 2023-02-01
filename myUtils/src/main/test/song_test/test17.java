package song_test;

import java.util.StringJoiner;

public class test17 {
    public static void main(String[] args) {
//
        StringBuilder sb = new StringBuilder("a").append("b");

        StringJoiner sj = new StringJoiner(",中间","开头[","]结尾");

        sj.add("a");
        sj.add("a12");
        sj.add("a12");
        sj.add("a12");
        sj.add("a12");
        System.out.println(sj);
    }
}
