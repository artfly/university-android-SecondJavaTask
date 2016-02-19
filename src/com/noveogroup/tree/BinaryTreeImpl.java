package com.noveogroup.tree;

import com.noveogroup.exception.ancestor.ElementAlreadyExistsException;
import com.noveogroup.exception.ancestor.NoSuchNodeException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;

public class BinaryTreeImpl<K extends Comparable<K>, V extends Serializable> implements BinaryTree<K, V> {
    private static final long serialVersionUID = 1;
    private Node<K, V> root;

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        Iterator<V> iterator = getIterator();
        int counter = 0;
        while (iterator.hasNext()) {
            counter++;
            iterator.next();
        }
        out.writeInt(counter);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        int counter = in.readInt();
        System.out.println("Successfully deserialized tree with " + counter + " nodes.");
    }

    @Override
    public void addElement(K key, V value) throws ElementAlreadyExistsException {
        Node node = new Node<> (key, value, null);
        if (root == null) {
            root = node;
            return;
        }
        addElement(root, node);
    }

    private void addElement(Node<K, V> root, Node<K, V> node) throws ElementAlreadyExistsException {
        if (root.compareTo(node) < 0) {
            if (root.getRight() != null) {
                addElement(root.getRight(), node);
                return;
            }
            root.setRight(node);
            node.setParent(root);
        }
        else if (root.compareTo(node) > 0) {
            if (root.getLeft() != null) {
                addElement(root.getLeft(), node);
                return;
            }
            root.setLeft(node);
            node.setParent(root);
        }
        else {
            throw new ElementAlreadyExistsException(String.format("Element with key \"%s\" already exists", node.getKey().toString()));
        }
    }

    @Override
    public void removeElement(K key) throws NoSuchNodeException {
        removeElement(key, root);
    }

    private void removeElement(K key, Node<K, V> root) throws NoSuchNodeException {
        if (root == null)
            throw new NoSuchNodeException(String.format("Node with key \"%s\" doesn't exists", key.toString()));
        if (root.getKey().compareTo(key) > 0) {
            removeElement(key, root.getLeft());
        }
        else if (root.getKey().compareTo(key) < 0) {
            removeElement(key, root.getRight());
        }
        else {
            if (root.getLeft() == null && root.getRight() == null) {
                if (root.getParent() != null)
                    root.getParent().changeChild(root, null);
                else
                    this.root = null;
                return;
            }
            else if (root.getRight() == null) {
                if (root.getParent() != null) {
                    root.getParent().changeChild(root, root.getLeft());
                }
                else
                    this.root = root.getLeft();
                root.getLeft().setParent(root.getParent());
                return;
            }

            Node<K, V> minNode = findSubtreeMin(root);
            System.out.println("Min parent : " + minNode.getParent().getValue().toString());
            System.out.println("Min  : " + minNode.getValue().toString());
            if (root.getParent() != null)
                root.getParent().changeChild(root, minNode);
            else
                this.root = minNode;
            minNode.getParent().changeChild(minNode, null);
            minNode.setParent(root.getParent());
            minNode.setLeft(root.getLeft());
            if (root.getLeft() != null)
                root.getLeft().setParent(minNode);
            if (minNode != root.getRight()) {
                Node<K, V> connectionNode = minNode;
                while (connectionNode.getRight() != null)
                    connectionNode = connectionNode.getRight();
                connectionNode.setRight(root.getRight());
                root.getRight().setParent(connectionNode);
            }
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
        return new TreeIterator<>(root);
    }

}
