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

public class ArrayDeque<anyType> implements Deque<anyType> {
    anyType[] items;
    int size = 0;
    private int length = 0;
    private int nextFirst = 0;
    private int nextLast = 1;

    private float usage = (float) (size/(length * 1.0));
    private int RFACTOR = 3; //array resizing up multiple

    public ArrayDeque(){
        items = (anyType[]) new Object[8];
        length = 8;
    }
    @Override
    public void addFirst(anyType x) {
        if (size + 1 >= length){
            items = makeBigger(); //now it's a 3x bigger array
        }
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
        if (size + 1 >= length){
            items = makeBigger();
        }
        size++;
        items[nextLast] = x;
        if (nextLast + 1 >= length){
            nextLast = 0;
        }
        else {
            nextLast++;
        }
    }

    @Override
    public List<anyType> toList() {
        List<anyType> returnList = new ArrayList<>();
        for (int i = 0; i < length; i++){
            int trueIndex = convertIndex(i);
            if (items[trueIndex] != null) {
                returnList.add(i, items[trueIndex]);
            }
        }
        return returnList;
    }
    private int convertIndex(int desiredIndex){ //converts a desired index (as the user interprets) to the array's true index
        if (nextFirst + 1 + desiredIndex >= length){
            return nextFirst + 1 - (length - desiredIndex);
        }
        return nextFirst + 1 + desiredIndex;
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
    public int length() {
        return length;
    }

    @Override
    public anyType removeFirst() {
        if (isEmpty()){
            return null;
        }
        size--;
        if (usage < .25){
            items = makeSmaller();
        }
        int firstIndex = convertIndex(0);
        anyType item = items[firstIndex];
        items[firstIndex] = null;
        nextFirst = firstIndex;
        return item;
    }

    @Override
    public anyType removeLast() {
        if (isEmpty()){
            return null;
        }
        if (((float)(size/length * 1.0)) < .25){
            items = makeSmaller();
        }
        int lastIndex = convertIndex(size - 1); //gets index of last non-null value in list
        anyType item = items[lastIndex];
        items[lastIndex] = null;
        nextLast = lastIndex;
        size--;
        return null;
    }

    @Override
    public anyType get(int index) { //should i consider the last "first" index 0?
        // nextFIrst + 1 = 0, nextFirst + 2 = 1, ...
        if (isEmpty() || index >= length){
            return null;
        }
        int trueIndex = convertIndex(index);
        return items[trueIndex];
    }
    private anyType[] makeBigger(){
        length = length * RFACTOR;
        anyType[] bigger = (anyType[]) new Object[length]; //so triples in size
        System.arraycopy(items, 0, bigger, 0,size); //why not shifting?
        return bigger;
    }
    private anyType[] makeSmaller(){
        if (size >= 16){
            length = size / 2;
            anyType[] smaller = (anyType[]) new Object[length];
            System.arraycopy(items, 0, smaller,0,size);
            return smaller;
        }
        return items;
    }

    @Override
    public anyType getRecursive(int index) {
        return null;
    }
    public static void main(String[] args){
        Deque<Integer> lla = new ArrayDeque<>();
        lla.addLast(1);
        lla.addLast(2);
        lla.addLast(3);
        lla.addLast(4);
        lla.addLast(5);
        //lla.removeFirst();
        lla.removeLast();
        System.out.println(lla.toList());
        System.out.println(lla.size());
        //for (int i = 0; i < 8; i++){
        //    System.out.println(lla.get(i));
        //}
    }
}

