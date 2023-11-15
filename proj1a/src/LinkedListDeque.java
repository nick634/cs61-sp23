public class LinkedListDeque<anyType> {
    //make a deque
    private Node sentinel;
    int size;
    public class Node{
        public anyType item;
        public Node next;
        public Node previous;
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

    public void addFirst(Node n){
        Node s = sentinel;
        n.next = s.next;
        n.previous = s;
        s.next = n;
    }
    public LinkedListDeque addLast(LinkedListDeque L, Node n){
        Node s = L.sentinel;
        n.previous = s.previous;
        n.next = s;
        s.previous = n;
        return L;
    }
    public static void main(String[] args){
        LinkedListDeque L = new LinkedListDeque<String>();
        L.sentinel.item = "69";
        LinkedListDeque.Node n1 = L.makeNode("hello");
        LinkedListDeque.Node n2 = L.makeNode("there");
        L.addFirst(n1);
        L.addFirst(n2);
        while(n1 != null){
            System.out.print(n1.item);
            n1 = n1.previous;
        }
    }
}

