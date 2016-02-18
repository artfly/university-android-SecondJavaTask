package com.noveogroup.tree;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Sample implementation.
 */
public class BinaryTreeImpl<K extends Comparable<K>, V> implements BinaryTree<K, V> {

    private class Node<K extends Comparable<K>, V>  implements Comparable<Node<K, V>> {
        private V value;
        private K key;
        private Node<K, V> left;
        private Node<K, V> right;
        private Node<K, V> parent;

        private Node () {}

        public Node (K key, V value, Node<K, V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }

        public V getValue() {
            return value;
        }

        @Override
        public int compareTo(Node<K, V> o) {
            return getKey().compareTo(o.getKey());
        }

        public K getKey() {
            return key;
        }

        public Node<K, V> getParent () {
            return parent;
        }

        public void changeChild(Node<K, V> oldChild, Node<K, V> newChild) {
            if (left == oldChild)
                left = newChild;
            else
                right = newChild;
        }
    }

    private class TreeIterator<K extends Comparable<K>, V> implements Iterator<V> {
        private Node<K, V> next;

        TreeIterator(Node<K, V> root) {
            this.next = root;
            if (next == null)
                return;
            while(next.left != null) {
                next = next.left;
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
            if(next.right != null) {
                next = next.right;
                while(next.left != null)
                    next = next.left;
                return ret.getValue();
            }

            while (true) {
                if (next.parent == null) {
                    next = null;
                    return ret.getValue();
                }
                if (next.parent.left == next) {
                    next = next.parent;
                    return ret.getValue();
                }
                next = next.parent;
            }
        }
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
            if (root.right != null) {
                addElement(root.right, node);
            }
            root.right = node;
            node.parent = root;
        }
        else if (root.compareTo(node) > 0) {
            if (root.left != null) {
                addElement(root.left, node);
            }
            root.left = node;
            node.parent = root;
        }
        else {
            root.value = node.value;
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
            removeElement(key, root.left);
        }
        else if (root.getKey().compareTo(key) < 0) {
            removeElement(key, root.right);
        }
        else {
            if (root.left == null && root.right == null) {
                this.root = null;
                return;
            }
            if (root.right == null) {
                root.parent.changeChild(root, root.left);
                return;
            }
            Node<K, V> minNode = findSubtreeMin(root);
            minNode.parent.changeChild(minNode, null);
            root.parent.changeChild(root, minNode);
            minNode.left = root.left;
            if (minNode != root.right)
                minNode.right = root.right;
        }
    }

    private Node<K, V> findSubtreeMin(Node<K, V> root) {
        Node<K, V> minNode = null;
        root = root.right;
        while(root != null) {
            minNode = root;
            root = root.left;
        }
        return minNode;
    }

    @Override
    public Iterator<V> getIterator() {
        iteraror = new TreeIterator<K, V> (root);
        return iteraror;
    }

}
