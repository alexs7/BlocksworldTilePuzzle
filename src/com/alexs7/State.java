package com.alexs7;

import java.util.Arrays;

/**
 * Created by alex on 11/06/2016.
 */
public class State {
    private Cell[] stateValues;

    public State(Cell[] stateValues) {
        this.stateValues = stateValues.clone();
    }

    public static boolean equals(State state1, State state2) {
        return Arrays.equals(state1.getStateValues(), state2.getStateValues());
    }

    public Cell[] getStateValues() {
        return stateValues;
    }

    public int getAgentPosition(){
        int position = -1;
        for (int i = 0; i < stateValues.length; i++) {
            if(stateValues[i].getCharValue() == 'o'){
                position = i;
            }
        }
        return position;
    }
}
