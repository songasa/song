package song_test;

import myClass.OuterClass;
//import myClass.OuterClass.InnerClass;

public class test11 {
    public static void main(String[] args) {
        OuterClass outerClass = new OuterClass();
//        new OuterClass().
        OuterClass.InnerClass a = new OuterClass().new InnerClass();
        a.print();
    }
}
