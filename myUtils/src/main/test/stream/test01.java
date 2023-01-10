package stream;

import myClass.MyInterface;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class test01 {
    public static void main(String[] args) {
        MyListClass myListClass = new MyListClass();
        List<MyListClass> lists = myListClass.getTestLists();
        Optional<MyListClass> collect =
                lists.stream()
                        .parallel() // 并行流处理 加速 乱序
//                        .max(Comparator.comparing(MyListClass::getAge))
//                        .min(Comparator.comparing(MyListClass::getBz))
                .filter(a -> a.isFlag() == true && a.getAge() == 3) // 过滤 可以用 && || 连接多个条件
//                        .count()
//                        .skip(1).limit(2)
//                .forEach(a -> System.out.println(a.getName()))
//                .collect(Collectors.toList())
                .findAny()
                ;
        System.out.println(collect.isPresent()+"   "+collect.get());
    }
}
