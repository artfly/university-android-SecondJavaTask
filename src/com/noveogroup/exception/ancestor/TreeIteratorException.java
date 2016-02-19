package com.noveogroup.exception.ancestor;

import com.noveogroup.exception.base.BinaryTreeRuntimeException;

/**
 * Created by arty on 19.02.16.
 */
public class TreeIteratorException extends BinaryTreeRuntimeException {
    public TreeIteratorException() {
        super();
    }

    public TreeIteratorException(String message) {
        super(message);
    }
}
