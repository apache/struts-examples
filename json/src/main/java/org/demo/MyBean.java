package org.demo;

import java.util.ArrayList;
import java.util.List;

public class MyBean {

    private int counter;
    private List<String> names;

    public MyBean() {
        counter = 0;
        names = new ArrayList<String>();
    }

    public MyBean(int counter, List<String> names) {
        this.counter = counter;
        this.names = names;
    }

    public int getCounter() {
        return counter;
    }

    public List<String> getNames() {
        return names;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }
}
