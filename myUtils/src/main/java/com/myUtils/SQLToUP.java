package com.myUtils;

import java.util.List;

public class SQLToUP {
    private static StringBuilder[] symbols;
    public static void main(String[] args) {
        String path = "d:\\Users\\songxq\\Desktop\\workspace\\idea\\songxianqi\\myUtils\\src\\main\\resource\\SQLToUP_SYMBOLS.txt";
        List list = new readFiles().readFile(path);
        for (Object o : list) {
            System.out.println(o);
        }

    }

}
