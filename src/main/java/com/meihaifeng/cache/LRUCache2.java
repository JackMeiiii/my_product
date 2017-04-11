package com.meihaifeng.cache;

import com.alibaba.dubbo.common.utils.*;
import com.alibaba.dubbo.common.utils.LRUCache;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 浙江卓锐科技股份有限公司
 *
 * @author meihf
 * @create 2017/4/7
 * @description
 */
public class LRUCache2<K,V> extends LinkedHashMap<K,V>{

    private static float DEFAULT_FACTOR = 0.75f;
    private int casheSize;
    private Lock lock = new ReentrantLock();

    public int getCasheSize() {
        return casheSize;
    }

    public void setCasheSize(int casheSize) {
        this.casheSize = casheSize;
    }

    private static int DEFAULT_MAX_CACHESIZE = 3;

    @Override
    public boolean removeEldestEntry(Map.Entry<K,V> old){
        return size()>casheSize;
    }

    public LRUCache2(int casheSize){
        super(casheSize,DEFAULT_FACTOR,true);
        this.casheSize = casheSize;
    }

    public V put(K key,V vaule){
        try {
            lock.lock();
            return super.put(key,vaule);
        }finally {
            lock.unlock();
        }
    }

    public V get(Object key){
        try {
            lock.lock();
            return super.get(key);
        }finally {
            lock.unlock();
        }
    }

    @Override
    public V remove(Object key) {
        try {
            lock.lock();
            return super.remove(key);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public int size() {
        try {
            lock.lock();
            return super.size();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void clear() {
        try {
            lock.lock();
            super.clear();
        } finally {
            lock.unlock();
        }
    }


    public static void main(String args[]) {
        LRUCache2 lruCache2 = new LRUCache2(3);

        lruCache2.put("1", "one"); // 1
        lruCache2.put("2", "two"); // 2 1
        lruCache2.put("3", "three"); // 3 2 1
        lruCache2.put("4", "four"); // 4 3 2
        if (lruCache2.get("2") == null)
            throw new Error(); // 2 4 3
        lruCache2.put("5", "five"); // 5 2 4
        lruCache2.put("4", "second four"); // 4 5 2


        if (!lruCache2.get("4").equals("second four"))
            throw new Error();
        if (!lruCache2.get("5").equals("five"))
            throw new Error();
        if (!lruCache2.get("2").equals("two"))
            throw new Error();
        Set<String> stringSet = lruCache2.keySet();
        //效率低
        for (String str:stringSet){
            System.out.println(lruCache2.get(str));
        }

//        for (Map.Entry<K,V> map:lruCache2.entrySet()){
//
//        }
        Iterator<Map.Entry> entryIterator = lruCache2.entrySet().iterator();
        while (entryIterator.hasNext()){
            Map.Entry map = entryIterator.next();
            System.out.println(map.getKey()+"  "+map.getValue());
        }


//        com.alibaba.dubbo.common.utils.LRUCache lruCache = new LRUCache(3);
//        lruCache.put("1", "one"); // 1
//        lruCache.put("2", "two"); // 2 1
//        lruCache.put("3", "three"); // 3 2 1
//        lruCache.put("4", "four"); // 4 3 2
//        if (lruCache.get("2") == null)
//            throw new Error(); // 2 4 3
//        lruCache.put("5", "five"); // 5 2 4
//        lruCache.put("4", "second four"); // 4 5 2
//
//
//        Set<String> stringSet2 = lruCache.keySet();
//        //效率低
//        for (String str:stringSet2){
//            System.out.println(lruCache.get(str));
//        }
    }
}
