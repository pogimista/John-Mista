package com.joko.floexam.main.model;

import java.util.List;

/**
 * Created by Joko on 5/16/2018.
 */

public class ElementResponse {

    String activityTitle;
    List<Element> elements;

    public List<Element> getElements() {
        return elements;
    }

    public String getActivityTitle() {
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle;
    }

    public void setElements(List<Element> elements) {
        this.elements = elements;
    }
}
