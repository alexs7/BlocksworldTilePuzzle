package com.alexs7;

import java.util.*;

/**
 * Created by alex on 11/06/2016.
 */
public class Tree {

    private TreeNode root;
    private int problemSize;
    private Agent agent;
    private State startingState;
    private State endingState;
    private ArrayList<int[]> visitedStates;
    private int[] startingValues;
    private int[] endingValues;

    public Tree() {
        startingValues = new int[]{ 1, 1, 1, 1,
                                    1, 1, 1, 1,
                                    1, 1, 1, 1,
                                    2, 3, 4, 0};

        endingValues = new int[]{   1, 1, 1, 1,
                                    1, 2, 1, 1,
                                    1, 3, 1, 1,
                                    1, 4, 1, 0};

        problemSize = (int) Math.sqrt(startingValues.length);
        agent = new Agent(problemSize);
        startingState = new State(startingValues);
        endingState = new State(endingValues);
        visitedStates = new ArrayList<>();


        //runDFSNonRecursive();
        //runBFSNonRecursive();
        runIterativeDeepeningDFS(1); // depth limit
    }

    private void runIterativeDeepeningDFS(int depthLimit){
        root = new TreeNode(startingState);
        Queue<TreeNode> queue  = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            List<TreeNode> children = getNextStates(node);

            if(node.getDepth() == depthLimit){
                System.out.println("Reached limit!");
                break;
            }

            for (TreeNode child : children){
                if(Arrays.equals(child.getData().getStateValues(),endingValues)){
                    System.out.println("Found!");
                    break;
                }
                child.setDepth(node.getDepth()+1);
                queue.add(child);
            }
        }
    }

    private void runBFSNonRecursive() {
        root = new TreeNode(startingState);
        Queue<TreeNode> queue  = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            List<TreeNode> children = getNextStates(node);

            for (TreeNode child : children){
                if(Arrays.equals(child.getData().getStateValues(),endingValues)){
                    System.out.println("Found!");
                    break;
                }
                queue.add(child);
            }
        }
    }

    private void runDFSNonRecursive() {
        root = new TreeNode(startingState);
        Stack<TreeNode> stack = new Stack();
        stack.push(root);
        visitedStates.add(root.getData().getStateValues());

        while(!stack.empty()){
            TreeNode node = stack.pop();
            List<TreeNode> children = getNextStates(node);

            for (TreeNode child : children){
                if(Arrays.equals(child.getData().getStateValues(),endingValues)){
                    System.out.println("Found!");
                    break;
                }
                stack.push(child);
            }

        }
    }

    private boolean goalStateReached(TreeNode node) {
        return Arrays.equals(node.getData().getStateValues(),endingValues);
    }

    private boolean stateVisited(int[] newStateValue) {
        for (int[] stateValue : visitedStates){

            if(Arrays.equals(stateValue,newStateValue)){
                return true;
            }
        }
        return false;
    }

    private List<TreeNode> getNextStates(TreeNode node) {

        List<TreeNode> nextStates = new ArrayList<>();

        if(agent.canMoveLeft(node)){
            nextStates.add(agent.moveLeft(node));
        }
        if(agent.canMoveRight(node)){
            nextStates.add(agent.moveRight(node));
        }
        if(agent.canMoveUp(node)){
            nextStates.add(agent.moveUp(node));
        }
        if(agent.canMoveDown(node)){
            nextStates.add(agent.moveDown(node));
        }

        return nextStates;
    }

//not use for now
//public void generateStateSpaceRecursive(TreeNode node){
//
//    if(node == null){
//        root = new TreeNode(startingState);
//        visitedStates.add(root.getData().getStateValues());
//        generateStateSpaceRecursive(root);
//    }else {
//        if (!goalStateReached(node)) {
//            List<TreeNode> nextStatesNodes = getNextStates(node);
//            for (TreeNode childNode : nextStatesNodes) {
//                if (!stateVisited(childNode.getData().getStateValues())) {
//                    visitedStates.add(childNode.getData().getStateValues());
//                    node.addChild(childNode);
//                    generateStateSpaceRecursive(childNode);
//                }
//            }
//        }
//    }
//
//}
}
