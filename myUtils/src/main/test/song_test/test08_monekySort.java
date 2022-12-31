package song_test;

public class test08_monekySort {
    public static void main(String[] args) {
        int[] nums = {3,1,2,20,5};
        for (int num : nums) {
            fun(num);
        }

    }

    static public void fun(int num){
        MyThread myThread = new MyThread(num);
        Thread thread = new Thread(myThread);
        thread.start();
    }

   static class MyThread extends Thread{
        private int num;
        public MyThread(int num){
            this.num = num;
        }
        @Override
       public void run(){
            try {
                Thread.sleep(num*100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(num);
        }
   }

}
