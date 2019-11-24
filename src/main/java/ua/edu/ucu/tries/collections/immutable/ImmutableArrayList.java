package ua.edu.ucu.tries.collections.immutable;
import java.util.Arrays;

public class ImmutableArrayList implements ImmutableList {

    private Object[] arrayOfObjects;
    private int length;
    private String name;

    public ImmutableArrayList() {
        arrayOfObjects = new Object[0];
        this.length = 0;
    }

    public ImmutableArrayList(Object[] elements) {
        this.arrayOfObjects = elements.clone();
        this.length = arrayOfObjects.length;
    }

    public ImmutableArrayList add(Object e) {
        return add(length, e);
    }

    public ImmutableArrayList add(int index, Object e) {
        if((index < 0) || (index > length)){
            throw new IllegalArgumentException();
        }

        Object[] newArr = new Object[length+1];
        if(index==0){
            newArr[0] = e;
        }
        else {
            System.arraycopy(arrayOfObjects, 0, newArr, 0, index);
            newArr[index] = e;
            if (index  < length ) {

                System.arraycopy(arrayOfObjects, index , newArr, index + 1, (length - index) );
            }
        }
        return new ImmutableArrayList(newArr);

    }


    public ImmutableArrayList addAll(Object[] c) {
        return addAll(length, c);
    }


    public ImmutableArrayList addAll(int index, Object[] c) {
        if((index < 0) || (index > length)){
            throw new IllegalArgumentException();
        }
        Object[] newArr = new Object[length+c.length];
        System.arraycopy(arrayOfObjects, 0, newArr, 0, index);
        System.arraycopy(c, 0, newArr, index , c.length);
        if ((index + c.length) < (length + c.length)) {
            System.arraycopy(arrayOfObjects, index, newArr, (index + c.length) , length - index);
        }
        return new ImmutableArrayList(newArr);

    }

    public Object get(int index) {
        if ((index < 0) || (index >= length)){
            throw new IllegalArgumentException();
        }

        return arrayOfObjects[index];


    }

    public ImmutableArrayList remove(int index) {
        if ((index < 0) || (index >= length)){
            throw new IllegalArgumentException();
        }
        Object[] newArr = new Object[length - 1];
        System.arraycopy(arrayOfObjects, 0, newArr, 0, index);
        System.arraycopy(arrayOfObjects, index + 1, newArr, index, length - index - 1);
        return new ImmutableArrayList(newArr);
    }


    public ImmutableArrayList set(int index, Object e) {
        if ((index < 0) || (index >= length)){
            throw new IllegalArgumentException();
        }
        Object[] newArr = new Object[length];
        System.arraycopy(arrayOfObjects, 0, newArr, 0, index);
        newArr[index] = e;
        System.arraycopy(arrayOfObjects, index + 1, newArr, index+1, length - index -1);
        return new ImmutableArrayList(newArr);
    }

    public int indexOf(Object e) {
        for (int i = 0; i < length; i++) {
            if (arrayOfObjects[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    public int size() {
        return length;
    }

    public ImmutableArrayList clear() {
        return new ImmutableArrayList();
    }

    public boolean isEmpty() {
        return length == 0;
    }

    public Object[] toArray() {
        Object[] newArr = new Object[arrayOfObjects.length];
        System.arraycopy(arrayOfObjects, 0, newArr, 0, arrayOfObjects.length);
        return newArr;
    }
    public String toString(){
        return Arrays.toString(toArray());
    }


}