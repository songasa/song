package likou;

public class a2180countEven {
    public static void main(String[] args) {
        System.out.println(countEven(4));//2
        System.out.println(countEven(9));//4
        System.out.println(countEven(10));//4
        System.out.println(countEven(11));//5
        System.out.println(countEven(30));//14
        System.out.println(countEven(31));//15
        System.out.println(countEven(32));//15
        System.out.println(countEven(39));//19
        System.out.println(countEven(40));//20
    }

    public static int countEven(int num) {
        int res = -1;
        String str = num+"";
        res += num/10*5;
        int tmp = num%10;
        int len = str.length();
        int sum = 0;
        for (int i = 0; i < len-1; i++) {
            sum += str.charAt(i);
        }
        if (sum%2 == 0){
            switch (tmp){
                case 0: res+=1;break;
                case 1: res+=1;break;
                case 2: res+=2;break;
                case 3: res+=2;break;
                case 4: res+=3;break;
                case 5: res+=3;break;
                case 6: res+=4;break;
                case 7: res+=4;break;
                case 8: res+=5;break;
                case 9: res+=5;break;
            }
        }else {
            switch (tmp){
                case 1: res+=1;break;
                case 2: res+=1;break;
                case 3: res+=2;break;
                case 4: res+=2;break;
                case 5: res+=3;break;
                case 6: res+=3;break;
                case 7: res+=4;break;
                case 8: res+=4;break;
                case 9: res+=5;break;
            }
        }
        return res;
    }
}
