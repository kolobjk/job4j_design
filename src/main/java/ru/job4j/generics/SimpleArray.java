package ru.job4j.generics;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {

    private final Object[] container;
    private int ids = 0;
    private int size = 0;
    public SimpleArray(int count) {
        size = count;
        container = new Object[count];
    }

    public void add(T model) {
        container[ids++] = model;
    }

    public void delete(int index) {
        Objects.checkIndex(index, ids);
        System.arraycopy(container, index + 1, container, index, ids - index);
        container[ids - 1] = null;
        ids--;
    }

    public void set(int index, T model) {
        Objects.checkIndex(index, ids);
        container[index] = model;
    }

    public T get(int index) {
        Objects.checkIndex(index, ids);
        return (T) container[index];
    }

    @Override
    public Iterator<T> iterator() {
        return (Iterator<T>) Arrays.stream(container).iterator();
    }
}
