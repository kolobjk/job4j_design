package ru.job4j.collection;

import java.util.*;

public class SimpleArray<T> implements Iterable<T> {

    private  Object[] container;
    private int size = 10;
    private int pointer = 0;
    private int modCount = 0;

    public SimpleArray() {
        container = new Object[size];
    }

    public int length() {
        return  pointer;
    }

    public T get(int index) {
        Objects.checkIndex(index, pointer);
        return (T) container[index];
    }

    public boolean contains(T model) {
        return Arrays.stream(container).anyMatch(it -> it == model);
    }

    public void add(T model) {
        modCount++;
        if (pointer + 1 == size) {
            size *= 2;
            container = Arrays.copyOf(container, size);
        }
        container[pointer++] = model;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int index = 0;
            private int expectedModCount = modCount;
            @Override
            public boolean hasNext() {
                return index < pointer;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return (T) container[index++];
            }
        };
    }
}
