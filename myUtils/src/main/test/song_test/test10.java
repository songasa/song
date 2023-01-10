package song_test;

import myClass.MyInterface;
import myClass.MyInterface2;

public class test10 {
    public static void main(String[] args) {

        MyInterface myInterface = new MyInterface() {
            @Override
            public String fun1(String context) {
                return context+"fun1";
            }

            @Override
            public int fun2(int num) {
                return num+1;
            }
        };
        System.out.println(myInterface.fun1("xyz"));
        System.out.println(myInterface.fun2(0));

        MyInterface2 myInterface2 = a->a+2;
        System.out.println(myInterface2.fun2(0));

    }



}
