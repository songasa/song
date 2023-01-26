package likou;

public class a2303calculateTax {
    public static void main(String[] args) {
        int[][] br = {{10,10}};
        System.out.println(calculateTax(br,5));
    }

    public static double calculateTax(int[][] brackets, int income) {
        double res = 0d;
        int len = brackets.length;
        if (income < brackets[0][0]){
            return income * brackets[0][1] / 100.0;
        }
        if (income > brackets[0][0] && income < brackets[1][0]){
            return (brackets[0][0] * brackets[0][1] + ((income - brackets[0][0]) * brackets[1][1])) / 100.0;
        }
        res += brackets[0][0] * brackets[0][1];
//        res += (brackets[1][0] - brackets[0][0]) * brackets[1][1];
        int tmp = 0;
        for (int i = 1; i < len; i++) {
            if (income < brackets[i][0]){
                res += (income - brackets[i-1][0]) * brackets[i][1];
                return res/100.0;
            }
            res += (brackets[i][0] - brackets[i-1][0]) * brackets[i][1];
        }
        return res/100.0;
    }
}
