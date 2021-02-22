package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        while (column < data.length && data[column].length == 0) {
            column++;
            row = 0;
        }
        return  column < data.length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Integer returnElement = data[column][row];
        row++;
        if (row == data[column].length) {
            column++;
            row = 0;
        }
        return returnElement;
    }
}