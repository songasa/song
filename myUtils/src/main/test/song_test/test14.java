package song_test;

import java.util.ArrayList;
import java.util.List;

public class test14 {
    public static void main(String[] args) {
        List<List> lists = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            List list = new ArrayList();
            list.add(i);
            lists.add(list);
        }
        lists.stream().forEach(list -> {
            System.out.println(list.toString());
        });
    }
}
