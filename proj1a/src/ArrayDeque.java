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
    public void removeFirst(){
        if (!this.isEmpty()){
            this.size -=1;
            float usageRatio = this.getUsage();
            if (usageRatio < .25)
                this.items = this.makeSmaller();
        }
        this.items[0] = null;
        anyType[] copy = (anyType[]) new Object[this.length];
        System.arraycopy(this.items, 1, copy, 0, this.size);
        this.items = copy;
    }
    public void removeLast(){
        if (!this.isEmpty()){
            this.size -= 1;
            float usageRatio = this.getUsage();
            if (usageRatio < .25){
                this.items = this.makeSmaller();
            }
            this.items[size] = null;
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
