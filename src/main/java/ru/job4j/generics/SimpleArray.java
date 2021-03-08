package ru.job4j.generics;

import java.util.*;

public class SimpleArray<T> implements Iterable<T> {

    private final Object[] container;
    private int ids = 0;
    public SimpleArray(int count) {
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
        return new Iterator<>() {
            private int index = 0;
            @Override
            public boolean hasNext() {
                return index < ids;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) container[index++];
            }
        };
    }
}
