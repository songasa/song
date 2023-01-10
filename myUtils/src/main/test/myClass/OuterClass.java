package myClass;

public class OuterClass {
    public String name;

    public class InnerClass{
        private String inner;
        public String print(){
            System.out.println("OuterClass.InnerClass.print");
            return "OuterClass.InnerClass.print";
        }
    }

    //private
    class InnerDefaultClass{
        private String inner;
        public String print(){
            System.out.println("OuterClass.InnerDefaultClass.print");
            return "OuterClass.InnerDefaultClass.print";
        }
    }



}
