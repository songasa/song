package likou;

public class a1663getSmallestString {
    public static void main(String[] args) {
//        char c = 97;
//        System.out.println(c);
        String s = "a";
        System.out.println();
        System.out.println(getSmallestString(9, 34));
        System.out.println(getSmallestString(5, 130));
    }

    public static String getSmallestString(int n, int k) {
        StringBuilder res = new StringBuilder();
        k = k - n;
        int yu = k % 25;
        int z = k / 25;
        int a = yu==0?n - z:n - z - 1;
        int x = yu==0?0:1;
        while (a-->0){
            res.append("a");
        }while (x-->0){
            char tmp = (char) (yu+97);
            res.append(tmp);
        }while (z-->0){
            res.append("z");
        }
        return res.toString();
    }
}
