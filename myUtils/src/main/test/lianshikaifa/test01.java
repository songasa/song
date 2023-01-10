package lianshikaifa;

public class test01 {
    public static void main(String[] args) {
        lianShi ls1 = new lianShi("song",129);
        lianShi ls2 = new lianShi().setAge(129).setName("song");
        System.out.println(ls1.toString());
        System.out.println(ls2.toString());
        Integer age1 = ls1.getAge();
        Integer age2 = ls2.getAge();
        System.out.println(ls1.equals(ls2));
        System.out.println(age1==age2);
        System.out.println(ls1.getAge()==ls2.getAge());

//        String a = "1";
//        String b = "1";
//        System.out.println(a==b);
//        System.out.println(a.equals(b));
//
//
//        String c = new String("1");
//        String d = new String("1");
//        System.out.println(c==d);
//        System.out.println(c.equals(d));

    }
}
