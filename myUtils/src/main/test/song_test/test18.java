package song_test;

public class test18 {
    public static void main(String[] args) {
        System.out.println(fun());
    }

    public static int fun(){
        int i = 0;
        try {
            i = i + 1;
            return i;
        }finally {
            i = i + 1;
        }
    }

    public static int fun2(){

            System.out.println(1);
            return -2;

    }
}
