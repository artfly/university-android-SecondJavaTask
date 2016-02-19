package com.noveogroup.tree;

import java.io.Serializable;

/**
 * Created by arty on 19.02.16.
 */
public class Node<K extends Comparable<K>, V extends Serializable>  implements Comparable<Node<K, V>>, Serializable {
    private static final long serialVersionUID = 1;
    private V value;
    private K key;
    private Node<K, V> left;
    private Node<K, V> right;
    private Node<K, V> parent;

    public Node (K key, V value, Node<K, V> parent) {
        this.key = key;
        this.value = value;
        this.parent = parent;
    }

    public void setParent(Node<K, V> parent) {
        this.parent = parent;
    }

    public void setLeft(Node<K, V> left) {
        this.left = left;
    }

    public void setRight(Node<K, V> right) {
        this.right = right;
    }

    public Node<K, V> getLeft() {
        return left;
    }

    public Node<K, V> getRight() {
        return right;
    }

    public V getValue() {
        return value;
    }

    public K getKey() {
        return key;
    }

    public Node<K, V> getParent () {
        return parent;
    }

    @Override
    public int compareTo(Node<K, V> o) {
        return getKey().compareTo(o.getKey());
    }

    public void changeChild(Node<K, V> oldChild, Node<K, V> newChild) {
        if (left == oldChild)
            left = newChild;
        else
            right = newChild;
    }
}