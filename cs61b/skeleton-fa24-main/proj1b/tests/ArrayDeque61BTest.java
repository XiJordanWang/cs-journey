import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BTest {

    @Test
    @DisplayName("ArrayDeque61B has no fields besides backing array and primitives")
    void noNonTrivialFields() {
        List<Field> badFields = Reflection.getFields(ArrayDeque61B.class)
                .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
                .toList();

        assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
    }

    @Test
    @DisplayName("Test add first and last")
    void addFirstAndLast() {
        ArrayDeque61B<String> l = new ArrayDeque61B<>();
        assertThat(l.isEmpty()).isEqualTo(true);

        l.addFirst("b");
        l.addFirst("a");
        assertThat(l.toList()).containsExactly("a", "b").inOrder();

        l.addLast("c");
        l.addLast("d");
        assertThat(l.toList()).containsExactly("a", "b", "c", "d").inOrder();
        assertThat(l.size()).isEqualTo(4);

        l.addLast("e");
        l.addLast("f");
        l.addLast("g");
        l.addLast("h");
        l.addLast("i");
        assertThat(l.toList()).containsExactly("a", "b", "c", "d", "e", "f", "g", "h", "i").inOrder();
    }

    @Test
    /** In this test, we have three different assert statements that verify that addFirst works correctly. */
    public void addFirstTestBasic() {
        Deque61B<String> lld1 = new ArrayDeque61B<>();

        lld1.addFirst("back"); // after this call we expect: ["back"]}
        assertThat(lld1.toList()).containsExactly("back").inOrder();

        lld1.addFirst("middle"); // after this call we expect: ["middle", "back"]
        assertThat(lld1.toList()).containsExactly("middle", "back").inOrder();

        lld1.addFirst("front"); // after this call we expect: ["front", "middle", "back"]
        assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();

         /* Note: The first two assertThat statements aren't really necessary. For example, it's hard
            to imagine a bug in your code that would lead to ["front"] and ["front", "middle"] failing,
            but not ["front", "middle", "back"].
          */
    }

    @Test
    /** In this test, we use only one assertThat statement. IMO this test is just as good as addFirstTestBasic.
     *  In other words, the tedious work of adding the extra assertThat statements isn't worth it. */
    public void addLastTestBasic() {
        Deque61B<String> lld1 = new ArrayDeque61B<>();

        lld1.addLast("front"); // after this call we expect: ["front"]
        lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
        lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
        assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();
    }

    @Test
    /** This test performs interspersed addFirst and addLast calls. */
    public void addFirstAndAddLastTest() {
        Deque61B<Integer> lld1 = new ArrayDeque61B<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
        lld1.addLast(0);   // [0]
        lld1.addLast(1);   // [0, 1]
        lld1.addFirst(-1); // [-1, 0, 1]
        lld1.addLast(2);   // [-1, 0, 1, 2]
        lld1.addFirst(-2); // [-2, -1, 0, 1, 2]

        assertThat(lld1.toList()).containsExactly(-2, -1, 0, 1, 2).inOrder();
    }

    // Below, you'll write your own tests for LinkedListDeque61B.

    @Test
    public void removeFirstAndRemoveLastTest() {
        Deque61B<String> lld1 = new ArrayDeque61B<>();
        lld1.addFirst("Lily");
        lld1.addFirst("Lucy");
        lld1.addFirst("Li Lei");
        lld1.addFirst("Han Meimei");

        String get0 = lld1.get(0);
        assertThat(get0).isEqualTo("Han Meimei");
        String get1 = lld1.get(1);
        assertThat(get1).isEqualTo("Li Lei");
        String get2 = lld1.get(2);
        assertThat(get2).isEqualTo("Lucy");
        String get3 = lld1.get(3);
        assertThat(get3).isEqualTo("Lily");

        String getR1 = lld1.get(1);
        assertThat(getR1).isEqualTo("Li Lei");
        String getR2 = lld1.get(2);
        assertThat(getR2).isEqualTo("Lucy");
        String getR3 = lld1.get(3);
        assertThat(getR3).isEqualTo("Lily");

        int size = lld1.size();
        assertThat(size).isEqualTo(4);

        String first = lld1.removeFirst();
        int size1 = lld1.size();
        assertThat(first).isEqualTo("Han Meimei");
        assertThat(size1).isEqualTo(3);
        assertThat(lld1.toList()).containsExactly("Li Lei", "Lucy", "Lily").inOrder();

        String last = lld1.removeLast();
        int size2 = lld1.size();
        assertThat(last).isEqualTo("Lily");
        assertThat(size2).isEqualTo(2);
        assertThat(lld1.toList()).containsExactly("Li Lei", "Lucy").inOrder();
    }
}
