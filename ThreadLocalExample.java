package multithreading;

public class ThreadLocalExample {

    public static void main(String args[]) {

        ThreadLocal<String> threadLocal = new ThreadLocal<>();


        new Thread(() -> {
            System.out.println(" Thread 1 started");
            threadLocal.set("firstValue");
            System.out.println(" Thread local value for Thread 1 is ::: " + threadLocal.get());
        }).start();

        new Thread(() -> {
            System.out.println(" Thread 2 started");
            threadLocal.set("SecValue");
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(" Thread local value for Thread 2 is ::: " + threadLocal.get());
        }).start();


        System.out.println(" Thread local value for string is ::: " + threadLocal.get());

    }
}
