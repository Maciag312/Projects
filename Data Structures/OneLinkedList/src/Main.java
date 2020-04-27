import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Scanner;


class Link extends Object{
    public String ref;
    public Link(String ref) {
        this.ref=ref;
    }
    // in the future there will be more fields

    public boolean equals(Link lin) { return this.ref == lin.ref;
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
    public OneWayLinkedList<Link> links;
    Document(String name, Scanner scan) {
        links = new OneWayLinkedList<Link>();
        this.name = name;
        load(scan);
    }

    public void load(Scanner scan) {
        //TODO
        while(scan.hasNext()){
            String str = scan.next();
            if(correctLink(str)){
                str = str.substring(5).toLowerCase();
                links.add(new Link(str));
            }
            if(str.equals("eod")){
                break;
            }
        }
    }
    // accepted only small letters, capitalic letter, digits nad '_' (but not on the begin)
    private static boolean correctLink(String str) {
        if(str.length()>5) {

            if(str.substring(0, 5).equalsIgnoreCase("link=")) {
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
        if(links.size()<=0){
            return ("Document: "+name);
        }
        for(int i = 0; i< links.size(); i++){
            if((i+1)==links.size()){
                str +=  "\n"+ links.get(i).ref;
            }else{
                str += "\n" + links.get(i).ref;
            }

        }
        return str;
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

    Element head = new Element(null);
    private class InnerIterator implements Iterator<E> {
        public Element curElem;

        public InnerIterator() {
            curElem = head;
            // TODO
        }
        @Override
        public boolean hasNext() {
            return !isEmpty();
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
        // make a head
        // TODO
        head = new Element(null);
        head.next = head;
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
    public boolean addOnTail(E e) {
        Element curElement = head;

        while(!curElement.next.equals(head)){
            if(curElement.next.object!=null) {
                curElement = curElement.next;
            }else{
                break;
            }
        }
        curElement.next = new Element(e);
        curElement = curElement.next;
        curElement.next = head;
        return true;
    }
    public boolean addOnHead(E e){
        while(!curElement.next.equals(head)){
            if(curElement.next.object!=null) {
                curElement = curElement.next;
            }else{
                break;
            }
        }

        Element curElement = head;
        head = new Element(e);
        head.next = curElement;

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
        Element curElemnt = head;
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
        Element elem = head.next;
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
                doc[currentDocNo].links.clear();
                continue;
            }
            // show
            if(word[0].equalsIgnoreCase("show") && word.length==1) {
                System.out.println(doc[currentDocNo].toString());
                continue;
            }
            // size
            if(word[0].equalsIgnoreCase("size") && word.length==1) {
                System.out.println(doc[currentDocNo].links.size());
                continue;
            }
            // add str
            if(word[0].equalsIgnoreCase("add") && word.length==2) {
                System.out.println(doc[currentDocNo].links.add(new Link(word[1])));
                continue;
            }
            // addi index str
            if(word[0].equalsIgnoreCase("addi") && word.length==3) {
                int index=Integer.parseInt(word[1]);
                try {
                    doc[currentDocNo].links.add(index, new Link(word[2]));
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
                    Link l=doc[currentDocNo].links.get(index);
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
                    Link l=doc[currentDocNo].links.set(index,new Link(word[2]));
                    System.out.println(l.ref);
                }
                catch(NoSuchElementException e) {
                    System.out.println("error");
                }

                continue;
            }
            // index str
            if(word[0].equalsIgnoreCase("index") && word.length==2) {
                int index=doc[currentDocNo].links.indexOf(new Link(word[1]));
                System.out.println(index);
                continue;
            }
            // remi index
            if(word[0].equalsIgnoreCase("remi") && word.length==2) {
                int index=Integer.parseInt(word[1]);
                try {
                    Link l=doc[currentDocNo].links.remove(index);
                    System.out.println(l.ref);
                }
                catch(NoSuchElementException e) {
                    System.out.println("error");
                }
                continue;
            }
            // rem str
            if(word[0].equalsIgnoreCase("rem") && word.length==2) {
                System.out.println(doc[currentDocNo].links.remove(new Link(word[1])));
                continue;
            }
            System.out.println("Wrong command");
        }
        System.out.println("END OF EXECUTION");
        scan.close();

    }




}