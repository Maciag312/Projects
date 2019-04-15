import java.util.*;

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

class TwoWayLinkedListWithHead<E> implements IList<E>{

    private class Element{
        public Element(E e) {
            this.object = e;
        }
        public Element(E e, Element next, Element prev) {
            this.object = e;
            this.next = next;
            this.prev = prev;
        }
        E object;
        Element next=null;
        Element prev=null;
    }

    Element head;
    // can be realization with the field size or without
    int size;

    private class InnerIterator implements Iterator<E>{
        Element pos;
        // TOD maybe more fields....

        public InnerIterator() {
            pos = head;
        }
        @Override
        public boolean hasNext() {
            if(pos.next == null && pos.prev == null)
                return false;
            if(pos.next!=head){
                if(pos.next.object!=null) {
                    return true;
                }

            }
            return false;
        }

        @Override
        public E next() {
            E obj = null;
            obj = pos.next.object;
            pos = pos.next;

            return obj;
        }
    }

    private class InnerListIterator implements ListIterator<E>{
        Element pos = head;
        // TOD maybe more fields....

        @Override
        public void add(E e) {
            throw new UnsupportedOperationException();

        }

        @Override
        public boolean hasNext() {
            if(pos.next == null && pos.prev == null)
                return false;
            if(pos.next!=head){
                return true;
            }
            return false;
        }

        @Override
        public boolean hasPrevious() {
            if(pos.next == null && pos.prev == null)
                return false;
            if(pos.prev!=head){
                return true;
            }
            return false;
        }

        @Override
        public E next() {
            E obj = null;
            obj = pos.next.object;
            pos = pos.next;

            return obj;
        }

        @Override
        public int nextIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
        public E previous() {
            E obj = null;
            obj = pos.prev.object;
            pos = pos.prev;

            return obj;
        }

        @Override
        public int previousIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();

        }

        @Override
        public void set(E e) {
            pos.object = e;
        }
    }

    public TwoWayLinkedListWithHead() {
        // make a head
        head = new Element(null);
        head.next = head;
        head.prev = head;
    }

    @Override
    public boolean add(E e) {
        Element curPointer = head;
        if(curPointer.next != null && curPointer.prev != null) {
            while (curPointer.next != head) {
                if (curPointer.next.object != null) {
                    curPointer = curPointer.next;
                } else {
                    break;
                }
            }
        }
        Element copied = curPointer;
        curPointer.next = new Element(e, head, copied);
        head.prev = curPointer.next;

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
        head.next = null;
        head.prev = null;
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
    public E get(int index) {
        Iterator<E> itr = iterator();
        int counter = -1;
        E obj = null;
        if(index>=size()||index<0){
            throw new NoSuchElementException();
        }
        while(itr.hasNext() && counter<index){
            obj = itr.next();
            counter++;
        }
        return obj;
    }

    @Override
    public E set(int index, E obj) {
        Element curElemnt = head;
        int counter = -1;
        while((curElemnt.next!=head)&&counter<(index-1)){
            curElemnt = curElemnt.next;
            counter++;
        }
        E copy;

        if(curElemnt.next==head) {
            curElemnt.next = new Element(obj, head, curElemnt);
            head.prev = curElemnt.next;
            copy = null;
        }else{
            copy = curElemnt.next.object;
            curElemnt.next.object = obj;
        }
        Element pointer = curElemnt;
        curElemnt = curElemnt.next;
        curElemnt.prev = pointer;
        curElemnt.object = obj;
        return copy;
    }

    @Override
    public int indexOf(E element) {
        int counter = 0;
        Iterator<E> itr = iterator();

        while(itr.hasNext()){
            if(itr.next().equals(element)){
                return counter;
            };
            counter++;

        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        if(size()==0){
            return true;
        }
        return false;
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
    public E remove(int index) {
        E obj;
        try {
            obj = get(index);
        }
        catch(NoSuchElementException e){
            throw new NoSuchElementException();
        }
        remove(obj);
        return obj;
    }

    @Override
    public boolean remove(E e) {
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
        Iterator<E> itr = iterator();
        int counter = 0;
        E obj=null;
        while(itr.hasNext()){
            counter++;
            obj = itr.next();
        }
        return counter;
    }
    public String toStringReverse() {
        ListIterator<E> iter=new InnerListIterator();
        E obj = null;
        String retStr="";

        while(iter.hasNext())
           obj = iter.next();
        if(obj instanceof Link){
            Link lob0 = (Link)obj;
            retStr += "\n"+ lob0.ref;
        }
        while(iter.hasPrevious()){
            obj = iter.previous();
            if(obj instanceof Link){
                Link lobj = (Link)obj;
                retStr +=  "\n"+lobj.ref;
            }
        }
        //TODO use reverse direction of the iterator
        return retStr;
    }

    public void add(TwoWayLinkedListWithHead<E> other) {
        if ( !head.equals(other.head) && other.head.next != null){
            Element LastactElem = head;
            LastactElem = LastactElem.prev;

            Element beginOtherElem = other.head.next;
            Element lastOtherElem = other.head.prev;

            LastactElem.next = beginOtherElem;
            beginOtherElem.prev = LastactElem;

            lastOtherElem.next = head;
            head.prev = lastOtherElem;
            other.clear();
        }
    }
}


class Link{
    public String ref;
    // in the future there will be more fields
    public Link(String ref) {
        this.ref=ref;
    }

    @Override
    public boolean equals(Object object) {
        if (ref != null && object != null && object instanceof Link) {
            Link other = (Link)object;
            if (other.ref == null) return false;
            return ref.equals(other.ref);
        }

        return false;
    }


}

class Document{
    public String name;
    public TwoWayLinkedListWithHead<Link> link;
    public Document(String name, Scanner scan) {
        this.name=name;
        link=new TwoWayLinkedListWithHead<Link>();
        load(scan);
    }
    public void load(Scanner scan) {
        while (scan.hasNext()) {
            String str = scan.next();
            if (correctLink(str)) {
                str = str.substring(5).toLowerCase();
                link.add(new Link(str));

            }
            if (str.equals("eod")) {
                break;
            }
        }
    }
    // accepted only small letters, capitalic letter, digits nad '_' (but not on the begin)
    private boolean correctLink(String str){
        if (str.length() > 5) {

            if (str.substring(0, 5).equalsIgnoreCase("link=")) {
                str = str.substring(5).toLowerCase();
                if (Character.isLetter(str.charAt(0))) {
                    return true;
                }
            }
        }
        return false;
    }
    @Override
    public String toString() {
        String str = "Document: " + name;
        if(link.size()<=0){
            return ("Document: " + name);
        }
        for(int i = 0; i< link.size(); i++){
            if((i+1)==link.size()){
                try {
                    str += "\n"+ link.get(i).ref;
                }
                catch(NullPointerException e){

                }
            }else{
                try {
                    str += "\n"+ link.get(i).ref;
                }
                catch(NullPointerException e){

                }
            }

        }
        return str;
    }

    public String toStringReverse() {
        String retStr="Document: "+name;
        return retStr+link.toStringReverse();
    }

}

public class Main {


    static Scanner scan; // for input stream


    public static void main(String[] args) {
        System.out.println("START");
        scan=new Scanner(System.in);
        Document[] doc=null;
        int currentDocNo=0;
        int maxNo=-1;
        boolean halt=false;
        while(!halt) {
            String line=scan.nextLine();
            // empty line and comment line - read next line
            if(line.length()==0 || line.charAt(0)=='#')
                continue;
            // copy line to output (it is easier to find a place of a mistake)
            System.out.println("!"+line);
            String word[]=line.split(" ");
            // go n - start with array of the length n
            if(word[0].equalsIgnoreCase("go") && word.length==2) {
                maxNo=Integer.parseInt(word[1]);
                doc = new Document[maxNo];
                continue;
            }
            //ch - change index
            if(word[0].equalsIgnoreCase("ch") && word.length==2) {
                currentDocNo=Integer.parseInt(word[1]);
                continue;
            }

            // ld documentName
            if(word[0].equalsIgnoreCase("ld") && word.length==2) {
                doc[currentDocNo]=new Document(word[1],scan);
                continue;
            }
            // ha
            if(word[0].equalsIgnoreCase("ha") && word.length==1) {
                halt=true;
                continue;
            }
            // clear
            if(word[0].equalsIgnoreCase("clear") && word.length==1) {
                doc[currentDocNo].link.clear();
                continue;
            }
            // show
            if(word[0].equalsIgnoreCase("show") && word.length==1) {
                System.out.println(doc[currentDocNo].toString());
                continue;
            }
            // reverse
            if(word[0].equalsIgnoreCase("reverse") && word.length==1) {
                System.out.println(doc[currentDocNo].toStringReverse());
                continue;
            }
            // size
            if(word[0].equalsIgnoreCase("size") && word.length==1) {
                System.out.println(doc[currentDocNo].link.size());
                continue;
            }
            // add str
            if(word[0].equalsIgnoreCase("add") && word.length==2) {
                System.out.println(doc[currentDocNo].link.add(new Link(word[1])));
                continue;
            }
            // addi index str
            if(word[0].equalsIgnoreCase("addi") && word.length==3) {
                int index=Integer.parseInt(word[1]);
                try {
                    doc[currentDocNo].link.add(index, new Link(word[2]));
                }
                catch (NoSuchElementException e) {
                    System.out.println("error");
                }
                continue;
            }
            // get index
            if(word[0].equalsIgnoreCase("get") && word.length==2) {
                int index=Integer.parseInt(word[1]);
                try {
                    Link l=doc[currentDocNo].link.get(index);
                    System.out.println(l.ref);
                }
                catch(NoSuchElementException e) {
                    System.out.println("error");
                }
                continue;
            }
            // set index str
            if(word[0].equalsIgnoreCase("set") && word.length==3) {
                int index=Integer.parseInt(word[1]);
                try {
                    Link l=doc[currentDocNo].link.set(index,new Link(word[2]));
                    System.out.println(l.ref);
                }
                catch(NoSuchElementException e) {
                    System.out.println("error");
                }

                continue;
            }
            // index str
            if(word[0].equalsIgnoreCase("index") && word.length==2) {
                int index=doc[currentDocNo].link.indexOf(new Link(word[1]));
                System.out.println(index);
                continue;
            }
            // remi index
            if(word[0].equalsIgnoreCase("remi") && word.length==2) {
                int index=Integer.parseInt(word[1]);
                try {
                    Link l=doc[currentDocNo].link.remove(index);
                    System.out.println(l.ref);
                }
                catch(NoSuchElementException e) {
                    System.out.println("error");
                }
                continue;
            }
            // rem str
            if(word[0].equalsIgnoreCase("rem") && word.length==2) {
                System.out.println(doc[currentDocNo].link.remove(new Link(word[1])));
                continue;
            }
            // addl <indexOfListArray>
            if(word[0].equalsIgnoreCase("addl") && word.length==2) {
                int number=Integer.parseInt(word[1]);
                doc[currentDocNo].link.add(doc[number].link);
                continue;
            }
            System.out.println("Wrong command");
        }
        System.out.println("END OF EXECUTION");
        scan.close();

    }

}