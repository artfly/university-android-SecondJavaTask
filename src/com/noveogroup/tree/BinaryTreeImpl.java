package com.noveogroup.tree;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Sample implementation.
 */
public class BinaryTreeImpl<K extends Comparable<K>, V extends Serializable> implements BinaryTree<K, V>, Serializable {
    private static final long serialVersionUID = 1;

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();

    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();

    }


    private Node<K, V> root;
    private Iterator<V> iteraror;

    @Override
    public void addElement(K key, V value) {
        Node node = new Node<> (key, value, null);
        if (root == null) {
            root = node;
            return;
        }
        addElement(root, node);
    }

    private void addElement(Node<K, V> root, Node<K, V> node) {
        if (root.compareTo(node) < 0) {
            if (root.getRight() != null) {
                addElement(root.getRight(), node);
            }
            root.setRight(node);
            node.setParent(root);
        }
        else if (root.compareTo(node) > 0) {
            if (root.getLeft() != null) {
                addElement(root.getLeft(), node);
            }
            root.setLeft(node);
            node.setParent(root);
        }
        else {
            root.setValue(node.getValue());
        }
    }

    @Override
    public void removeElement(K key) {
        if (root == null)
            return;
        removeElement(key, root);
    }

    private void removeElement(K key, Node<K, V> root) {
        if (root == null)
            return;
        if (root.getKey().compareTo(key) > 0) {
            removeElement(key, root.getLeft());
        }
        else if (root.getKey().compareTo(key) < 0) {
            removeElement(key, root.getRight());
        }
        else {
            if (root.getLeft() == null && root.getRight() == null) {
                this.root = null;
                return;
            }
            if (root.getRight() == null) {
                root.getParent().changeChild(root, root.getLeft());
                return;
            }
            Node<K, V> minNode = findSubtreeMin(root);
            minNode.getParent().changeChild(minNode, null);
            root.getParent().changeChild(root, minNode);
            minNode.setLeft(root.getLeft());
            if (minNode != root.getRight())
                minNode.setRight(root.getRight());
        }
    }

    private Node<K, V> findSubtreeMin(Node<K, V> root) {
        Node<K, V> minNode = null;
        root = root.getRight();
        while(root != null) {
            minNode = root;
            root = root.getLeft();
        }
        return minNode;
    }

    @Override
    public Iterator<V> getIterator() {
        iteraror = new TreeIterator<>(root);
        return iteraror;
    }

}
