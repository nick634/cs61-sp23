import org.junit.jupiter.api.*;
import deque.Deque;
import deque.ArrayDeque;
import deque.LinkedListDeque;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class MaxArrayDequeTest {
    @Test
    public void equalsTest() {
        Deque<Integer> lla = new ArrayDeque<>();
        for (int i = 0; i < 40; i++) { //[0, 1,..., 39]
            if (i > 20) {
                lla.addLast(i);
            } else {
                lla.addFirst(i);
            }
        }
        Deque<Integer> llb = new ArrayDeque<>();
        for (int i = 0; i < 40; i++) { //[0, 1,..., 39]
            if (i > 20) {
                llb.addLast(i);
            } else {
                llb.addFirst(i);
            }
        }
        System.out.println(llb.toList());

        //lla.removeFirst();
        //lla.removeLast();
        //System.out.println(lla.toList());
        //System.out.println(lla.get(3));
        assertThat(llb).isEqualTo(lla);
    }
}
