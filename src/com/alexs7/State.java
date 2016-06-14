package com.alexs7;

import java.util.Arrays;

/**
 * Created by alex on 11/06/2016.
 */
public class State {
    private int[] stateValues;

    public State(int[] stateValues) {
        this.stateValues = stateValues.clone();
    }

    public int[] getStateValues() {
        return stateValues;
    }

    public int getAgentPosition(){
        int position = -1;
        for (int i = 0; i < stateValues.length; i++) {
            if(stateValues[i] == 0){
                position = i;
            }
        }
        return position;
    }
}
