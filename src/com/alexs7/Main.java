package com.alexs7;

public class Main {

    public static void main(String[] args) {
        Problem problem  = new Problem(4); // width and height of board
        TreeNode root = new TreeNode(new State(problem.getStartingValues()));
        TreeNode goal = new TreeNode(new State(problem.getEndingValues()));

        Agent agent = new Agent(problem.getSize());
        BlindSearch.runIterativeDeepeningDFS(root,goal,agent,2);
    }
}
