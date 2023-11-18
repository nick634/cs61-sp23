public class ArrayDeque<anyType> {
    int size = 0;
    int length = 0;
    anyType[] items;
    private int RFACTOR = 3;
    public ArrayDeque(){
        items = (anyType[]) new Object[8];
        length = 8;
    }
    public anyType[] ArrayDeque(ArrayDeque other){
        anyType[] copy = (anyType[]) new Object[other.length]; //want same length
        System.arraycopy(other.items, 0, copy, 0, other.size); //but only need to copy non-zero elements
        return copy;
    }
    public int size(){
        return this.size;
    }
    public anyType get(int index){
        if (index >= this.size){
            return null;
        }
        return this.items[index];
    }
    public float getUsage(){
        return (float) (this.size/(this.length * 1.0));
    }
    public void addFirst(anyType item){
        if (this.size + 1 > this.length){
            this.items = this.makeBigger(); //now its a bigger array
        }
        System.arraycopy(this.items, 0, this.items, 1, this.size);
        this.items[0] = item;
        this.size += 1;
    }
    private anyType[] makeBigger(){
        this.length = this.size * RFACTOR; //RFACTOR == 3
        anyType[] bigger = (anyType[]) new Object[this.length]; //so triples in size
        System.arraycopy(this.items, 0, bigger, 0,this.size); //why not shifting?
        return bigger;
    }
    public void printArrayDeque(){
        for (int i = 0; i < this.length; i++){
            System.out.print(this.items[i]);
        }
        System.out.println();
    }
    public static void main(String[] args){
        LinkedListDeque L = new LinkedListDeque<String>();
        ArrayDeque A = new ArrayDeque<String>();
        A.addFirst("hello");
        A.addFirst("there");
        A.addFirst("hello");
        A.addFirst("there");
        A.addFirst("hello");
        A.addFirst("there");
        A.addFirst("hello");
        A.addFirst("there");
        A.addFirst("Nick");
        A.printArrayDeque();
    }
}
