package com.simplexlabs.colorpicker.helperClasses;

/**
 * Created by Bill on 2017-04-06.
 */

public class MutableInt {
    public int value = 1; // note that we start at 1 since we're counting

    public MutableInt() {
        this.value = 1;
    }

    public void increment() {
        ++value;
    }

    public int get() {
        return value;
    }
}
