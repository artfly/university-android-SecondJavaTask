package com.noveogroup.model;

public class ElementForExample implements TreeElement {
    int id = 0;

    public ElementForExample(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return Integer.toString(id);
    }
}
