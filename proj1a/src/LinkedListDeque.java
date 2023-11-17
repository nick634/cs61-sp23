public class LinkedListDeque<anyType> {
    //make a deque
    private Node sentinel;
    int size;
    public class Node{
        public anyType item;
        public Node next;
        public Node previous;

        public anyType nullify(){
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
    public Node makeNode(anyType i){
        Node n = new Node();
        n.item = i;
        return n;
    }

    public void addFirst(anyType item){
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
    public void addLast(anyType item){
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

    public anyType removeFirst(){
        if (this.isEmpty()){
           return null;
        }
        Node n = this.sentinel.next;
        this.sentinel.next = n.next;
        n.next.previous = sentinel;
        anyType item = n.nullify();
        return item; //should I null this or no? will garbage collector dispose of it?
    }
    public anyType removeLast(){
        if (this.isEmpty()){
            return null;
        }
        Node n = this.sentinel.previous;
        n.previous.next = sentinel;
        sentinel.previous = n.previous;
        anyType item = n.nullify();
        return item;
    }
    public anyType get(int index){
        if (this.isEmpty() || index >= this.size){
            return null;
        }
        Node n = sentinel.next;
        int s = this.size;
        int i = 0;
        while (i < s){
            if (i == index){
                break;
            }
            n = n.next;
            i++;
        }
        return n.item;
    }

    public static void main(String[] args){
        LinkedListDeque L = new LinkedListDeque<String>();
        L.sentinel.item = "69";
        //LinkedListDeque.Node n1 = L.makeNode("hello");
        //LinkedListDeque.Node n2 = L.makeNode("there");
        L.addLast("hello");
        L.addLast("there");
        L.addLast("man");
        L.printDeque();
        System.out.println(L.get(2));
    }
}

