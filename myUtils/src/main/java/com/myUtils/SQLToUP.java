package com.myUtils;

import java.util.List;

public class SQLToUP {
    static final String path = "..\\resource\\SQLToUP_SYMBOLS.txt";
    private static StringBuilder[] symbols;
    public static void main(String[] args) {
        //String path = "d:\\Users\\songxq\\Desktop\\workspace\\idea\\songxianqi\\myUtils\\src\\main\\resource\\SQLToUP_SYMBOLS.txt";
        List<String> list = new readFiles().readFile(path);
        list.stream().forEach(s -> s.toLowerCase());
        System.out.println(list);
        for (Object o : list) {
            System.out.println(o);
        }

    }

    public void HandlerSQL(String sql){

    }

}
