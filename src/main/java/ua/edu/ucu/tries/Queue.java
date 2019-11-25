package ua.edu.ucu.tries;
import ua.edu.ucu.tries.collections.immutable.ImmutableLinkedList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue<Item> implements Iterable<Item>{
    public ImmutableLinkedList data;
    private int n;         // number of elements on queue
    private Node first;    // beginning of queue
    private Node last;

//    public static void main(){
//        Queue
//    }

    private class Node {
        private Item item;   // the item in the node
        private Node next;   // reference to next item
    }

    public Queue(){
        this.data = new ImmutableLinkedList();
    }

    public Object peek(){
        return data.getFirst();
    }

    public Object dequeue(){
        Object element = data.getFirst();
       data =data.removeFirst();
        return element;
    }

    public  void enqueue(Object e){
        data = data.addLast(e);
    }

    public int size() {
        return data.length;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private ImmutableLinkedList.Node current = data.getHead();  // node containing current item

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = (Item) current.data;
            current = current.next;
            return item;
        }
    }


}

