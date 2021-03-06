import java.lang.reflect.Array;
import java.util.*;



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

class TwoWayCycledOrderedListWithSentinel<E> implements IList<E>{

    private class Element{
        public Element(E e) {
            this.object=e;
            this.next=this;
            this.prev=this;
        }
        public Element(E e, Element next, Element prev) {
            this.object=e;
            this.next=next;
            this.prev=prev;
        }
        // add element e after this
        public void addAfter(Element elem) {
            elem.next=this.next;
            elem.prev=this;
            this.next.prev=elem;
            this.next=elem;
        }
        // assert it is NOT a last element in a list
        public void remove() {
            this.next.prev=this.prev;
            this.prev.next=this.next;
        }
        E object;
        Element next=null;
        Element prev=null;
    }


    Element sentinel;
    int size;

    private class InnerIterator implements Iterator<E>{
        Element p;
        int pos=0;
        public InnerIterator() {
            pos=0;
            p=sentinel.next;
        }
        @Override
        public boolean hasNext() {
            return pos<size;
        }

        @Override
        public E next() {
            E elem=p.object;
            p=p.next;
            pos++;
            return elem;
        }
    }

    private class InnerListIterator implements ListIterator<E>{
        Element p;
        int pos=0;
        public InnerListIterator() {
            pos=0;
            p=sentinel.next;
        }
        @Override
        public boolean hasNext() {
            return pos<size;
        }

        @Override
        public E next() {
            E elem=p.object;
            p=p.next;
            pos++;
            return elem;
        }
        @Override
        public void add(E arg0) {
            throw new UnsupportedOperationException();
        }
        @Override
        public boolean hasPrevious() {
            return pos>0;
        }
        @Override
        public int nextIndex() {
            throw new UnsupportedOperationException();
        }
        @Override
        public E previous() {
            pos--;
            p=p.prev;
            return p.object;
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
        sentinel=new Element(null);
        sentinel.next=sentinel;
        sentinel.prev=sentinel;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean add(E e) {
        Element p=sentinel.next;
        while(p!=sentinel && ((Comparable<E>)p.object).compareTo(e)<=0)
            p=p.next;
        p.prev.addAfter(new Element(e));
        size++;
        return true;
    }

    private Element getElement(int index) {
        checkIndex(index);
        Element p=sentinel.next;
        while(index>0) {
            index--;
            p=p.next;
        }
        return p;
    }

    private Element getElement(E obj) {
        Element p=sentinel.next;
        while(p!=sentinel) {
            if(p.object.equals(obj))
                return p;
            p=p.next;
        }
        return null;
    }

    @Override
    public void add(int index, E element) {
        throw new UnsupportedOperationException();

    }

    private void checkIndex(int index) {
        if(index<0 || index>=size) throw new NoSuchElementException();
    }

    @Override
    public void clear() {
        sentinel.next=sentinel;
        sentinel.prev=sentinel;
        size=0;
    }

    @Override
    public boolean contains(E element) {
        return indexOf(element)!=-1;
    }

    @Override
    public E get(int index) {
        return getElement(index).object;
    }

    @Override
    public E set(int index, E element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(E element) {
        Element p=sentinel.next;
        int counter=0;
        counter++;
        while (p!=sentinel) {
            if(p.object.equals(element))
                return counter;
            else {
                counter++;
                p=p.next;
            }
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return sentinel.next==sentinel;
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
        Element p=getElement(index);
        E retValue=p.object;
        p.remove();
        size--;
        return retValue;
    }

    @Override
    public boolean remove(E e) {
        Element p=getElement(e);
        if(p==null)
            return false;
        p.remove();
        size--;
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    public String toStringReverse() {
        //TODO
        ListIterator<E> iter=new InnerListIterator();
        while(iter.hasNext())
            iter.next();
        String retStr="";
        while(iter.hasPrevious())
            retStr+="\n"+iter.previous().toString();
        return retStr;
    }

    @SuppressWarnings("unchecked")
    public void add(TwoWayCycledOrderedListWithSentinel<E> other) {
        if(this==other)
            return;
        if(other.isEmpty())
            return;
        // this empty, but other not
        if(isEmpty()) {
            sentinel.next=other.sentinel.next;
            sentinel.prev=other.sentinel.prev;
            size=other.size;
            other.clear();
        }
        // both not empty
        else {
            Element p1=sentinel.next;
            Element p2=other.sentinel.next;
            while(p1!=sentinel && p2!=other.sentinel) {
                if(((Comparable<E>)p1.object).compareTo(p2.object)<=0){
                    p1=p1.next;
                }
                else {
                    p2=p2.next;
                    p1.prev.addAfter(p2.prev);
                }
            }
            while(p2!=other.sentinel) {
                p2=p2.next;
                p1.prev.addAfter(p2.prev);
            }
            size+=other.size;
            other.clear();
        }

    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void removeAll(E e) {
        Element p=getElement(e);
        if(p==null)
            return;
        while(p!=sentinel && ((Comparable)p.object).compareTo(e)==0) {
            p.remove();
            p=p.next;
            size--;
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
    @Override
    public boolean equals(Object obj) {
        return ((Link)obj).ref.equalsIgnoreCase(ref);
    }
    @Override
    public String toString() {
        return ref+"("+weight+")";
    }
    @Override
    public int compareTo(Link another) {
        return ref.compareTo(another.ref);
    }
}

class Document{
    public String name;
    public TwoWayCycledOrderedListWithSentinel<Link> link;
    public Document(String name, Scanner scan) {
        this.name=name.toLowerCase();
        link=new TwoWayCycledOrderedListWithSentinel<Link>();
        load(scan);
    }
    public void load(Scanner scan) {
        String marker="link=";
        String endMarker="eod";
        String line=scan.nextLine().toLowerCase();
        while(!line.equalsIgnoreCase(endMarker)) {
            String arr[]=line.split(" ");
            for(String word:arr) {
                if(word.startsWith(marker))
                {
                    String linkStr=word.substring(marker.length());
                    Link l;
                    if((l=createLink(linkStr))!=null)
                        link.add(l);
                }

            }
            line=scan.nextLine().toLowerCase();
        }
    }

    public static boolean isCorrectId(String id) {
        id=id.toLowerCase();
        if(id.length()==0) return false;
        if( id.charAt(0)<'a' ||id.charAt(0)>'z')
            return false;
        for(int i=1;i<id.length();i++) {
            if( !(id.charAt(i)>='a' && id.charAt(i)<='z'
                    || id.charAt(i)>='0' && id.charAt(i)<='9'
                    || id.charAt(i)=='_'))
                return false;
        }
        return true;
    }

    private static Link createIdAndNumber(String id, int n) {
        if(!isCorrectId(id)) return null;
        return new Link(id.toLowerCase(),n);
    }
    // accepted only small letters, capitalic letter, digits nad '_' (but not on the begin)
    static Link createLink(String link) {
        if(link.length()==0) return null;
        int openBracket=link.indexOf('(');
        int closeBracket=link.indexOf(')');
        if(openBracket>0 && closeBracket>openBracket && closeBracket==link.length()-1) {
            String strNumber=link.substring(openBracket+1, closeBracket);
            try {
                int number=Integer.parseInt(strNumber);
                if(number<1)
                    return null;
                return createIdAndNumber(link.substring(0, openBracket),number);
            }
            catch(NumberFormatException ex) {
                return null;
            }
        }

        return createIdAndNumber(link,1);
    }

    @Override
    public String toString() {
        String retStr="Document: "+name;
        int counter=0;
        for(Link linkElem:link) {
            if(counter%10==0)
                retStr+="\n";
            else
                retStr+=" ";
            retStr+=linkElem.toString();
            counter++;
        }
        return retStr;
    }

    public String toStringReverse() {
        String retStr="Document: "+name;
        int counter=0;
        ListIterator<Link> iter=link.listIterator();
        while(iter.hasNext())
            iter.next();
        while(iter.hasPrevious()){
            if(counter%10==0)
                retStr+="\n";
            else
                retStr+=" ";
            retStr+=iter.previous().toString();
            counter++;
        }
        return retStr;
    }

    public int[] getWeights() {
        int[] arr=new int[link.size()];
        int i=0;
        for(Link linkElem:link) {
            arr[i++]=linkElem.weight;
        }
        return arr;
    }

    public static void showArray(int[] arr) {
        if(arr.length>0) {
            System.out.print(arr[0]);
            for(int i=1;i<arr.length;i++)
                System.out.print(" "+arr[i]);
        }
        System.out.println();
    }

    void bubbleSort(int[] arr) {
        int cur = 0;
        showArray(arr);
        while(cur<arr.length){
            for(int i = arr.length-1; i>cur; i--){
                if(arr[i]<arr[i-1])
                    swapElements(arr, i-1, i);
            }
            showArray(arr);
            cur++;
        }

    }
    public void mergesort(int[] arr, int a, int q, int b){
        int from = a;
        int to = b;
        int rise = q+1;
        ArrayList<Integer> change = new ArrayList<>();
        ArrayList<Integer> change2 = new ArrayList<>();
        ArrayList<Integer> sum = new ArrayList<>();
        for(int i = from; i<=q; i++){
            change.add(Integer.valueOf(arr[i]));
        }
        for(int i = q+1; i<=b; i++){
            change2.add(Integer.valueOf(arr[i]));
        }
        while(!change.isEmpty()&&!change2.isEmpty()){

            if(change.get(0)>change2.get(0)){
                sum.add(change2.remove(0));
            }else{
                sum.add(change.remove(0));
            }
        }
        while(!change.isEmpty()){
            sum.add(change.remove(0));
        }
        while(!change2.isEmpty()){
            sum.add(change2.remove(0));
        }
        for(int i = from; i<=to; i++){
            arr[i] = sum.remove(0);
        }
    }
    public boolean check(int[] arr, int arr2[]){
        for(int i = 0; i<arr.length; i++){
            if(arr[i]!=arr2[i])
                return false;
        }
        return true;
    }
    public void iterativeMergeSort(int[] arr) {
        showArray(arr);
        int group = 1;
        int div = 2;
        int pos2 = 0;
        int q = 0;
        boolean br = false;
        int[] copy = new int[arr.length];
        while(div>=1) {
            copy = Arrays.copyOf(arr, arr.length);
            div = 0;
            group *= 2;
            int pos = 0;
            int end = arr.length - 1;
            while (pos < end) {
                pos2 = (pos + group - 1);
                if(pos2>end)
                    pos2 = end;
                q = (2*pos+group-1)/2;
                //if(pos==0&&pos2>=end){
                if(q>=pos2){
                    q = (pos2+pos)/2;
                }
                   // br = true;break;}
                mergesort(arr, pos, q,pos2);

                pos += group;
                if(pos<=end){
                    div++;
                }

            }
            ///if(br)
               // break;//if(!check(arr, copy))
            showArray(arr);
        }

    }

    void countSort(int arr[], int how)
    {
        int output[] = new int[arr.length]; // output array
        int i;
        int count[] = new int[10];

        // Store count of occurrences in count[]
        for (i = 0; i < arr.length; i++)
            count[ (arr[i]/how)%10 ]++;

        // Change count[i] so that count[i] now contains actual
        //  position of this digit in output[]
        for (i = 1; i < 10; i++)
            count[i] += count[i - 1];

        // Build the output array
        for (i = arr.length - 1; i >= 0; i--)
        {
            output[count[ (arr[i]/how)%10 ] - 1] = arr[i];
            count[ (arr[i]/how)%10 ]--;
        }

        // Copy the output array to arr[], so that arr[] now
        // contains sorted numbers according to current digit
        for (i = 0; i < arr.length; i++)
            arr[i] = output[i];
    }

    public void radixSort(int[] arr) {
        int posOfWe = 3;
        //Sort jednosci
        //Do weighta

        showArray(arr);
        ArrayList<Integer> ar = new ArrayList<>();
        for(int i = 0; i<posOfWe;i++){
            int how = 1;
            for(int a = 0; a<(i); a++){
                how*=10;
            }
            for(int a = 0; a<arr.length; a++){
                int c = arr[a] / how;
                if(c!=0){
                    ar.add(Integer.valueOf(arr[a]));
                }
            }
            countSort(arr, how);
            showArray(arr);
        }

    }

    private void swapElements(int[] arr, int a, int b) {
        int copy=arr[a];
        arr[a]=arr[b];
        arr[b]=copy;
    }
    public void insertSort(int[] arr) {

        int pos = arr.length-2;
        int end = arr.length-1;
        showArray(arr);
        for(int i = 1; i<arr.length;i++){
            int pointer = pos;
            while(pointer<end){
                if(arr[pointer]>arr[pointer+1])
                    swapElements(arr, pointer, pointer+1);
                pointer++;
            }
            showArray(arr);
            pos--;
        }
    }
    public void selectSort(int[] arr) {
        showArray(arr);
        int toSwap = 0;
        for(int i=arr.length;i>=0;i--) {
            toSwap = 0;
            for (int j = 0;j<i;j++) {
                if(arr[toSwap ] < arr[j])
                    toSwap = j;
            }
            swapElements(arr, i-1, toSwap);
            showArray(arr);
        }
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
                if(Document.isCorrectId(word[1]))
                    doc[currentDocNo]=new Document(word[1],scan);
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
            }				// size
            if(word[0].equalsIgnoreCase("size") && word.length==1) {
                System.out.println(doc[currentDocNo].link.size());
                continue;
            }
            // add str
            if(word[0].equalsIgnoreCase("add") && word.length==2) {
                Link link=Document.createLink(word[1]);
                if(link==null)
                    System.out.println("error");
                else
                    System.out.println(doc[currentDocNo].link.add(link));
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
                    System.out.println(l);
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
            // remall str
            if(word[0].equalsIgnoreCase("remall") && word.length==2) {
                doc[currentDocNo].link.removeAll(new Link(word[1]));
                continue;
            }
            // addl <indexOfListArray>
            if(word[0].equalsIgnoreCase("addl") && word.length==2) {
                int number=Integer.parseInt(word[1]);
                doc[currentDocNo].link.add(doc[number].link);
                continue;
            }

            if(word[0].equalsIgnoreCase("bubblesort") && word.length==1) {
                doc[currentDocNo].bubbleSort(doc[currentDocNo].getWeights());
                continue;
            }

            if(word[0].equalsIgnoreCase("insertsort") && word.length==1) {
                doc[currentDocNo].insertSort(doc[currentDocNo].getWeights());
                continue;
            }

            if(word[0].equalsIgnoreCase("selectsort") && word.length==1) {
                doc[currentDocNo].selectSort(doc[currentDocNo].getWeights());
                continue;
            }
            if(word[0].equalsIgnoreCase("mergesort") && word.length==1) {
                doc[currentDocNo].iterativeMergeSort(doc[currentDocNo].getWeights());
                continue;
            }
            if(word[0].equalsIgnoreCase("radixsort") && word.length==1) {
                doc[currentDocNo].radixSort(doc[currentDocNo].getWeights());
                continue;
            }
            System.out.println("Wrong command");
        }
        System.out.println("END OF EXECUTION");
        scan.close();

    }




}