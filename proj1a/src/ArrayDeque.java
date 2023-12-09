import java.util.ArrayList;
import java.util.List;

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

    public float usage(){return usage;}

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
        int newLength = length * RFACTOR;
        if (nextFirst == size){
            nextFirst = newLength - 1;
        }
        length = newLength;
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
        System.out.println(lla.get(3));
    }
}

