package ua.edu.ucu.tries.collections.immutable;

public class ImmutableLinkedList implements ImmutableList {

    public Node<Object> head;
    public Node<Object> tail;
    public int length;

    public ImmutableLinkedList() {
        this.head = null;
        this.tail = null;
        this.length = 0;
    }

    public ImmutableLinkedList(Node<Object> head, Node<Object> tail) {
        this.head = head;
        this.tail = tail;
    }

    public ImmutableLinkedList(Object[] elements) {
        head = new Node<>(elements[0]);
        Node<Object> curNode = head;

        for(int i = 1; i<elements.length;i++){
            curNode.setNext(new Node<>(elements[i]));
            curNode = curNode.getNext();
        }
    }
    public Node getHead(){
        return head;
    }

    @Override
    public ImmutableLinkedList add(Object e) {
        return add(size(), e);
    }


    @Override
    public ImmutableLinkedList add(int index, Object e) {
        ImmutableLinkedList newInstance = newList();
        newInstance.length =length;
        Node<Object> curNode = newInstance.head;
        if (index == 0) {
            newInstance.head = new Node<>(e);
            newInstance.head.setNext(curNode);
            newInstance.length++;

        } else {

            if (index > size()) {
                throw new IllegalArgumentException();
            }
            for (int i = 0; i < (index - 1); i++) {
                curNode = curNode.getNext();
            }
            Node<Object> previous = curNode;
            Node<Object> oldNext = previous.getNext();
            previous.setNext(new Node<>(e));


            Node<Object> a= previous.getNext();
            a.setNext(oldNext);

            newInstance.length++;
        }
        return newInstance;
    }

    @Override
    public ImmutableLinkedList addAll(Object[] c) {
        return addAll(size(),c);

    }


    @Override
    public ImmutableLinkedList addAll(int index, Object[] c) { // додає масив елементів починаючи з з
        ImmutableLinkedList newInstance = newList();

        if (newInstance.size() ==0){
            newInstance.length =c.length;
            return new ImmutableLinkedList(c);
        }
        if (index>=(newInstance.length+1)){
            throw new IllegalArgumentException();
        }
        Node curNode = newInstance.head;
        if(index==0){
            for(int i = 0;i<length;i++){
                curNode = new Node(c[i]);
                curNode = curNode.getNext();
            }
        }
        else {
            int i = 0;
            while (i != index - 1) {
                curNode = curNode.getNext();
                i++;
            }
            Node<Object> oldNext = curNode.getNext();
            int k = 0;
            while (k != c.length) {
                curNode.setNext(new Node(c[k]));
                curNode = curNode.getNext();
                k++;
            }
            Node<Object> a = curNode.getNext();
            curNode.setNext(oldNext);
            newInstance.length += c.length;
        }
        return newInstance;
    }



    public ImmutableLinkedList addFirst(Object e) {
        return add(0, e);
    }

    public ImmutableLinkedList addLast(Object e) {
        return add(e);
    }

    public Object getFirst() {
        return get(0);
    }

    public Object getLast() {
        return get(size() - 1);
    }

    public ImmutableLinkedList removeFirst() {
        return remove(0);
    }

    public ImmutableLinkedList removeLast() {
        return remove(size() - 1);
    }

    public int size() {
        int listSize = 0;
        Node<Object> current = head;
        while (current != null) {
            listSize++;
            current = current.getNext();
        }
        return listSize;
    }

    public boolean isEmpty() {
        return head == null;
    }


    public Object get(int index) {    //повертає елемент за індексом, та кидає виключну ситуацію, якщо індекс виходить за межі колекції
        int currentIndex = 0;
        Node<Object> cur = head;
        if (index <= size()) {
            while (index != currentIndex) {
                currentIndex++;
                cur = cur.getNext();
            }
            return cur.getData();
        }
        throw new IllegalArgumentException();
    }

    @Override
    public ImmutableLinkedList remove(int index) {
        ImmutableLinkedList newInst = newList();

        Node curNode = newInst.head;
        if(index==0){
            newInst.head = curNode.getNext();
            newInst.length--;
            return newInst;
        }
        for(int i = 1; i<newInst.length;i++){
            if(i==index){
                curNode.setNext(curNode.getNext().getNext());
                newInst.length--;
                return newInst;
            }
            else{
                curNode = curNode.getNext();}
        }
        throw new IllegalArgumentException();
    }

    @Override
    public ImmutableLinkedList set(int index, Object e) {
        if (index >= size()) {
            throw new IllegalArgumentException();
        }
        ImmutableLinkedList newInst = newList();
        Node currentElem = newInst.head;
        for (int i = 0; i < newInst.length; i++) {
            if (index == i) {
                currentElem.setData(e);
                break;
            }
            currentElem = currentElem.getNext();
        }
        return newInst;
    }


    public int indexOf(Object e) { //шукає індекс елемента (повертає індекс першого який знайшов, або -1 у випадку відсутності)
        int curIndex = 0;
        Node<Object> curNode = head;
        while (head != null) {
            if (curNode.getData().equals(e)) {
                return curIndex;
            } else {
                curIndex++;
                curNode = curNode.getNext();
            }
        }
        return -1;
    }

    public ImmutableLinkedList clear() {
        return new ImmutableLinkedList();
    }


    public ImmutableLinkedList newList() {

        ImmutableLinkedList newList = new ImmutableLinkedList();
        if (size() == 0) {
            return newList;
        }
        newList.length = size();
        newList.head = new Node(head.getData());
        Node curNode = head.getNext();
        Node newNode = newList.head;
        while(curNode!=null){
            newNode.setNext(new Node(curNode.getData()));
            newNode = newNode.getNext();
            curNode = curNode.getNext();
        }

        return newList;
    }

    public Object[] toArray() {
        Object[] arr = new Object[size()];
        Node<Object> curElement = head;
        int i = 0;
        while (i != size()) {
            arr[i] = curElement.getData();
            curElement = curElement.getNext();
            i++;
        }
        return arr;
    }

    public String toString() {
        String strList = "";
        Node<Object> current = head;
        while (current != null) {
            strList += current.getData() + ", ";
            current = current.getNext();
        }
        return strList;
    }

    public class Node<T> {
        public T data;
        public Node<T> next;

        public Node() {

        }

        public Node(T data) {
            this.data = data;
        }

        public Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }
    }
}