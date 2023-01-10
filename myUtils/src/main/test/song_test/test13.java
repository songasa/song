package song_test;

import stream.MyListClass;

import java.util.Optional;


public class test13 {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("a");
        fun(sb);
        sb = null;
    }

    public static void fun(StringBuilder sb){
        sb.append(1);
        MyListClass myListClass = new MyListClass();
        sb.append(myListClass.getTestLists());
        System.out.println(sb);
        System.gc();
    }
}
