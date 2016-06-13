package com.alexs7;

import java.util.Arrays;

/**
 * Created by alex on 11/06/2016.
 */
public class State {
    Cell[] stateValues;

    public State(Cell[] startingValues) {
        stateValues = startingValues;
    }

    public boolean equals(State state) {
        return Arrays.equals(state.getStateValues(),this.getStateValues());
    }

    public Cell[] getStateValues() {
        return stateValues;
    }
}
