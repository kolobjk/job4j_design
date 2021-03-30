package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;

    public T deleteFirst() {
        final Node<T> first = head;
        if (first == null) {
            throw new NoSuchElementException();
        }
        final T returnElement = first.value;
        head = first.next;
        first.value = null;
        first.next = null;
        return  returnElement;
    }

    public void addFirst(T addElement) {
        if (head == null) {
            head = new Node<>(addElement, null);
            return;
        }
        head = new Node<>(addElement, head);
    }

    public void add(T value) {
        Node<T> node = new Node<T>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    public boolean revert() {
        if (head == null || head.next == null) {
            return false;
        }
        Node<T> prevElement = null;
        Node<T> curElement = head;
        Node<T> nextElement;
        while (curElement != null) {
            nextElement = curElement.next;
            curElement.next = prevElement;
            prevElement = curElement;
            curElement = nextElement;
        }
        head = prevElement;
        return true;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}