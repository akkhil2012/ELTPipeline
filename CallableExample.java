package testPackage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableExample {


    public static void main(String args[]) throws ExecutionException, InterruptedException {

        ExecutorService service = Executors.newFixedThreadPool(2);

        Callable<String> callableTask = () ->{
            Thread.sleep(3000);
            return  "testForCallable";
        };

       // service.submit(new RunnableTask());
        //Future<String> res =  service.submit(callableTask);
        //System.out.println(" Result of future is " + res.get());

        List<Callable> callableList = new ArrayList<>();
        callableList.add(callableTask);

       // callableList.stream().forEach(task -> service.submit(task));

        List<Future> listOfFuture = new ArrayList<>();
        for(int i=0;i<10;i++){
            Future<String> future = service.submit(callableTask);
            listOfFuture.add(future);
        }
       // Future<String> future = service.submit(callableTask);

        //System.out.println("<------- Some unRelated TAsks--------->");


        for(int i=0;i<10;i++){
            Future<String> future = listOfFuture.get(i);
            System.out.println("Result of future "+ i +" is :: " + future.get());
        }

        System.out.println("<------- Some unRelated TAsks--------->");

       // System.out.println("Future result is " + future.get());
        //service.submit()



        service.shutdownNow();
    }
}

class RunnableTask implements  Runnable{

    @Override
    public void run() {
        System.out.println("Runnable Task......");
    }
}







