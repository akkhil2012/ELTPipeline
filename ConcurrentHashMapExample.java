package testPackage;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrentHashMapExample {

    public static void main(String args[]) {

        Map<Integer,Integer> mp = Map.of(1,11,2,22,3,33,4,44,5,55,6,66);

        /*
          Concurrent
         */
        ConcurrentHashMap<Integer,Integer> chm = new ConcurrentHashMap<>(mp);

       // mp.entrySet().forEach(en -> System.out.println(" "+ en.getKey()+" : " + en.getValue()));


        /*
          Concurrent
         */
        for(Map.Entry<Integer,Integer> e : chm.entrySet()){
            System.out.println(" " + e);
            if(e.getValue()==33){
                chm.put(e.getKey(),99);
            }
        }


        /*
          Plain HashMap
         */
        ConcurrentHashMap<Integer,Integer> chm1 = new ConcurrentHashMap<>(mp);
        for(Map.Entry<Integer,Integer> e : chm1.entrySet()){
            System.out.println("-- " + e);
            if(e.getValue()==33){
                chm1.put(e.getKey(),101);
            }
        }

    }
}
