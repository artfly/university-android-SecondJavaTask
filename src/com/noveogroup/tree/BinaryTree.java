package com.noveogroup.tree;

import com.noveogroup.exception.base.BinaryTreeException;

import java.io.Serializable;
import java.util.Iterator;

/**
 * Binary tree interface. Please add your exceptions to methods.
 */
public interface BinaryTree <K,V> extends Serializable {
    void addElement(K key, V element) throws BinaryTreeException;
    void removeElement(K key) throws BinaryTreeException;
    Iterator<V> getIterator();
}
