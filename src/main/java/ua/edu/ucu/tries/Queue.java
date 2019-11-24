package ua.edu.ucu.tries;
import ua.edu.ucu.tries.collections.immutable.ImmutableLinkedList;

public class Queue {
    private ImmutableLinkedList data;

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


}
