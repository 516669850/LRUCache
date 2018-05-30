package LRUcache;

public class DoubleLinkedListNode {
    public int val;
    public int key;
    public DoubleLinkedListNode pre;
    public DoubleLinkedListNode next;

    public DoubleLinkedListNode(int key,int val){
        this.key = key;
        this.val = val;
    }
}
