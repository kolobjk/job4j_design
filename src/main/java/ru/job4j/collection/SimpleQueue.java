package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int inCount = 0;
    private  int outCount = 0;

    public T poll() {
        if (inCount == 0 && outCount == 0) {
            throw new NoSuchElementException();
        }
        if (inCount != 0 && outCount == 0) {
            while (inCount > 0) {
                out.push(in.pop());
                inCount--;
                outCount++;
            }
        }
        outCount--;
        return out.pop();
    }

    public void push(T value) {
        inCount++;
        in.push(value);
    }
}