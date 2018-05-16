package com.joko.floexam.main.model;

import java.util.List;
import java.util.Map;

/**
 * Created by john.mista on 5/16/18.
 */

public class User {

    Map<String, Object> userValues;
    List<ElementField> elementFields;

    public List<ElementField> getElementFields() {
        return elementFields;
    }

    public Map<String, Object> getUserValues() {
        return userValues;
    }

    public void setElementFields(List<ElementField> elementFields) {
        this.elementFields = elementFields;
    }

    public void setUserValues(Map<String, Object> userValues) {
        this.userValues = userValues;
    }
}
