package com.noveogroup.model;

import sun.reflect.generics.tree.Tree;

/**
 * Created by arty on 19.02.16.
 */
public class AnotherElement implements TreeElement {
    int id = -1;

    @Override
    public int getId() {
        return -id;
    }
}
