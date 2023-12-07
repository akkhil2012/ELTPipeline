package lld;

import java.util.HashMap;
import java.util.Map;

public class RateLimiterExample {

    public static void main(String ars[]) throws InterruptedException {

        RateLimiter rateLimiter = new RateLimiter();
        for(int i=0;i<6;i++){
            new Thread(()-> rateLimiter.isRequestValid("Thread1")).start();
            Thread.sleep(1000);
        }


        for(int i=0;i<6;i++){
            new Thread(()-> rateLimiter.isRequestValid("Thread2")).start();
           // Thread.sleep(1000);
        }
    }

}


class RateLimiter {

    private int TIME_PERIOD_TO_ELAPSE = 9;
    private int COUNTS_OF_REQUESTS_ALLOWED = 5;

    private Map<String, UserData> userToRequestMap;


    public RateLimiter() {
        userToRequestMap = new HashMap<>();
    }

    public boolean isRequestValid(String user) {
        long currentRequestTime = System.currentTimeMillis();

        if (userToRequestMap.get(user) == null) {
            userToRequestMap.put(user, new UserData(currentRequestTime, 1));
            System.out.println("Request is valid...");
            return true;
        }
        long lastRequestMadeAtTime = userToRequestMap.get(user).getLastCallAt();
        long subsCallDiff = currentRequestTime - lastRequestMadeAtTime;
        if (subsCallDiff < TIME_PERIOD_TO_ELAPSE) {
            System.out.println("Request is INVALID...");
            return false;
        } else {
            if (COUNTS_OF_REQUESTS_ALLOWED > userToRequestMap.get(user).getCount()) {
                userToRequestMap.get(user).setLastCallAt(currentRequestTime);
                userToRequestMap.get(user).setCount(userToRequestMap.get(user).getCount() + 1);
                System.out.println("Request is valid...");
                return true;
            }
        }
        System.out.println("Request is INVALID...");
        return false;
    }

}


class UserData {
    private long lastCallAt;
    private int count;

    public int getCount() {
        return count;
    }

    public void setLastCallAt(long lastCallAt) {
        this.lastCallAt = lastCallAt;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public long getLastCallAt() {
        return lastCallAt;
    }

    public UserData(long lastCallAt, int count) {
        this.lastCallAt = lastCallAt;
        this.count = count;
    }
}

