package ru.job4j.generics;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArrayTest {

    @Test
    public void whenAddThenGet() {
        SimpleArray<Integer> array = new SimpleArray<>(100);
        array.add(1);
        Integer rsl = array.get(0);
        assertThat(rsl, is(1));
    }

    @Test
    public void testIterator() {
        SimpleArray<Integer> array = new SimpleArray<>(100);
        array.add(1);
        Integer rsl = array.iterator().next();
        assertThat(rsl, is(1));
    }

    @Test
    public void testIteratorHasNext() {
            SimpleArray<Integer> array = new SimpleArray<>(100);
            array.add(1);
            Integer rsl = array.iterator().next();
            assertTrue(array.iterator().hasNext());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenExpectOutBound() {
        SimpleArray<Integer> array = new SimpleArray<>(100);
        array.add(1);
        array.get(1);
    }

}