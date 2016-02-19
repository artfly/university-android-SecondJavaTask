package com.noveogroup.main;

import com.noveogroup.exception.BinaryTreeException;
import com.noveogroup.model.AnotherElement;
import com.noveogroup.model.ElementForExample;
import com.noveogroup.model.TreeElement;
import com.noveogroup.tree.BinaryTree;
import com.noveogroup.tree.BinaryTreeImpl;

import java.io.*;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        //You can check your implementation here.
        //For example:

        //create BinaryTree
        BinaryTree<Integer, TreeElement> tree = new BinaryTreeImpl<>();
        try {
            tree.addElement(10, new ElementForExample());
            tree.addElement(42, new AnotherElement());
            tree.addElement(3, new ElementForExample());
            tree.addElement(14, new AnotherElement());
            File file = new File("tree.bin");

            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(tree);
            oos.close();

            FileInputStream fis =  new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            BinaryTree<Integer, TreeElement> treeFromFile = (BinaryTree<Integer, TreeElement>) ois.readObject();
        } catch (BinaryTreeException e) {

        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
//        try {
//            //add element
//            tree.addElement(10, new ElementForExample());
//            //remove element
//            tree.removeElement(10);
//            //get iterator
//            Iterator<TreeElement> iterator = tree.getIterator();
//            while (iterator.hasNext()) {
//                TreeElement next = iterator.next();
//                System.out.println(next.getId());
//            }
//        } catch (BinaryTreeException e) {
//            //handle the exception
//        }
    }
}
