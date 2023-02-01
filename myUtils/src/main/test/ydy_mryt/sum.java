package ydy_mryt;

import java.math.BigDecimal;
import java.util.Scanner;

public class sum {
    private static String grade = "B";
    private static double hour_ps;
    private static double hour_zm;
    private static double hour_fd;
    private static BigDecimal base = new BigDecimal("5260");
    private static BigDecimal jb = new BigDecimal("2030");


    public static void main(String[] args) {
        BigDecimal all = new BigDecimal("0");
        Scanner sc = new Scanner(System.in);
        System.out.println("ps");
        hour_ps = sc.nextDouble();
        System.out.println("zm");
        hour_zm = sc.nextDouble();
        System.out.println("fd");
        hour_fd = sc.nextDouble();

        System.out.println(hour_ps+hour_zm+hour_fd);
    }
}
