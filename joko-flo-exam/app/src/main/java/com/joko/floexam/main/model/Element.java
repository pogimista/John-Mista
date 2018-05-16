package com.joko.floexam.main.model;

import java.util.List;

/**
 * Created by Joko on 5/16/2018.
 */

public class Element {

    String id;
    String type;
    String data;
    List<ElementField> fields;


    public List<ElementField> getFields() {
        return fields;
    }

    public String getData() {
        return data;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setFields(List<ElementField> fields) {
        this.fields = fields;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }
}

