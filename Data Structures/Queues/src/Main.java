import javax.naming.LimitExceededException;
import javax.naming.SizeLimitExceededException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}

class Queues<O> {
    OneWayLinkedList<O> owll = new OneWayLinkedList<>();
    boolean add(O obj){
        return owll.add(obj);
    }
    O remove() throws NoSuchElementException{
        if(owll.isEmpty())
            throw new NoSuchElementException();
        return owll.remove(0);
    }
    O peek(){
        if(owll.isEmpty())
            return null;
        return owll.get(0);
    }
    O poll(){
        if(owll.isEmpty())
            return null;
        return this.remove();
    }
    O element(){
        return owll.get(0);
    }
}
// add to code
//structure look like
// show before and after some operations
// explain something with which technique do you use
// 4th June Test 7 PM


class ArrayFifoQueue<E> {
    private Object array[];
    public ArrayFifoQueue(final int maxsize){
        this.maxsize = maxsize;
        Object array[] = new Object[size];
    }
    private int size = 0;
    private int maxsize = 0;
    private int headpos = 0;
    private int nextpos =0;
    boolean add(E obj) throws SizeLimitExceededException {
        if(size>=maxsize) {
            throw new SizeLimitExceededException();
        }
        array[nextpos] = (E)obj;
        size++;
        nextpos++;
        if(nextpos>=size){
            nextpos = 0;
        }
        return true;
    }

    E remove() throws NoSuchElementException{
        size--;
        E get = (E)array[headpos];
        array[headpos] = null;
        headpos++;if(headpos>=size)headpos=0;
        return get;
    }
    E peek(){
        if(array[headpos] == null)
            return null;
        return (E)array[headpos];
    }
    E poll(){
        if(array[headpos] ==null)
            return null;
        E obj = (E)array[headpos];
        array[headpos] = null;
        headpos++;
        size--;
        if(headpos>size)headpos=0;
        return obj;
    }
    E element(){
        E obj = (E)array[headpos];
        array[headpos] = null;
        headpos++;
        size--;
        if(headpos>size)headpos=0;
        return obj;
    }
}

interface IList<E> extends Iterable<E> {


    boolean add(E e); // qdd element to the end of list
    void add(int index, E element) throws NoSuchElementException; // add element on position index
    void clear(); // delete all elements
    boolean contains(E element); // is list containing an element (equals())
    E get(int index) throws NoSuchElementException; //get element from position
    E set(int index, E element) throws NoSuchElementException; // set new value on position
    int indexOf(E element); // where is element (equals())
    boolean isEmpty();
    Iterator<E> iterator();
    ListIterator<E> listIterator() throws UnsupportedOperationException; // for ListIterator
    E remove(int index) throws NoSuchElementException; // remove element from position index
    boolean remove(E e); // remove element
    int size();
}


class OneWayLinkedList<E> implements IList<E>{

    private class Element{
        public Element(E e) {
            this.object=e;
        }
        E object;
        Element next=null;
    }

    Element sentinel = new Element(null);
    private class InnerIterator implements Iterator<E> {
        public Element curElem;

        public InnerIterator() {
            curElem = sentinel;
            // TODO
        }
        @Override
        public boolean hasNext() {
            if(curElem.next==null){
                return false;
            }else{
                if(curElem.next.object==null){
                    return false;
                }
            }
            return true;
        }

        @Override
        public E next() {
            if(!hasNext()){
                return null;
            }
            curElem = curElem.next;
            return curElem.object;
        }
    }


    public OneWayLinkedList() {
        // make a sentinel
        // TODO
        sentinel = new Element(null);
    }

    @Override
    public Iterator<E> iterator() {
        return new InnerIterator();
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(E e) {
        Element curElement = sentinel;

        while(curElement.next!=null){
            if(curElement.next.object!=null) {
                curElement = curElement.next;
            }else{
                break;
            }
        }
        curElement.next = new Element(e);
        return true;
    }

    @Override
    public void add(int index, E element) throws NoSuchElementException { // i dont use thorwing exeption beacuse of get function already contains it
        int siz = size();
        if(index<0 || index>siz){
            throw new NoSuchElementException();
        }

        if(siz==0){
            add(element);
        }else {
            add(get(0)); // abc -> abcc adding one element in order to find a space for implementing new element
            int pos = index;
            E ob3 = get(pos); // storing next
            E ob2 = get(pos);
            while (pos < siz) {
                ob2 = ob3;

                ob3 = get(pos + 1);
                // I introduced ob3 to store next's element. Previously it moved to next element and fatched it this is why it was a line after index repetetiving letters
                set((pos + 1), ob2); //

                pos++;
            }
            set(index, element); // filling gap which we prepared to implement 'element'
        }
    }

    @Override
    public void clear() {
        for(int i=0; i<size();i++){
            set(i,null);
        }
    }

    @Override
    public boolean contains(E element) {
        Iterator<E> iit = iterator();
        while(iit.hasNext()){
            if(element.equals(iit.next())){
                return true;
            }
        }
        return false;
    }

    @Override
    public E get(int index) throws NoSuchElementException {

        Iterator<E> iit = iterator();
        E obj=null;
        if(index<0){
            throw new NoSuchElementException();
        }
        int counter = 0;
        while(counter<=index){
            if(iit.hasNext()) {
                obj = iit.next();
                counter += 1;
            }
            else{
                throw new NoSuchElementException();
            }
        }

        return obj;
    }

    @Override
    public E set(int index, E element) throws NoSuchElementException {
        // TODO Auto-generated method stub
        if(index>=size()){
            throw new NoSuchElementException();
        }
        Element curElemnt = sentinel;
        int counter = -1;
        while(curElemnt.next!=null&&counter<(index-1)){
            curElemnt = curElemnt.next;
            counter++;
        }
        E copy;

        if(curElemnt.next==null) {
            curElemnt.next = new Element(element);
            copy = null;
        }else{
            copy = curElemnt.next.object;
            curElemnt.next.object = element;
        }
        curElemnt = curElemnt.next;
        curElemnt.object = element;
        return copy;
    }

    @Override
    public int indexOf(E value) {
        Element elem = sentinel.next;
        boolean found = false;
        int counter = 0;
        while (elem != null) {
            try {
                if (elem.object.equals(value)) {
                    found = true;
                    return counter;
                }
                counter++;
                elem = elem.next;
            }
            catch(NullPointerException e){
                return -1;
            }
        }
        if(!found){
            return -1;
        }
        return counter;
    }

    @Override
    public boolean isEmpty() {
        // TODO
        if(size()==0){
            return true;
        }
        return false;
    }

    @Override
    public E remove(int index) throws NoSuchElementException {
        // TODO Auto-generated method stub
        E obj;
        try {
            obj = get(index);
        }
        catch(IndexOutOfBoundsException e){
            throw new NoSuchElementException();
        }
        remove(obj);
        return obj;
    }

    @Override
    public boolean remove(E e) {
        // TODO Auto-generated method stub
        int pos = indexOf(e);
        if(pos<0) {
            return false;
        }
        pos++;
        int siz = size();
        for(int i = pos; i<(siz+1); i++){
            if(i==siz) {
                set(i-1, null);
            }
            else {
                set((i - 1), get(i));
            }

        }
        return true;
    }

    @Override
    public int size() {
        Iterator<E> iit = iterator();
        E obj=null;

        int counter = 0;
        while(iit.hasNext()){
            obj = iit.next();
            counter += 1;

        }

        return counter;

    }

}