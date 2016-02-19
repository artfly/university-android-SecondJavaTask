package com.noveogroup.exception.ancestor;

import com.noveogroup.exception.base.BinaryTreeException;

/**
 * Created by arty on 19.02.16.
 */
public class ElementAlreadyExistsException extends BinaryTreeException {
    public ElementAlreadyExistsException() {
        super();
    }

    public ElementAlreadyExistsException(String message) {
        super(message);
    }
}
