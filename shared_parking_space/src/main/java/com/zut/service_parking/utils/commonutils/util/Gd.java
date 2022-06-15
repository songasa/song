package com.zut.service_parking.utils.commonutils.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Gd {

    /**
     * @param args
     */

//高德KEY
    private static String KEY = "42180226155d89ad5045291e8bdeb5ea";
//百度KEYB
    private static String KEYB = "wy3yeQ11evApuyNCneRGtn9IhCiVWF8Z";
    private static Pattern pattern= Pattern.compile("\"location\":\"(\\d+\\.\\d+),(\\d+\\.\\d+)\"");
    public static String[] addressToGPS(String address) {
        try {
            String url = "http://restapi.amap.com/v3/geocode/geo?address="
                    +address+
                    "&output=JSON&key=42180226155d89ad5045291e8bdeb5ea";
            URL myURL = null;
            URLConnection httpsConn = null;
            try {
                myURL = new URL(url);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            InputStreamReader insr = null;
            BufferedReader br = null;
            httpsConn = (URLConnection) myURL.openConnection();// 不使用代理
            if (httpsConn != null) {
                insr = new InputStreamReader( httpsConn.getInputStream(), "UTF-8");
                br = new BufferedReader(insr);
                String data = "";
                String line = null;
                while((line= br.readLine())!=null){
                    data+=line;
                }
                int begin = data.indexOf("adcode");
                Matcher matcher = pattern.matcher(data);
                if (matcher.find() && matcher.groupCount() == 2) {
                    String[] gps = new String[3];
                    gps[0] = String.valueOf(matcher.group(1));
                    gps[1] = String.valueOf(matcher.group(2));
                    gps[2] = data.substring(begin+9,begin+15);
                    return gps;
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
    public static String[] GPSToAddress(String lng,String lat){
            try {
                String url = "http://restapi.amap.com/v3/geocode/regeo?key="+KEY+
                        "&location="+lng+","+lat;
                URL myURL = null;
                URLConnection httpsConn = null;
                try {
                    myURL = new URL(url);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                InputStreamReader insr = null;
                BufferedReader br = null;
                httpsConn = (URLConnection) myURL.openConnection();// 不使用代理
                if (httpsConn != null) {
                    insr = new InputStreamReader( httpsConn.getInputStream(), "UTF-8");
                    br = new BufferedReader(insr);
                    String data = "";
                    String line = null;
                    while((line= br.readLine())!=null){
                        data+=line;
                    }
//                System.out.println(url);
//                    System.out.println(data);
                    int begin = data.indexOf("adcode");
                    int begin_address = data.indexOf("formatted_address");
                    int begin_address_end = data.indexOf("\"},\"info\":");
                    String[] res = new String[2];
                    res[0] = data.substring(begin+9,begin+15);
                    res[1] = data.substring(begin_address+20,begin_address_end);
//                    System.out.println(res[0]);
//                    System.out.println(res[1]);
                    return res;
                }
            }catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        return null;
    }
    public static String[] IPToGPS(String IP){
//高德地图定位2.0 4月30号下线，所以用百度的api，再转换为高德经纬度输出
        int type = 0;
        try {
            if(IP.indexOf(":")>0){
                type = 6;
            }else {
                type = 4;
            }
//            String url = "https://restapi.amap.com/v5/ip?key="+KEY+
//                    "&type="+type+"&ip="+IP;

            String url = "https://api.map.baidu.com/location/ip?ak="+KEYB+
                    "&ip="+IP +"&coor=bd09ll";
            URL myURL = null;
            URLConnection httpsConn = null;
            try {
                myURL = new URL(url);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            InputStreamReader insr = null;
            BufferedReader br = null;
            httpsConn = (URLConnection) myURL.openConnection();// 不使用代理
            if (httpsConn != null) {
                insr = new InputStreamReader( httpsConn.getInputStream(), "UTF-8");
                br = new BufferedReader(insr);
                String data = "";
                String line = null;
                while((line= br.readLine())!=null){
                    data+=line;
                }
//                System.out.println(data);
                int xbegin = data.indexOf("\"point\":{\"x\":\"");
                int xend = data.indexOf("\",\"y\":\"");
                String lngstr = data.substring(xbegin+14,xend);
                int ybegin = data.indexOf("\",\"y\":\"");
                int yend = data.indexOf("\"}},\"status\":");
                String latstr = data.substring(ybegin+7,yend);
                String[] res = baiduToGaode(Double.valueOf(lngstr),Double.valueOf(latstr));
//                System.out.println(res[0]);
//                System.out.println(res[1]);
                return res;
            }
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("IP类型:IPV"+type);
            return null;
        }
        return null;
    }


    //百度地图经纬度转高德地图经纬度
    private static String[] baiduToGaode(double lng,double lat){
        double x_pi = 3.14159265358979324 * 3000.0 / 180.0;
        double x = lng - 0.0065;
        double y = lat - 0.006;
        double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * x_pi);
        double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * x_pi);
        double lngs = z * Math.cos(theta);
        double lats = z * Math.sin(theta);
        String[] res = {lngs+"",lats+""};
        return res;
    }



    public static void main(String[] args) {
        String[] data = Gd.addressToGPS("河南省郑州市中原区林山寨街道中原中路郑州市人民政府");
//////        double [] data = Gd.addressToGPS("河南省新乡市牧野区后河头镇");
        System.out.println("经度:"+data[0]);
        System.out.println("纬度:"+data[1]);
        System.out.println(data[2]);
//
//            Gd.GPSToAddress("113.625360","34.746333");
//        Gd.IPToGPS("117.159.17.214");
    }
}

//经度:113.954815
//纬度:35.364631

//经度:113.941032
//纬度:35.351321