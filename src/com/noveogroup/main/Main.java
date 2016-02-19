package com.noveogroup.main;

import com.noveogroup.exception.base.BinaryTreeException;
import com.noveogroup.model.ElementForExample;
import com.noveogroup.model.TreeElement;
import com.noveogroup.tree.BinaryTree;
import com.noveogroup.tree.BinaryTreeImpl;

import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        BinaryTree<String, TreeElement> tree = new BinaryTreeImpl<>();
        try {
            tree.addElement("hello", new ElementForExample(42));
            Iterator<TreeElement> iterator = tree.getIterator();
            iterator.next();
            iterator.next();
            iterator.next();
        } catch (BinaryTreeException e) {
            e.printStackTrace();
        }
    }
}
