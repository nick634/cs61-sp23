import java.util.ArrayList;
import java.util.List;

public class ArrayDeque<T> implements Deque<T> {
    T[] items;
    int size = 0;
    private int length = 0;
    private int nextFirst = 0;
    private int nextLast = 1;

    private float usage = (float) size / length ;
    private int RFACTOR = 3; //array resizing up multiple

    public ArrayDeque(){
        items = (T[]) new Object[8];
        length = 8;
    }
    @Override
    public void addFirst(T x) {
        if (size + 1 > length){
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
    public void addLast(T x) {
        if (size + 1 > length){
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
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
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
    public T removeFirst() {
        if (isEmpty()){
            return null;
        }
        size--;
        if ((float) size/length < .25){
            items = makeSmaller();
        }
        int firstIndex = convertIndex(0);
        T item = items[firstIndex];
        items[firstIndex] = null;
        nextFirst = firstIndex;
        return item;
    }

    @Override
    public T removeLast() {
        if (isEmpty()){
            return null;
        }
        if ((float) (size - 1)/length < .25){
            items = makeSmaller();
        }
        int lastIndex = convertIndex(size - 1); //gets index of last non-null value in list
        T item = items[lastIndex];
        items[lastIndex] = null;
        nextLast = lastIndex;
        size--;
        return null;
    }

    @Override
    public T get(int index) { //should i consider the last "first" index 0?
        // nextFIrst + 1 = 0, nextFirst + 2 = 1, ...
        if (isEmpty() || index >= length){
            return null;
        }
        int trueIndex = convertIndex(index);
        return items[trueIndex];
    }
    private T[] makeBigger(){
        int firstIndex = convertIndex(0);
        int lastIndex = convertIndex(size - 1);
        length = length * RFACTOR;
        T[] bigger = (T[]) new Object[length]; //so triples in size

        System.arraycopy(items, firstIndex, bigger,0,size - firstIndex); //first element of list to end of memory
        if (firstIndex != 0){
            System.arraycopy(items, 0, bigger,size - firstIndex, lastIndex + 1); //rest of list
        }
        nextFirst = length - 1;
        nextLast = size;
        return bigger;
    }
    private T[] makeSmaller(){
        if (length >= 16){
            int newLength = length / 2;
            T[] smaller = (T[]) new Object[newLength];
            if (nextFirst == length - 1){ //annoying case when nextFirst is last memory box
                System.arraycopy(items, 0, smaller,0,size + 1);
            }
            else{
                System.arraycopy(items, nextFirst + 1, smaller,0,size + 1);
            }
            length = newLength;
            nextFirst = length - 1;
            nextLast = size;
            return smaller;
        }
        return items;
    }

    @Override
    public T getRecursive(int index) {
        return null;
    }
    public static void main(String[] args){
        Deque<Integer> lla = new ArrayDeque<>();
        for (int i = 0; i < 40; i++) { //[0, 1,..., 39]
            if (i > 20){
                lla.addLast(i);
            }
            else{
                lla.addFirst(i);
            }
        }
        System.out.println(lla.toList());
        lla.removeFirst();
        lla.removeLast();
        System.out.println(lla.toList());
        System.out.println(lla.get(3));
    }
}

