package LRUcache;

import java.util.HashMap;

/**
 * 最近最久未被访问算法，用于内存页面置换
 * 解决方法是采用双向链表+HashMap、
 * 双向列表连接Cache中的数据项，保证链表维持数据项从最近访问到最久访问的顺序
 * 每次数据项被查询时，将该节点移动到链表头部，没有被使用的就移动到了链表尾部
 * get(key):如果不存在key，返回-1，否则返回该值，并在原链表中删除，作为头结点
 * set(key,value):set的key值已存在，则更新value，将其删除，并作为头结点
 *                如果不存在，删除最后一个node，再将其作为头结点
 * */

public class LRU {
    private HashMap<Integer,DoubleLinkedListNode> map = new HashMap<>();
    private DoubleLinkedListNode head;
    private DoubleLinkedListNode end;
    private int capacity;
    private int len;

    public LRU(int capacity){
        this.capacity = capacity;
        len = 0;
    }

    public int get(int key){
        if(map.containsKey(key)){
            DoubleLinkedListNode latest = map.get(key);
            removeNode(latest);
            setHead(latest);
            return latest.val;
        }else{
            return -1;
        }
    }

    public void removeNode(DoubleLinkedListNode node){
        DoubleLinkedListNode cur = node;
        DoubleLinkedListNode pre = node.pre;
        DoubleLinkedListNode next = node.next;

        if(pre != null){
            pre.next = next;
        }else{
            head = next;
        }

        if(next != null){
            next.pre = pre;
        }else{
            end = pre;
        }
    }

    public void setHead(DoubleLinkedListNode node){
        node.next = head;
        node.pre = null;
        if(head != null){
            head.pre = node;
        }

        head = node;
        if(end == null){
            end = node;
        }
    }

    public void set(int key,int val){
        if(map.containsKey(key)){
            DoubleLinkedListNode oldNode = map.get(key);
            oldNode.val = val;
            removeNode(oldNode);
            setHead(oldNode);
        }else{
            DoubleLinkedListNode newNode = new DoubleLinkedListNode(key,val);
            if(len < capacity){
                setHead(newNode);
                map.put(key,newNode);
                len++;
            }else{
                map.remove(end.key);
                end = end.pre;
                if(end != null){
                    end.next = null;
                }
                setHead(newNode);
                map.put(key,newNode);
            }
        }
    }
}
