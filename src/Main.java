import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
        Thread thread1 = new MyThread();
        Thread thread2 = new MyThread();
        Thread thread3 = new MyThread();
        Thread thread4 = new MyThread();
        Thread thread5 = new MyThread();


        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();

        thread1.interrupt();
        thread2.interrupt();
        thread3.interrupt();
        thread4.interrupt();
        thread5.interrupt();


    }


}