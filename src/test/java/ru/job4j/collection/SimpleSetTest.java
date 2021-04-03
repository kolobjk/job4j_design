package ru.job4j.collection;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class SimpleSetTest {

    @Test
    public void whenAddNonNull() {
        SimpleSet<Integer> set = new SimpleSet<>();
        assertTrue(set.add(1));
        assertTrue(set.contains(1));
        assertFalse(set.add(1));
    }

    @Test
    public void testIterator() {
        SimpleSet<Integer> set = new SimpleSet<>();
        Integer add1 = 1;
        Integer add2 = 2;
        set.add(add1);
        set.add(add2);
        Iterator<Integer> it = set.iterator();
        assertEquals(it.next(), add1);
        assertEquals(it.next(), add2);
    }

    @Test
    public void whenAddNull() {
        SimpleSet<Integer> set = new SimpleSet<>();
        assertTrue(set.add(null));
        assertTrue(set.contains(null));
        assertFalse(set.add(null));
    }

}