package com.noveogroup.exception.base;

/**
 * This is the base exception of your binary tree.
 * You can change it and create subclasses (ElementAlreadyExistsException, for example).
 */
public class BinaryTreeException extends Exception {
    public BinaryTreeException() {
        super();
    }

    public BinaryTreeException(String message) {
        super(message);
    }
}
