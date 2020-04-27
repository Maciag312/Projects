import java.util.*;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

interface IWithName{
    String getName();
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
        return (this.ref.equals(ln.ref) /*&& this.weight == ln.weight*/);
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
    public BST<Link> link;
    public Document(String name) {
        this.name=name.toLowerCase();
        link=new BST<Link>();
    }

    public Document(String name, Scanner scan) {
        this.name=name.toLowerCase();
        link=new BST<Link>();
        load(scan);
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
        String retStr="Document: "+name+"\n";
        retStr+=link.toStringInOrder();
        return retStr;
    }

    public String toStringPreOrder() {
        String retStr="Document: "+name+"\n";
        retStr+=link.toStringPreOrder();
        return retStr;
    }

    public String toStringPostOrder() {
        String retStr="Document: "+name+"\n";
        retStr+=link.toStringPostOrder();
        return retStr;
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

    @Override
    public String getName() {
        return name;
    }
}
class BST<T> {
    private class Node{
        T value;
        Node left,right,parent;
        public Node(T v) {
            value=v;
        }
        public T getValue(){
            return value;
        }
        public Node(T value, Node left, Node right, Node parent) {
            super();
            this.value = value;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }
    }
    private Node root=null;

    public BST() {
    }

    public T getElement(T elem) {
        return findNode(elem,root).value;
    }

    public T successor(T elem) {
        Node nextnode = findMin(elem);
        return nextnode==null?null:nextnode.value;
    }


    public String toStringInOrder() {
        if(size==0) return "";
        List<Link> links  = inOrder(root).stream().map(node -> (Link)node.getValue()).collect(Collectors.toList());
        String str = links.stream().map(link -> link.toString()).collect(Collectors.joining(", "));
        return str;
    }

    public String preOrder(Node node){
        String str = "";
        if(!node.equals(root)) str += ", ";
        str += elemToString(node.value);
        if(node.left!=null) str += preOrder(node.left);
        if(node.right!=null) str += preOrder(node.right);
        return str;
    }
    public List<Node> inOrder(Node node){
        ArrayList<Node> nodes = new ArrayList<>();
        if(node.left!=null) nodes.addAll(inOrder(node.left));
        nodes.add(node);
        if(node.right!=null) nodes.addAll(inOrder(node.right));
        return nodes;
    }
    public List<Node> postOrder(Node node){
        ArrayList<Node> nodes = new ArrayList<>();
        if(node.left!=null) nodes.addAll(postOrder(node.left));
        if(node.right!=null) nodes.addAll(postOrder(node.right));
        nodes.add(node);
        return nodes;
    }

    public String toStringPreOrder() {
        if(size==0) return "";
        String str = preOrder(root);
        return str;
    }
    public String elemToString(T elem){
        Link link = (Link)elem;
        return link.toString();
    }

    public String toStringPostOrder() {
        if(size==0) return "";
        List<Link> links = postOrder(root).stream().map(node -> (Link)node.getValue()).collect(Collectors.toList());
        String str = links.stream().map(link -> link.toString()).collect(Collectors.joining(", "));
        return str;
    }

    private int size = 0;
    public boolean add(T elem) {
        //if(getElement(elem)==null) return false;
        Node currentNode = root;
        if(currentNode==null){ root = new Node(elem,null,null,null);size++;return true;}
        Link curlink = (Link) elem;
        if(findNode(elem,root).value!=null) return false;
        boolean insertLeft = true;
        while(true){
            if(((Link) elem).compareTo((Link)currentNode.value)<=0)
                if(currentNode.left != null) currentNode = currentNode.left;
                else{insertLeft = true;break;}
            else{
                if(currentNode.right != null)currentNode = currentNode.right;
                else{insertLeft = false;break;}
            }
        }
        if(insertLeft)
            currentNode.left = new Node(elem,null,null,currentNode);
        else{
            currentNode.right = new Node(elem, null, null, currentNode);
        }
        size++;
        return true;
    }

    public T remove(T value) {
        T temp;
        Node found = findNode(value,root);
        Node first;
        Node second;

        if(found.value != null) {
            size = size -1;
        }
        if(found.value == null) {
            return null;
        }


        if ((found.left == null) || (found.right == null)) {
            second = found;}
        else {
            second = findNode(successor(found.value),root);}
        if (second.left != null) {
            first = second.left;}
        else { first = second.right;}
        if (first != null) {
            first.parent = second.parent;}
        if(second.parent==null)

        {
            root = first;
        }

        else if (second == second.parent.left) {
            second.parent.left = first;}
        else {second.parent.right = first;}
        if (second != found) {
            temp = second.value;
            second.value= found.value;
            found.value = temp;

        }

        return second.value;

    }
    public Node minimumKey(Node curr)
    {
        while(curr.left != null) {
            curr = curr.left;
        }
        return curr;
    }



    public Node findMin(T elem){
        Link link = (Link) elem;
        boolean next = false;
        List<Node> nodeList = inOrder(root);
        for(int i = 0; i<nodeList.size();i++){
            if(next)return nodeList.get(i);
            if(link.equals((Link)nodeList.get(i).value)) next = true;
        }
        return null;
    }
    public Node findNode(T elem, Node from){
        Link link = (Link) elem;
        if(link.equals((Link)from.value)) return from;
        if(from==null) return from;
        if(from.left!=null){
            if(link.compareTo((Link)from.value)<0)
                return findNode(elem, from.left);}
        if(from.right!=null){
            if(link.compareTo((Link)from.value)>0)
                return findNode(elem, from.right);}
        return new Node(null,null,null,null);
    }

    public void clear() {
        root = null;
        size=0;
    }

    public int size() {
        return size;
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
            // postorder
            if(word[0].equalsIgnoreCase("postorder") && word.length==1) {
                if(currentDoc!=null)
                    System.out.println(currentDoc.toStringPostOrder());
                else
                    System.out.println("no current document");
                continue;
            }
            // preorder
            if(word[0].equalsIgnoreCase("preorder") && word.length==1) {
                if(currentDoc!=null)
                    System.out.println(currentDoc.toStringPreOrder());
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
            // get str
            if(word[0].equalsIgnoreCase("get") && word.length==2) {
                if(currentDoc!=null) {
                    Link l=currentDoc.link.getElement(new Link(word[1]));
                    if(l!=null) {
                        System.out.println(l);
                    }
                    else {
                        System.out.println("error");
                    }
                }
                else
                    System.out.println("no current document");
                continue;
            }
            // successor str
            if(word[0].equalsIgnoreCase("successor") && word.length==2) {
                if(currentDoc!=null) {
                    Link l=currentDoc.link.successor(new Link(word[1]));
                    if(l!=null) {
                        System.out.println(l);
                    }
                    else {
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
                    Link l=currentDoc.link.remove(new Link(word[1]));
                    if(l!=null) {
                        System.out.println(l);
                    }
                    else {
                        System.out.println("error");
                    }
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