package br.ic.ufal.ia.utils;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class QueueUtils<Item> implements Iterable<Item> {
    private Node<Item> first;
    private Node<Item> last;
    private int N;

    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    /*  Initializes empty queue */
    public QueueUtils() {
        first = null;
        last = null;
        N = 0;
    }

    public void clear() {
        first = null;
        last = null;
        N= 0;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return N;
    }

    /* Returns the item least recently added to queue */
    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException("QueueUtils underflow");
        return first.item;
    }

    public void enqueue(Item item) {
        Node<Item> oldlast = last;
        last = new Node<Item>();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else oldlast.next = last;
        N++;
    }

    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("QueueUtils underflow");
        Item item = first.item;
        first = first.next;
        N--;
        if (isEmpty()) last = null;
        return item;
    }

    /* Returns an iterator that iterates over the items in this queue in FIFO order */
    public Iterator<Item> iterator() {
        return new ListIterator<Item>(first);
    }

    private class ListIterator<Item> implements Iterator<Item> {
        private Node<Item> current;

        public ListIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public void addQueue(QueueUtils<Item> queue) {
        if (!queue.isEmpty()) {
            Node<Item> oldFirst = first;

            if (isEmpty()) {
                first = queue.first;
                last = queue.last;
            } else {
                first = queue.first;
                queue.last.next = oldFirst;
            }
            N = N + queue.size();
        }
    }
}
