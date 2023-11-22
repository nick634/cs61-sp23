import java.util.ArrayList;
import java.util.List;

/**public class ArrayDeque<anyType> implements Deque<anyType> {
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
    public boolean isEmpty(){
        if (this.size == 0){
            return true;
        }
        return false;
    }
    public anyType get(int index){
        if (index >= this.size){
            return null;
        }
        return this.items[index];
    }

    private float getUsage(){
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
    public void addLast(anyType item){
        if (this.size + 1 > this.length){
            this.items = this.makeBigger(); //now its a bigger array
        }
        this.items[this.size] = item;
        this.size += 1;
    }


    public anyType removeFirst(){
        anyType item = this.items[0];
        if (!this.isEmpty()){
            this.size -= 1;
            float usageRatio = this.getUsage();
            if (usageRatio < .25)
                this.items = this.makeSmaller();
        }
        this.items[0] = null;
        anyType[] copy = (anyType[]) new Object[this.length];
        System.arraycopy(this.items, 1, copy, 0, this.size);
        this.items = copy;
        return item;
    }

    public anyType removeLast(){
        anyType item = this.items[size];
        if (!this.isEmpty()){
            this.size -= 1;
            float usageRatio = this.getUsage();
            if (usageRatio < .25){
                this.items = this.makeSmaller();
            }
            this.items[size] = null;
            return item;
        }
    }
    private anyType[] makeBigger(){
        this.length = this.size * RFACTOR; //RFACTOR == 3
        anyType[] bigger = (anyType[]) new Object[this.length]; //so triples in size
        System.arraycopy(this.items, 0, bigger, 0,this.size); //why not shifting?
        return bigger;
    }
    private anyType[] makeSmaller(){
        if (this.size > 16){
            this.length = this.size / 2;
            anyType[] smaller = (anyType[]) new Object[this.length];
            System.arraycopy(this.items, 0, smaller,0,this.size);
            return smaller;
        }
        return this.items;
    }
    public void printArrayDeque(){
        for (int i = 0; i < this.length; i++){
            System.out.print(this.items[i]);
        }
        System.out.println();
    }
    public static void main(String[] args){
        Deque<Integer> ad = new ArrayDeque<>();
        LinkedListDeque L = new LinkedListDeque<String>();
        ArrayDeque A = new ArrayDeque<String>();
        System.out.println(A.isEmpty());
        A.addLast("hello");
        A.addLast("there");
        A.addLast("hello");
        A.addLast("there");
        A.addLast("hello");
        A.addLast("there");
        A.addLast("hello");
        A.addLast("there");
        A.addLast("Nick");
        A.removeFirst();
        //A.removeLast();
        A.printArrayDeque();
        System.out.println(A.isEmpty());
    }
}
 */
import java.util.List;

public class ArrayDeque<anyType> implements Deque<anyType> {
    anyType[] items;
    int size = 0;
    int length = 0;
    int nextFirst = 0;
    int nextLast = 1; //not sure...bt dont think can be same as nextFirst

    public ArrayDeque(){
        items = (anyType[]) new Object[8];
        length = 8;
    }
    @Override
    public void addFirst(anyType x) {
        size++;
        items[nextFirst] = x;
        if (nextFirst == 0){
            nextFirst = length - 1;
        }
        else {
            nextFirst--;
        }
    }

    @Override
    public void addLast(anyType x) {
        size++;
        items[nextLast] = x;
        if (!hasNext(nextLast)){
            nextLast = 0;
        }
        else {
            nextLast++;
        }
    }

    @Override
    public List<anyType> toList() {
        List<anyType> returnList = new ArrayList<>();
        int i = nextFirst + 1; //current first
        while (i != nextFirst){
            returnList.add(items[i]);
            if (hasNext(i)){
                i++;
            }
            else {
                i = 0;
            }
        }
        returnList.add(items[nextFirst]); //sloppy, gotta manually add last element to allow for loop
        return returnList;
    }
    private boolean hasNext(int i){
        if (i + 1 < length){
            return true;
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0){
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public anyType removeFirst() {
        return null;
    }

    @Override
    public anyType removeLast() {
        return null;
    }

    @Override
    public anyType get(int index) { //should i consider the last "first" index 0?
        // nextFIrst + 1 = 0, nextFirst + 2 = 1, ...
        if (isEmpty() || index > length){
            return null;
        }
        int firstIndex = nextFirst + 1;
        if (firstIndex + index < length){
            return items[firstIndex + index];
        }
        return items[firstIndex - (length - index)];
    }

    @Override
    public anyType getRecursive(int index) {
        return null;
    }
    public static void main(String[] args){
        Deque<Integer> lla = new ArrayDeque<>();
        lla.addLast(1);
        lla.addFirst(0);
        lla.addLast(2);
        lla.addFirst(7);
        lla.addFirst(6);
        lla.addFirst(5);
        lla.addLast(3);
        lla.addFirst(4);
        System.out.println(lla.toList());
        System.out.println(lla.get(2));
    }
}

