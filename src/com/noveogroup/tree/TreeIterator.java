package com.noveogroup.tree;

import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by arty on 19.02.16.
 */
public class TreeIterator<K extends Comparable<K>, V extends Serializable> implements Iterator<V>, Serializable {
    private Node<K, V> next;

    TreeIterator(Node<K, V> root) {
        this.next = root;
        if (next == null)
            return;
        while(next.getLeft() != null) {
            next = next.getLeft();
        }
    }

    @Override
    public boolean hasNext() {
        return next != null;
    }

    @Override
    public V next() {
        if(!hasNext()) {
            //TODO
            throw new NoSuchElementException();
        }

        Node<K, V> ret = next;
        if(next.getRight() != null) {
            next = next.getRight();
            while(next.getLeft() != null)
                next = next.getLeft();
            return ret.getValue();
        }

        while (true) {
            if (next.getParent() == null) {
                next = null;
                return ret.getValue();
            }
            if (next.getParent().getLeft() == next) {
                next = next.getParent();
                return ret.getValue();
            }
            next = next.getParent();
        }
    }
}
