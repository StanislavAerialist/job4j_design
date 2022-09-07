package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements LinkedList<E> {
    transient Node<E> last;
    transient Node<E> first;
    transient int size = 0;
    private int modCount;
    private int expectedModCount;
    public SimpleLinkedList() {
        first = new Node<E>(null, null, last);
        last = new Node<E>(first, null, null);
    }

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;
        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    @Override
    public void add(E value) {
        Node<E> l = last;
        last = new Node<>(l, value, null);
        if (l.item == null) {
            first = last;
        } else {
            l.next = last;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> rsl = first;
        for (int i = 0; i < index; i++) {
            rsl = rsl.next;
        }
        return rsl.item;
    }

    @Override
    public Iterator<E> iterator() {
        expectedModCount = modCount;
        return new Iterator<E>() {
            private Node<E> lReturned;
            private Node<E> nextNode = first;
            private int count = 0;
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return count < size;
            }
            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                lReturned = nextNode;
                nextNode = nextNode.next;
                count++;
                return lReturned.item;
            }
        };
    }
}