package lld;

import java.util.*;
public class LRUCacheExample {

}



class LRUCache{
    // capacity
    // add
    // get
    // delete:
    int capacity;
    Map<Integer,Integer> map;
    Queue<Integer> q = new ArrayDeque<>();

    public LRUCache(int capacity, Map<Integer, Integer> map, Queue<Integer> q) {
        this.capacity = capacity;
        this.map = map;
        this.q = q;
    }

    public void put(int key, int value){
        if(map.containsKey(key)){
            q.remove(key);
            q.offer(key);
        }else{
            if(q.size()==capacity){
                q.remove(q.poll());
                q.offer(key);
            }
        }
        map.put(key,value);

    }

    public int get(int key){
        if(map.containsKey(key)){
            q.remove(key);
            q.offer(key);
            return  map.get(key);
        }
        return  -1;
    }

}
