package ru.job4j.collection;

import java.util.*;

public class LinkedList<T> implements Iterable<T> {

    private static class Node<T> {
        T item;
        Node<T> next;
        Node<T> prev;

        Node(Node<T> prev, T element, Node<T> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    transient int size = 0;
    transient Node<T> first;
    transient Node<T> last;
    private int modCount = 0;

    public T get(int index) {
        Node<T> returnElement = first;
        Objects.checkIndex(index, size);
        for (int i = 0; i < index; i++) {
            returnElement = returnElement.next;
        }
        return returnElement.item;
    }

    public void add(T model) {
        modCount++;
        if (size == 0) {
            first = new Node<>(null, model, null);
            last = first;
            size++;
            return;
        }
        Node<T> addElement = new Node(last, model, null);
        last.next = addElement;
        last = addElement;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int index = 0;
            private final int expectedModCount = modCount;
            private Node<T> currentNode = first;
            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                T returnItem = currentNode.item;
                currentNode = currentNode.next;
                return returnItem;
            }
        };
    }
}
