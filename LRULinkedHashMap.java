package LRUCache;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRULinkedHashMap<K,V> extends LinkedHashMap<K,V> {
    private int capacity;

    public LRULinkedHashMap(int capacity){
        //初始容量，装载因子（size/capacity）数量超过容量*装载因子，就会重建并扩容
        //accessOrder为true则按访问顺序排序，false则按插入顺序排序
        super(16,0.75f,true);
        this.capacity = capacity;
    }

    public boolean removeEldestEntry(Map.Entry<K,V> eldset){
        return size() > capacity;
    }
}
