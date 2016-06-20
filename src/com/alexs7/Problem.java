package com.alexs7;

/**
 * Created by alex on 20/06/2016.
 */
public class Problem {
    private int[] startingValues;
    private int[] endingValues;
    private int problemSize;

    public Problem(int problemSize){
        this.problemSize = problemSize;
        startingValues = new int[]{ 1, 1, 1, 1,
                                    1, 1, 1, 1,
                                    1, 1, 1, 1,
                                    2, 3, 4, 0};

        endingValues = new int[]{   1, 1, 1, 1,
                                    1, 2, 1, 1,
                                    1, 3, 1, 1,
                                    1, 4, 1, 0};
    }

    public int[] getStartingValues() {
        return startingValues;
    }

    public int[] getEndingValues() {
        return endingValues;
    }

    public int getSize() {
        return problemSize;
    }
}
