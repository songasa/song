package com.zut.shared_parking_space;

public class baiduToGaode {
    public static void main(String[] args) {
        double lng = 113.63141921;
        double lat = 34.75343885;
        double x_pi = 3.14159265358979324 * 3000.0 / 180.0;
        double x = lng - 0.0065;
        double y = lat - 0.006;
        double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * x_pi);
        double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * x_pi);
        double lngs = z * Math.cos(theta);
        double lats = z * Math.sin(theta);
        System.out.println(lngs);
        System.out.println(lats);
    }
}
