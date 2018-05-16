package com.joko.floexam.main.model;

/**
 * Created by Joko on 5/16/2018.
 */

public class ElementField {

    String id;
    String label;
    String type;

    public void setId(String id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
