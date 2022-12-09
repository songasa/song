package likou;

public class a1812squareIsWhite {

    public static boolean squareIsWhite(String coordinates) {
        char[] c = coordinates.toCharArray();
        return (Integer.valueOf(c[0])+Integer.valueOf(c[1])) % 2 == 0?false:true;
    }

    public static void main(String[] args) {
        System.out.println(squareIsWhite("a1"));
    }

}
