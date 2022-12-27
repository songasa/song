package song_test;

import java.util.ArrayList;

public class test04 {
    public static void main(String[] args) {
        fun1();
        fun2();
    }
    static void fun1(){
        ArrayList<Integer> be = new ArrayList<>();
        be.add(1);
        ArrayList<ArrayList<Integer>> end = new ArrayList<>();
        for (int i = 1; i < 4; i++) {
            ArrayList<Integer> list = be;
            list.set(0,i);
            end.add(list);
        }
        System.out.println(end.toString());
    }
    static void fun2(){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(-1);
        ArrayList<ArrayList<Integer>> end = new ArrayList<>();
        for (int i = 1; i < 4; i++) {
            list.set(0,i);
            end.add((ArrayList<Integer>) list.clone());
        }
        System.out.println(end.toString());
    }
}
