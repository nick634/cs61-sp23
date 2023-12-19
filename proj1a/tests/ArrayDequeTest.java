import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDequeTest {
    @Test
    public void removeFirstAndLastTest() {
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

        Deque<Integer> llc = new ArrayDeque<>();
        for (int i = 0; i <= 40; i++){
            if (i % 2 == 0){
                llc.addLast(i);
            }
            else{
                llc.addFirst(i);
            }
        }
        assertThat(llc.toList()).containsExactly(39,37,35,33,31,29,27,25,23,21,19,17,15,13,11,9,7,5,3,1,0,2,4,6,8,10,12,14,16,18,20,22,24,26,28,30,32,34,36,38,40);
        for (int j = 0; j < 35; j++){
            if (j % 2 == 0){
                llc.removeLast();
            }
            else{
                llc.removeFirst();
            }
        }
        assertThat(llc.toList()).containsExactly(5,3,1,0,2,4);
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
            llc.addLast(i); //this thing has 400 elements
        }
        assertThat(llc.length()).isEqualTo(648);
        int x = 400;
        while (x > 5) {
            llc.removeFirst();
            if (x == 162 || x == 82){ //when youre down to 161 elements then youll downsize, final one of this length is 81 elemts
                assertThat(llc.length()).isEqualTo(324);
            }
            else if (x == 81 || x == 42){
                assertThat(llc.length()).isEqualTo(162);//length should equal 162 at both ends
            }
            else if (x == 41 || x == 22){ //40 elms or 21
                assertThat(llc.length()).isEqualTo(81);
            }
            else if (x == 21 || x == 11){ //now length 40
                assertThat(llc.length()).isEqualTo(40);
            }
            else if (x == 10 || x == 6){
                assertThat(llc.length()).isEqualTo(20);
            }
            else if (x < 6){
                assertThat(llc.length()).isEqualTo(10);
            }
            x--;
            if (llc.size() >= 8) {
                assertThat(llc.usage()).isGreaterThan(0.25F);
            }
        }
        assertThat(llc.length()).isEqualTo(20);
        assertThat(llc.toList()).containsExactly(395,396,397,398,399).inOrder();

        Deque<Integer> lld = new ArrayDeque<>();
        for (int i = 0; i <= 40; i++){
            if (i % 2 == 0){
                lld.addLast(i);
            }
            else{
                lld.addFirst(i);
            }
        }
        assertThat(lld.toList()).containsExactly(39,37,35,33,31,29,27,25,23,21,19,17,15,13,11,9,7,5,3,1,0,2,4,6,8,10,12,14,16,18,20,22,24,26,28,30,32,34,36,38,40);
        for (int j = 0; j < 35; j++){
            if (j % 2 == 0){
                lld.removeLast();
            }
            else{
                lld.removeFirst();
            }
        }
        assertThat(lld.toList()).containsExactly(5,3,1,0,2,4);
        assertThat(lld.length()).isEqualTo(18);
    }

    @Test
    public void getTest(){
        Deque<Integer> lla = new ArrayDeque<>();
        for (int i = 0; i < 100; i++){
            lla.addLast(i);
        }
        for (int j = 0; j < 100; j++){
            assertThat(lla.get(j)).isEqualTo(j);
        }
        for (int k = 0; k < 80; k++){
            lla.removeFirst();
        }
        for (int l = 0; l < 20; l++){
            assertThat(lla.get(l)).isEqualTo(l+80);
        }
    }
}


