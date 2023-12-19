import java.util.ArrayList;
import java.util.List;

public class LinkedListDeque<T> implements Deque<T> {
    //make a deque
    private Node sentinel;
    int size;
    public class Node{
        public T item;
        public Node next;
        public Node previous;

        private T nullify(){
            this.next = null;
            this.previous = null;
            return this.item;
        }
    }
    public LinkedListDeque(){
        size = 0;
        sentinel = new Node();
        sentinel.next = sentinel;
        sentinel.previous = sentinel;
    }
    public LinkedListDeque LinkedListDeque(LinkedListDeque other){
        LinkedListDeque L = new LinkedListDeque<T>();
        if (other.size() == 0){
            return L;
        }
        Node n = other.sentinel.next;
        while (n != other.sentinel){
            Node copy = n;
            L.addLast(n.item);
            n = n.next;
        }
        return L;
    }
    public Node makeNode(T i){
        Node n = new Node();
        n.item = i;
        return n;
    }

    public void addFirst(T item){
        Node n = this.makeNode(item);
        Node s = this.sentinel;
        if (this.size == 0){ //dont love this...how else to make sentinel loop with first node?
            s.previous = n;
        }
        n.next = s.next;
        n.previous = s;
        s.next.previous = n;
        s.next = n;
        size++;
    }
    public void addLast(T item){
        Node n = this.makeNode(item);
        Node s = this.sentinel;

        if (this.size == 0){
            s.next = n;
        }
        n.previous = s.previous;
        n.next = s;
        s.previous.next = n;
        s.previous = n;
        size++; //probably a way to make this all more elegant
    }

    public boolean isEmpty(){
        return this.size == 0;
    }
    public int size(){
        return this.size;
    }
    public void printDeque(){
        Node n = this.sentinel.next;
        while (n != this.sentinel){
            System.out.print(n.item + " ");
            n = n.next;
        }
        System.out.println();
    }

    public T removeFirst(){
        if (this.isEmpty()){
           return null;
        }
        this.size -= 1;
        Node n = this.sentinel.next;
        this.sentinel.next = n.next;
        n.next.previous = sentinel;
        T item = n.nullify();
        return item; //should I null this or no? will garbage collector dispose of it?
    }
    public T removeLast(){
        if (this.isEmpty()){
            return null;
        }
        this.size -= 1;
        Node n = this.sentinel.previous;
        n.previous.next = sentinel;
        sentinel.previous = n.previous;
        T item = n.nullify();
        return item;
    }
    public T get(int index){
        if (this.isEmpty() || index >= this.size){
            return null;
        }
        Node n = sentinel.next;
        while (index > 0){
            n = n.next;
            index--;
        }
        return n.item;
    }
    public T getRecursive(int index){
        if (this.size == 0 || index > this.size){
            return null;
        }
        if (index == 0){
            return this.sentinel.next.item;
        }
        LinkedListDeque L = this.LinkedListDeque(this); //deep copy
        L.removeFirst();
        return (T) L.getRecursive(index - 1);
    }

    @Override
    public int length() {
        return 0;
    }

    @Override
    public float usage() {
        return 0;
    }


    public List<T> toList(){
        ArrayList<T> returnList = new ArrayList<T>();
        Node n = this.sentinel.next;
        while (n != this.sentinel){
            returnList.add(n.item);
            n = n.next;
        }
        return returnList;
    }

    public static void main(String[] args){
        Deque<Integer> lld = new LinkedListDeque<>();
        LinkedListDeque L = new LinkedListDeque<String>();
        L.sentinel.item = "69";
        //LinkedListDeque.Node n1 = L.makeNode("hello");
        //LinkedListDeque.Node n2 = L.makeNode("there");
        L.addLast("hello");
        L.addLast("there");
        L.addLast("man");
        LinkedListDeque L2 = L.LinkedListDeque(L);
        L.printDeque();
        L2.printDeque();
        System.out.println("space");
        L2.removeFirst();
        L2.addFirst("hi");
        L.printDeque();
        L2.printDeque();
        List<String> L3 = L2.toList();
        System.out.println(L3);
        System.out.println("space");
        System.out.println(L2.getRecursive(0));
    }
}

