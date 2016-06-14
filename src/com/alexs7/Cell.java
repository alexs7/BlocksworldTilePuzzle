package com.alexs7;

/**
 * Created by alex on 11/06/2016.
 */
public class Cell {
    int intValue;
    char charValue;

    public Cell(int intValue) {
        this.intValue = intValue;
    }

    public Cell(char charValue) {
        this.charValue = charValue;
    }

    public char getCharValue() {
        return charValue;
    }
}
