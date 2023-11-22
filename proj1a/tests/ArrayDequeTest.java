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
    }

    public void makeSmallerTest() {
        int x = 0;
        Deque<Integer> lla = new ArrayDeque<>();
        while (x < 31) {
            lla.removeLast();
            x++;
        }
    }
}

