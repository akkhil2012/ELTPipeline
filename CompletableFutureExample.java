package testPackage;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureExample {

    public static void main(String args[]) throws ExecutionException, InterruptedException {

        CompletableFuture<String>
                  future = CompletableFuture.supplyAsync(()->{
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return  "Thread completed " + Thread.currentThread().getName();
                }
                );

        System.out.println(" Some oTher task...........");
       // future.get();
        while (!future.isDone()) {
            String result = future.get(); // This will not block if the result is already available
            System.out.println("Result: " + result);
        }

       // System.out.println(" Some oTher task...........");
    }
}
