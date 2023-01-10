package song_test;


public class test12 {
    public static void main(String[] args) throws Throwable {
        fun();
    }

    public static int fun() throws Exception {
        try {
            int i = 10/0;
        }catch (Exception e){
            String message = e.getMessage();
            throw new Exception(message);
        }

        return -1;
    }
}
