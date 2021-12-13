import java.util.LinkedList;
import java.util.Queue;

public class Trie {
    private static final int R = 26;
    private Node root;

    private static class Node {
        String value;
        Node[] next = new Node[R];
    }

    public Trie(String[] strArr) {
        root = new Node();
        for(String s : strArr) {
            put(s);
        }
    }

    private int charToIdx(char c) {
        return (int)Character.toLowerCase(c) - 'a';
    }

    public void put(String str) {
        root = put(root, str, 0);
    }

    public Node put(Node root, String str, int idx) {
        if(root == null) root = new Node();
        if(idx == str.length()) {
            root.value = str;
            return root;
        }
        int cidx = charToIdx(str.charAt(idx));
        root.next[cidx] = put(root.next[charToIdx(str.charAt(idx))], str, idx+1);
        return root;
    }

    public Node get(Node root, String s, int idx) {
        if(root == null) return null;
        if(s.length() == idx) return root;

        return get(root.next[charToIdx(s.charAt(idx))], s, idx+1);
    }

    public Iterable<String> startWith(String prefix) {
        Node prefixNode = get(root, prefix, 0);
        Queue<String> q = new LinkedList<>();
        collect(prefixNode, q);
        return q;
    }

    private void collect(Node root, Queue<String> q) {
        if(root == null) return;
        if(root.value != null) q.add(root.value);

        for(int i=0; i<R; i++) {
            collect(root.next[i], q);
        }
    }

    public static void main(String[] args) {
        String[] strings = {
                "by", "sea", "sells", "shells", "shore", "the"
        };

        Trie trie = new Trie(strings);
        for(String s : trie.startWith("b")) {
            System.out.println(s);
        }
    }

}
