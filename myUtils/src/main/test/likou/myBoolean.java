package likou;

public class myBoolean {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(fun());
        }
    }
    public static boolean fun(){
        return (Math.random()*10)%2>=1;
    }
}
