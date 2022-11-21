package com.myUtils;

import org.junit.Test;
import org.junit.runners.Parameterized;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class readFiles {

//    final static String path1 =
//    "d:\\Users\\songxq\\Desktop\\workspace\\idea\\songxianqi\\myUtils\\src\\main\\resource\\SQLToUP_SYMBOLS.txt";

//    final static String path2 = "d:\\Users\\songxq\\Desktop\\workspace\\idea\\songxianqi\\myUtils\\src\\main\\resource\\Write.txt";

//    static String arr = "zhangsan,23,福建";


    //读取文件成字符串数组
    public static List readFile(String path){
        try{
            FileInputStream fis=new FileInputStream(path);
            InputStreamReader isr=new InputStreamReader(fis, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String line="";
            List<String> list = new ArrayList<String>();
            while ((line=br.readLine())!=null) {
                list.add(line);
//                System.out.println(line);
            }
            br.close();
            isr.close();
            fis.close();
            return list;
        }catch (Exception e){
            System.out.println(e);
            final String error = e.toString();
            return new ArrayList<String>(){{ add(error); }};
        }

    }



    //写入context 到指定路径下 覆盖
    public static void writeFileCover(String path,String context){
        try{
            //写入中文字符时解决中文乱码问题
            FileOutputStream fos=new FileOutputStream(new File(path));
            OutputStreamWriter osw=new OutputStreamWriter(fos, "UTF-8");
            BufferedWriter  bw=new BufferedWriter(osw);

            bw.write(context+"\t\n");

            //注意关闭的先后顺序，先打开的后关闭，后打开的先关闭
            bw.close();
            osw.close();
            fos.close();
        }catch (Exception e){
            System.out.println(e);
            return;
        }
    }

    //写入context 到指定路径下 追加
    public static void writeFileAppend(String path,String context){
//        String path = "d:\\Users\\songxq\\Desktop\\workspace\\idea\\songxianqi\\myUtils\\src\\main\\resource\\SQLToUP_SYMBOLS.txt";;
//        String context = "when";
        try{
            File file = new File(path);
            FileOutputStream fos = null;
            if(!file.exists()){
                file.createNewFile();//如果文件不存在，就创建该文件
                fos = new FileOutputStream(path);//首次写入获取
            }else{
                //如果文件已存在，那么就在文件末尾追加写入
                fos = new FileOutputStream(file,true);//这里构造方法多了一个参数true,表示在文件末尾追加写入
            }
            OutputStreamWriter osw=new OutputStreamWriter(fos, "UTF-8");
            BufferedWriter  bw=new BufferedWriter(osw);

            bw.write(context+"\n");

            //注意关闭的先后顺序，先打开的后关闭，后打开的先关闭
            bw.close();
            osw.close();
            fos.close();
        }catch (Exception e){
            System.out.println(e);
            return;
        }
    }


}
