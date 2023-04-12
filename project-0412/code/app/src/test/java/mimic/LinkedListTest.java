package mimic;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest {

    @Test
    void objectIsNullAndTheItemOfFirstOneInListIsNull() {
        List<String> list = new LinkedList<>();
        list.add(null);
        list.add("a");
        list.add("b");
        list.add("c");
        boolean flag = list.remove(null);
        assertEquals(3,list.size());
        assertTrue(flag);
    }

    @Test
    void objectIsNullAndListIsNull(){
        List<String> list = new LinkedList<>();
        boolean flag = list.remove(null);
        assertEquals(0,list.size());
        assertFalse(flag);
    }

    @Test
    void objectIsNullAndNotFoundInList(){
        List<String> list = new LinkedList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        boolean flag = list.remove(null);
        assertEquals(3,list.size());
        assertFalse(flag);
    }

    @Test
    void objectIsNotNullAndTheItemOfFirstOneInListIsExpected(){
        List<String> list = new LinkedList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        boolean flag = list.remove("a");
        assertEquals(2,list.size());
        assertTrue(flag);
    }

    @Test
    void objectIsNotNullAndListIsNull(){
        List<String> list = new LinkedList<>();
        boolean flag = list.remove("a");
        assertEquals(0,list.size());
        assertFalse(flag);
    }

    @Test
    void objectIsNotNullAndNotFoundInList(){
        List<String> list = new LinkedList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        boolean flag = list.remove("d");
        assertEquals(3,list.size());
        assertFalse(flag);
    }

}