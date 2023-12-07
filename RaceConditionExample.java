package multithreading;

import java.util.concurrent.atomic.AtomicLong;

public class RaceConditionExample {

    public static void main(String args[]) {

        CounterSyncronized counter = new CounterSyncronized();
        new Thread(getRunnable(counter, "first thread "))
                .start();
        new Thread(getRunnable(counter, "Second thread "))
                .start();
    }

    private static Runnable getRunnable(CounterSyncronized counter, String message) {

        return () -> {
            for (int i = 0; i < 10000; i++) {
                counter.incGet();
            }

            System.out.println(message + " : " + counter.getCount());
        };

    }


}

// Race Condition
/*class Counter {


    private long count = 0;

    public long incGet() {
        return count++;
    }

    public long getCount() {
        return count;
    }

}
*/


// Make it syncronized explicitly:
class CounterSyncronized {


    private long count = 0;

    public long incGet() {
        synchronized (this){
            count++;
        }
        return count;
    }

    public long getCount() {
        return count;
    }

}


class Counter {


    // private long count = 0;
    private AtomicLong count = new AtomicLong();

    public long incGet() {
        return count.incrementAndGet();
    }

    public long getCount() {
        return count.get();
    }

}