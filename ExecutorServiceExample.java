package lld;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceExample {

    static Task task = new Task();

    public static void main(String args[]) throws InterruptedException {

        ExecutorService service = Executors.newCachedThreadPool();

        long start  = System.currentTimeMillis();
        //for (int i = 0; i < 1000; i++) {
            service.execute(() -> {
                try {
                    System.out.println(" Thread running is " + Thread.currentThread().getName());
                    task.readFromStream();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        //}

        //System.out.println(" Time taken is " + (System.currentTimeMillis()-start));
        service.awaitTermination(5, TimeUnit.SECONDS);
        if(!service.awaitTermination(5, TimeUnit.SECONDS)){
            System.out.println(" TimeOut occred");
            service.shutdown();
        }

        System.out.println(" Time taken is " + (System.currentTimeMillis()-start));

    }
}


class Task {

    private String name;

    public void readFromStream() throws InterruptedException {
        Thread.sleep(3000);
    }
}
