package com.noveogroup.exception.ancestor;

import com.noveogroup.exception.base.BinaryTreeException;

/**
 * Created by arty on 19.02.16.
 */
public class NoSuchNodeException extends BinaryTreeException {
    public NoSuchNodeException() {
        super();
    }

    public NoSuchNodeException(String message) {
        super(message);
    }
}
