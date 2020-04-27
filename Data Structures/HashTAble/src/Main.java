import java.util.*;

import static java.lang.Math.abs;

interface IWithName{
    String getName();
}

interface IList<E> extends Iterable<E> {
    boolean add(E e); // add element to the list on proper position
    void add(int index, E element) throws NoSuchElementException; // not implemented
    void clear(); // delete all elements
    boolean contains(E element); // is list containing an element (equals())
    E get(int index) throws NoSuchElementException; //get element from position
    E set(int index, E element) throws NoSuchElementException; // not implemented
    int indexOf(E element); // where is element (equals())
    boolean isEmpty();
    Iterator<E> iterator();
    ListIterator<E> listIterator() throws UnsupportedOperationException; // for ListIterator
    E remove(int index) throws NoSuchElementException; // remove element from position index
    boolean remove(E e); // remove element
    int size();
}


class HashTable{
    LinkedList arr[]; // use pure array
    private final static int defaultInitSize=8;
    private final static double defaultMaxLoadFactor=0.7;
    private int size;
    private int occupited = 0;
    private final double maxLoadFactor;
    public HashTable() {
        this(defaultInitSize);
    }
    public HashTable(int size) {
        this(size,defaultMaxLoadFactor);
    }


    public HashTable(int initCapacity, double maxLF) {
        this.maxLoadFactor=maxLF; size =initCapacity;
        arr = new LinkedList[(int)(size)];
        for(int i=0; i<arr.length;i++){
            arr[i] = new LinkedList();
        }
    }

    public boolean add(Object elem) {
        if(occupited>=(int)((double)size*maxLoadFactor))
            doubleArray();
        int pos = Math.floorMod(elem.hashCode(),size);
        occupited++;
        arr[pos].add(elem);
        return true;
    }


    private void doubleArray() {
        occupited = 0;
        ArrayList<Object> arrayList = new ArrayList<>();
        for(int i= 0; i<size;i++){
            for(int j = 0; j<arr[i].size();j++)
                arrayList.add(arr[i].get(j));
        }
        size *= 2;
        arr = new LinkedList[(int)(size)];
        for(int i=0; i<arr.length;i++){
            arr[i] = new LinkedList();
        }
        while(!arrayList.isEmpty())
            add(arrayList.remove(0));

    }


    @Override
    public String toString() {
        String str = "";
        for(int i=0;i<arr.length;i++){
            boolean semi = false;
            str += (i+": ");
            for(int j = 0; j<arr[i].size();j++) {
                IWithName iwt = (IWithName) arr[i].get(j);
                if(!semi) str += iwt.getName();
                else str += (", " + iwt.getName());
                semi=true;

            }
            str += "\n";
        }
        return str;
    }

    public Object get(Object toFind) {
        Document document = (Document) toFind;
        int pos = Math.floorMod(document.hashCode(),size);
        for(int i = 0; i<arr[pos].size();i++){
            if(document.equals(arr[pos].get(i))) return arr[pos].get(i);
        }
        return null;
    }
}

class TwoWayCycledOrderedListWithSentinel<E> implements IList<E>{

    private class Element{
        public Element(E e) {
            this.object = e;
        }
        public Element(E e, Element next, Element prev) {
            this.object = e;
            this.next = next;
            this.prev = prev;
        }
        // add element e after this
        public void addAfter(Element elem) {
            elem.next=this.next;
            elem.prev=this;
            this.next.prev=elem;
            this.next=elem;
        }
        // assert it is NOT a sentinel
        public void remove() {
            this.next.prev=this.prev;
            this.prev.next=this.next;
        }
        E object;
        Element next=null;
        Element prev=null;
    }


    Element sentinel = new Element(null, null , null);


    int size;

    private class InnerIterator implements Iterator<E>{
        Element pos;

        public InnerIterator() {
            pos = sentinel;
        }
        @Override
        public boolean hasNext() {
            if(pos.next == null && pos.prev == null)
                return false;
            if(pos.next!=sentinel){
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
        Element pos = sentinel;

        public InnerListIterator() {
            //TODO
        }
        @Override
        public boolean hasNext() {
            if(pos.next == null && pos.prev == null)
                return false;
            if(pos.next!=sentinel){
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
        public void add(E arg0) {
            throw new UnsupportedOperationException();
        }
        @Override
        public boolean hasPrevious() {
            if(pos.next == null && pos.prev == null)
                return false;
            if(pos.prev!=sentinel){
                return true;
            }
            return false;        }
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
        public void set(E arg0) {
            throw new UnsupportedOperationException();
        }
    }
    public TwoWayCycledOrderedListWithSentinel() {
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    //@SuppressWarnings("unchecked")
    @Override
    public boolean add(E e){

        Element curElem=sentinel.next;

        while(curElem!=sentinel && ((Comparable<E>)curElem.object).compareTo(e)<=0) {
            curElem = curElem.next;
        }
        Element previous = curElem.prev;
        previous.addAfter(new Element(e));
        return true;
    }

    private Element getElement(int index) {

        return null;
    }

    private Element getElement(E obj) {
        Iterator<E> iit = iterator();
        E elem;
        while(iit.hasNext()){
            elem = iit.next();
            if(elem.equals(obj)){

            }
        }
        return null;
    }

    @Override
    public void add(int index, E element) {
        throw new UnsupportedOperationException();

    }

    @Override
    public void clear() {
        sentinel.next = null;
        sentinel.prev = null;
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
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
    public E set(int index, E element) {
        throw new UnsupportedOperationException();
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
        return new InnerListIterator();
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
        if(pos<0){
            return false;
        }
        Element CurElem = sentinel;
        int counter = 0;
        while(CurElem.next!=sentinel&&counter<=pos){
            CurElem = CurElem.next;
            counter ++;
        }
        CurElem.remove();
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

    //@SuppressWarnings("unchecked")
    public void add(TwoWayCycledOrderedListWithSentinel<E> other) {
        Element thisP = sentinel.next;
        Element otherP = other.sentinel.next;
        while(thisP != sentinel && otherP != other.sentinel){
            int otherCompared = ((Comparable<E>) otherP.object).compareTo(thisP.object);
            if (otherCompared >= 0){
                thisP = thisP.next;
                continue;
            }
            if (otherCompared < 0){
                otherP = otherP.next;
                thisP.prev.addAfter(otherP.prev);
                size++;
                other.size--;
            }
        }
        if (thisP == sentinel && otherP != other.sentinel){
            while (otherP != other.sentinel){
                otherP = otherP.next;
                sentinel.prev.addAfter(otherP.prev);
                size++;
                other.size--;
            }
        }
        other.clear();
    }

    //@SuppressWarnings({ "unchecked", "rawtypes" })
    public void removeAll(E e) {
        Element curElem = sentinel;
        if(e instanceof Link){
            Link oth = (Link)e;
            while(curElem.next != sentinel){
                curElem = curElem.next;
                if(curElem.object instanceof Link){
                    Link copy = (Link)curElem.object;
                    if(copy.ref.equals(oth.ref)){
                        curElem.remove();
                    }
                }
            }
        }
    }

}


class Link implements Comparable<Link>{
    public String ref;
    public int weight;
    public Link(String ref) {
        this.ref=ref;
        weight=1;
    }
    public Link(String ref, int weight) {
        this.ref=ref;
        this.weight=weight;
    }
    public boolean equals(Link ln){
        return (this.ref == ln.ref && this.weight == ln.weight);
    }
    @Override
    public boolean equals(Object obj) {
        if(ref != null && obj != null && obj instanceof Link){
            Link other = (Link)obj;
            return ref.equals(other.ref);
        }
        return false;
    }
    @Override
    public String toString() {
        return ref+"("+weight+")";
    }
    @Override
    public int compareTo(Link another) {
        int com = this.ref.compareTo(another.ref);
        return com;
    }
}

class Document implements IWithName{
    public String name;
    public TwoWayCycledOrderedListWithSentinel<Link> link;
    public Document(String name, Scanner scan) {
        this.name=name.toLowerCase();
        link=new TwoWayCycledOrderedListWithSentinel<Link>();
        load(scan);
    }

    public Document(String name) {
        this.name=name.toLowerCase();
    }
    @Override
    public boolean equals(Object obj) {
        Document document = (Document) obj;
        return document.name.equals(this.name);
    }

    @Override
    public int hashCode() {
        if(name.equals("zx")){
            int a = 0;
        }
        int[] nsequence = {7,11,13,17,19};
        int sum = 0;
        if(name.length()>=1)sum = name.charAt(0);
        int j = 0;
        for(int i = 1; i<name.length();i++){
            sum = (sum*nsequence[j] + name.charAt(i));
            j++;if(j>=nsequence.length)j=0;
        }
        return sum;
    }

    public void load(Scanner scan) {
        int found = 0;
        int found2 = 0;
        while (scan.hasNext()) {
            found = 0;
            found2 = 0;

            String str = scan.next();
            if(correctLink(str)) {
                str = str.substring(5).toLowerCase();
                String st = str;
                for(int i= 0; i<str.length(); i++){
                    if(str.charAt(i) == '('){
                        found = i;
                    }
                    if(str.charAt(i) == ')') {
                        found2 = i;
                    }
                }
                if(found!=0&&found2!=0) {
                    str = str.substring((found + 1), found2);
                    st = st.substring(0, found);
                    link.add(new Link(st, Integer.parseInt(str)));
                }else{
                    link.add(new Link(st,1));
                }
            }
            if (str.equals("eod")) {
                break;
            }
        }
    }
    private boolean correctLink(String str){
        int found = 0;
        int found2 = 0;
        if (str.length() > 5) {

            if (str.substring(0, 5).equalsIgnoreCase("link=")) {
                str = str.substring(5).toLowerCase();
                if (Character.isLetter(str.charAt(0))) {
                    for(int i= 0; i<str.length(); i++){
                        if(str.charAt(i) == '('){
                            found = i;
                        }
                        if(str.charAt(i) == ')'){
                            found2 = i;
                        }
                    }
                    if(found>0&&found2>found){
                        str = str.substring((found+1),found2);
                        if(str.matches("^[0-9]*$")){
                            if(Integer.parseInt(str)<=0){
                                return false;
                            }
                            return true;
                        }
                    }
                    if(found==0&found2==0){
                        if(Character.isLetter(str.charAt(0))){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    public static boolean isCorrectId(String id) {
        if(Character.isLetter(id.charAt(0)))
            return id.matches("[a-zA-Z0-9_]+");
        return false;
    }

    // accepted only small letters, capitalic letter, digits nad '_' (but not on the begin)
    // and eventually weight in parenthesis
    static Link createLink(String str) {
        int found = 0;
        int found2 = 0;

        str = str.toLowerCase();

        String st = str;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                found = i;
            }
            if (str.charAt(i) == ')') {
                found2 = i;
            }
        }
        if (found != 0 && found2 != 0) {
            str = str.substring((found + 1), found2);
            return new Link(st.substring(0, found), Integer.parseInt(str));
        }
        if (found == 0 && found2 == 0) {
            return new Link(st, 1);
        }
        return null;
    }

    @Override
    public String toString() {
        String str = "Document: " + name + "\n";
        if(link.size()<=0){
            return ("Document: " + name);
        }
        for(int i = 0; i< link.size(); i++){
            if((i+1)==link.size()){
                try {
                    str += link.get(i).ref + "(" + link.get(i).weight + ") ";
                }
                catch(NullPointerException e){

                }
            }else{
                try {
                    str +=  link.get(i).ref +  "(" + link.get(i).weight + ") ";
                }
                catch(NullPointerException e){

                }
            }

        }
        return str;
    }

    public String toStringReverse() {
        String retStr="Document: "+name +"\n";
        ListIterator<Link> iter=link.listIterator();
        Link obj = null;
        if(link.size()<=0){
            return ("Document: " + name);
        }
        while(iter.hasNext())
            obj = iter.next();
        retStr += obj.ref + "(" + obj.weight + ") ";

        while(iter.hasPrevious()){
            obj = iter.previous();
            retStr += obj.ref + "(" + obj.weight + ") ";
        }
        return retStr;
    }

    @Override
    public String getName() {
        return name;
    }


}


public class Main {




    static Scanner scan; // for input stream



    public static void main(String[] args) {
        System.out.println("START");
        scan=new Scanner(System.in);
        HashTable hashTable=new HashTable(8);
        Document currentDoc=null;
        boolean halt=false;
        while(!halt) {
            String line=scan.nextLine();
            // empty line and comment line - read next line
            if(line.length()==0 || line.charAt(0)=='#')
                continue;
            // copy line to output (it is easier to find a place of a mistake)
            System.out.println("!"+line);
            String word[]=line.split(" ");
            //getdoc name - change document to name
            if(word[0].equalsIgnoreCase("getdoc") && word.length==2) {
                currentDoc=(Document)hashTable.get(new Document(word[1]));
                continue;
            }

            // ld documentName
            if(word[0].equalsIgnoreCase("ld") && word.length==2) {
                if(Document.isCorrectId(word[1])) {
                    currentDoc=new Document(word[1],scan);
                    if(!hashTable.add(currentDoc))
                        System.out.println("error");
                }
                else
                    System.out.println("incorrect ID");
                continue;
            }
            // ha
            if(word[0].equalsIgnoreCase("ha") && word.length==1) {
                halt=true;
                continue;
            }
            // clear
            if(word[0].equalsIgnoreCase("clear") && word.length==1) {
                if(currentDoc!=null)
                    currentDoc.link.clear();
                else
                    System.out.println("no current document");
                continue;
            }
            // show
            if(word[0].equalsIgnoreCase("show") && word.length==1) {
                if(currentDoc!=null)
                    System.out.println(currentDoc.toString());
                else
                    System.out.println("no current document");
                continue;
            }
            // reverse
            if(word[0].equalsIgnoreCase("reverse") && word.length==1) {
                if(currentDoc!=null)
                    System.out.println(currentDoc.toStringReverse());
                else
                    System.out.println("no current document");
                continue;
            }
            // size
            if(word[0].equalsIgnoreCase("size") && word.length==1) {
                if(currentDoc!=null)
                    System.out.println(currentDoc.link.size());
                else
                    System.out.println("no current document");
                continue;
            }
            // add str
            if(word[0].equalsIgnoreCase("add") && word.length==2) {
                if(currentDoc!=null) {
                    Link link=Document.createLink(word[1]);
                    if(link==null)
                        System.out.println("error");
                    else
                        System.out.println(currentDoc.link.add(link));
                }
                else
                    System.out.println("no current document");
                continue;
            }
            // get index
            if(word[0].equalsIgnoreCase("get") && word.length==2) {
                if(currentDoc!=null) {
                    int index=Integer.parseInt(word[1]);
                    try {
                        Link l=currentDoc.link.get(index);
                        System.out.println(l.ref);
                    }
                    catch(NoSuchElementException e) {
                        System.out.println("error");
                    }
                }
                else
                    System.out.println("no current document");
                continue;
            }
            // index str
            if(word[0].equalsIgnoreCase("index") && word.length==2) {
                if(currentDoc!=null) {
                    int index=currentDoc.link.indexOf(new Link(word[1]));
                    System.out.println(index);
                }
                else
                    System.out.println("no current document");

                continue;
            }
            // remi index
            if(word[0].equalsIgnoreCase("remi") && word.length==2) {
                if(currentDoc!=null) {
                    int index=Integer.parseInt(word[1]);
                    try {
                        Link l=currentDoc.link.remove(index);
                        System.out.println(l);
                    }
                    catch(NoSuchElementException e) {
                        System.out.println("error");
                    }
                }
                else
                    System.out.println("no current document");

                continue;
            }
            // rem str
            if(word[0].equalsIgnoreCase("rem") && word.length==2) {
                if(currentDoc!=null) {
                    System.out.println(currentDoc.link.remove(new Link(word[1])));
                }
                else
                    System.out.println("no current document");
                continue;
            }
            // remall str
            if(word[0].equalsIgnoreCase("remall") && word.length==2) {
                if(currentDoc!=null) {
                    currentDoc.link.removeAll(new Link(word[1]));
                }
                else
                    System.out.println("no current document");
                continue;
            }
            // ht - show hashtable
            if(word[0].equalsIgnoreCase("ht") && word.length==1) {
                System.out.print(hashTable.toString());
                continue;
            }

            System.out.println("Wrong command");
        }
        System.out.println("END OF EXECUTION");
        scan.close();

    }




}