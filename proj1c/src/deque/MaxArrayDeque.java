package deque;
import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T>{
    Comparator c;
    public MaxArrayDeque(Comparator<T> comp){
        c = comp; //now the maxarraydeque has a specific comparator
        items = (T[]) new Object[8];
        length = 8; //still need to create the thing, i think
    }
    public T max(){
        if (size == 0) {
            return null;
        }
        return null; //idk how to do this shit
    }
}

