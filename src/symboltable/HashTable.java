package symboltable;

import java.util.LinkedList;
import java.util.List;


/**
 * 
 * table using hash key, dynamic array of list
 * 
 * @author tir29
 */
public class HashTable<K extends Comparable<K>, V> {
    private static class Node<K extends Comparable<K>,V> implements Comparable<Node<K,V>> {
        K key;
        V val;

        Node(K key, V val) {
            this.key = key;
            this.val = val;
        }

        @Override
        public int compareTo(Node<K, V> o) {
            return key.compareTo(o.key);
        }
    }

    List<Node<K,V>>[] table;
    private final int N;
    private int size;

    public HashTable() {
        this(100);
    }

    public HashTable(int n) {
        table = new LinkedList[n];
        N = n;
        size = 0;
    }

    private int hashing(K key) {
        return (key.hashCode() & 0x7fffffff) % N;
    }

    public void put(K key, V val) {
        int hc = hashing(key);
        if(table[hc] == null) table[hc] = new LinkedList<>();
        table[hc].add(new Node<>(key, val));
        size++;
    }

    public V get(K key) {
        int hc = hashing(key);
        if(table[hc] == null) return null;
        return table[hc].stream()
                .filter(node -> node.key.equals(key))
                .findAny()
                .orElse(new Node<>(null, null)).val;
    }

    public static void main(String[] args) {
        HashTable<String, Integer> hashTable = new HashTable<>();

        for(int i=0; i<50; i++){
            hashTable.put("Test"+i, i);
        }

        System.out.println(hashTable.get("Test"+49));
    }


}
