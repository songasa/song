package com.myUtils;

import com.sun.org.apache.xpath.internal.objects.XString;

import java.util.List;

public class SQLToUP {
    static final  String localPath = System.getProperty("user.dir");
    static final String path = localPath + "\\myUtils\\src\\main\\resource\\SQLToUP_SYMBOLS.txt";

    public String HandlerSQL(String sql){
        List<String> list = new readFiles().readFile(path);
        sql = sql.toLowerCase();
        for (String s : list) {
            s = s.toLowerCase();
            sql = sql.replace(s,s.toUpperCase());
        }
        return sql;
    }

}
