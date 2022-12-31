package likou;

public class a1750minimumLength {
    public static void main(String[] args) {

        System.out.println(minimumLength("bbb"));
    }
    public static int minimumLength(String s) {
        int f = 0;
        int p = s.length() - 1;
        while (f < p && s.charAt(f) == s.charAt(p)){
            char tmp = s.charAt(f);
            while (s.charAt(f) == tmp){
                f++;
                if (f==p){
                    break;
                }
            }
            while (s.charAt(p) == tmp){
                p--;
                if (f==p){
                    break;
                }
            }
        }

        return p-f<0?0:p-f+1;
    }
}
