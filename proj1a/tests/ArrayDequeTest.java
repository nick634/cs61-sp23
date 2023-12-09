import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDequeTest {
    @Test
    public void removeFirstTest() {
        Deque<Integer> lla = new ArrayDeque<>();
        lla.addFirst(0);
        lla.addLast(1);
        lla.addLast(2);
        lla.removeFirst();
        assertThat(lla.toList()).containsExactly(1, 2).inOrder();

        Deque<Integer> llb = new ArrayDeque<>();
        assertThat(llb.removeFirst()).isNull();
        assertThat(llb.removeFirst()).isNull();
        llb.addFirst(0);
        llb.addLast(1);
        llb.addLast(2);
        llb.addLast(3);
        llb.addLast(4);
        llb.addLast(5);
        llb.addLast(6);
        llb.addLast(7);
        llb.removeFirst();
        assertThat(llb.toList()).containsExactly(1, 2, 3, 4, 5, 6, 7);
        llb.removeLast();
        assertThat(llb.toList()).containsExactly(1, 2, 3, 4, 5, 6);
        llb.removeFirst();
        llb.removeLast();
        llb.removeLast();
        assertThat(llb.toList()).containsExactly(2, 3, 4);
    }

    @Test
    public void makeBiggerTest() {
        Deque<Integer> lla = new ArrayDeque<>();
        for (int i = 0; i < 40; i++) {
            lla.addLast(i);
        }
        assertThat(lla.size()).isEqualTo(40);
        assertThat(lla.length()).isEqualTo(72);

        Deque<Integer> llb = new ArrayDeque<>();
        for (int i = 0; i < 4000; i++) {
            llb.addLast(i);
        }
        assertThat(llb.size()).isEqualTo(4000);
        assertThat(llb.length()).isEqualTo(5832);
    }
    @Test
    public void makeSmallerTest() {
        Deque<Integer> llc = new ArrayDeque<>();
        for (int i = 0; i < 400; i++) { //[0, 1,..., 39]
            llc.addLast(i);
        }
        int x = 0;
        while (x < 395) {
            llc.removeFirst();
            x++;
            if (llc.size() >= 8) {
                assertThat(llc.usage()).isGreaterThan(0.25F);
            }
        }
        assertThat(llc.length()).isEqualTo(20);
        assertThat(llc.toList()).containsExactly(395,396,397,398,399).inOrder();
    }
}

