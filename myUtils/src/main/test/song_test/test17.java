package song_test;

public class test17 {
    public static void main(String[] args) {
        int a = 3;
        int b = (++a) + (a++) + (a * 10);
        System.out.println(a);
        System.out.println(b);
    }
}
