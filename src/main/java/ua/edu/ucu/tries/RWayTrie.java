package ua.edu.ucu.tries;


import java.util.ArrayList;
import java.util.List;

public class RWayTrie implements Trie {

    private static int R = 256; // radix
    private Node root;// root of trie
    int size ;

    public RWayTrie(){
        size = 0;
    }

    private static class Node
    {
        private Object val;
        private Node[] next = new Node[R];
    }


    public Object get(String key)
    {
        Node x = get(root, key, 0);
        if (x == null) {
            return null;
        }
        return x.val;
    }


    private Node get(Node x, String key, int d)
    {  // Return value associated with key in the subtrie rooted at x.
        if (x == null){
            return null;
        }
        if (d == key.length()) {
            return x;
        }
        char c = key.charAt(d); // Use dth key char to identify subtrie.
        return get(x.next[c], key, d+1);
    }



    private Node put(Node x, Tuple el, int d)
    {
        if (x == null)
            x = new Node();
        if (d == el.term.length()) {
            x.val = el.weight;
            return x;
        }
        char c = el.term.charAt(d); // Use dth key char to identify subtrie.
        x.next[c] = put(x.next[c],el, d+1);
        return x;
    }

    @Override
    public void add(Tuple t) {
//        (term, weight)
        root = put(root, t, 0);
    }

    @Override
    public boolean contains(String word) {

        if (get(word).equals(null))
            return false;
        return true;
    }

    @Override
    public boolean delete(String word) {
        root = delete(root, word, 0);;
        return true;
    }

    private Node delete(Node x, String key, int d)
    {
        if (x == null) {
            return null;
        }
        if (d == key.length()){
            x.val = null;
        }
        else
        {
            char c = key.charAt(d);
            x.next[c] = delete(x.next[c], key, d+1);
        }
        if (x.val != null) return x;
        for (char c = 0; c < R; c++)
            if (x.next[c] != null) return x;
        return null;
    }

    private void collect(Node x, String pre,
                         Queue q)
    {
        if (x == null) return;
        if (x.val != null) q.enqueue(pre);
        for (char c = 0; c < R; c++)
            collect(x.next[c], pre + c, q);
    }

    @Override
    public Iterable<String> words() {
//        Ітератор по всім словам (обхід дерева в ширину)
        return wordsWithPrefix("");
    }

    @Override
    public Iterable<String> wordsWithPrefix(String s) {
        Queue<String> q = new Queue();
        collect(get(root, s, 0), s, q);
        return q;
    }

    @Override
    public int size()
    {  return size(root);  }


    private int size(Node x)
    {
        if (x == null)
            return 0;
        int cnt = 0;
        if (x.val != null)
            cnt++;
        for (char c = 0; c < R; c++)
            cnt += size(x.next[c]);
        return cnt; }


}
