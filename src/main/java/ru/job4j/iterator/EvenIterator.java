package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenIterator implements Iterator<Integer> {
    private final int[] data;
    private int point = 0;

    public EvenIterator(int[] data) {
        this.data = data;
    }
    public Integer newPointPosition() {
        Integer returnPosition = point;
        while (returnPosition < data.length && data[returnPosition] % 2 != 0) {
            returnPosition++;
        }
        return  returnPosition;
    }
    @Override
    public boolean hasNext() {
        return  newPointPosition() < data.length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        point = newPointPosition();
        return data[point++];
    }
}
