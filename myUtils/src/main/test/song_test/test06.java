package song_test;

import java.lang.annotation.*;

public class test06 {

    //自定义一个User注解
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    @Inherited
    public @interface User{
        String a() default "abc";

    }

    @User(a = "d")
    public void fun(){
    }

    public static void main(String[] args) throws ClassNotFoundException {

    }
}
