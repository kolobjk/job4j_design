package ru.job4j.collection;


import java.util.Iterator;

public class SimpleSet<T> {

    private SimpleArray<T> set = new SimpleArray<>();

    public boolean add(T value) {
        if (!set.contains(value)) {
            set.add(value);
            return true;
        }
        return false;
    }

    public boolean contains(T value) {
        return set.contains(value);
    }

    public Iterator<T> iterator() {
        return set.iterator();
    }
}