package com.alexs7;

import java.awt.*;
import java.util.*;
import java.util.List;

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
        //runIterativeDeepeningDFS(4); // depth limit
        runBestFirstSearch(); // uses a heuristic function
    }

    private void runBestFirstSearch() {
        root = new TreeNode(startingState);
        Queue<TreeNode> queue  = new LinkedList<>();
        queue.add(root);

        mainloop:
        while(!queue.isEmpty()){
            TreeNode node = getNodeWithMinimumCostFunctionValueFromGoal(queue,endingState);
            List<TreeNode> children = agent.getNextStates(node);

            for (TreeNode child : children){

                if(isNodeEndGoal(child)){
                    System.out.println("Found!");
                    break mainloop;
                }

                double cost = calculateManhattanDistance(child,endingState);
                child.setCostFunctionValue(cost);

                queue.add(child);
            }
        }
    }

    private TreeNode getNodeWithMinimumCostFunctionValueFromGoal(Queue<TreeNode> queue, State endingState) {

        double minManhattanDistance = Double.MAX_VALUE;
        TreeNode minNode = null;

        if(queue.size() == 1){
            return queue.poll();
        }
        for (TreeNode node : queue){
            if(node.getCostFunctionValue() < minManhattanDistance){
                minNode = node;
                minManhattanDistance = node.getCostFunctionValue();
            }
        }
        return minNode;
    }

    private double calculateManhattanDistance(TreeNode child, State endingState) {
        int[] childStateValues = child.getData().getStateValues();
        int[] endingStateValues = endingState.getStateValues();
        int tempElement = 0;
        int childElementIndex = 0;
        int endingStateElementIndex = 0;
        Point childElementPoint;
        Point endingStateElementPoint;
        double manhattanDistance = 0.0;

        for (int i = 0; i < childStateValues.length; i++) {
            if(childStateValues[i] != 1){
                tempElement = childStateValues[i];
                childElementIndex = i;
                for (int j = 0; j < endingStateValues.length; j++) {
                    if(endingStateValues[j] == tempElement){
                        endingStateElementIndex = j;
                    }
                }
                childElementPoint = new Point(childElementIndex%4,Math.round(childElementIndex/4));
                endingStateElementPoint = new Point(endingStateElementIndex%4,Math.round(endingStateElementIndex/4));
                manhattanDistance += Math.abs(childElementPoint.getX() - endingStateElementPoint.getX()) + Math.abs(childElementPoint.getY() - endingStateElementPoint.getY());

//                System.out.println("checking element:" + tempElement + ", index in childNode: "+ childElementIndex + ", index in goalNode: "+ endingStateElementIndex);
//                System.out.println("childElement coordinates: "+ childElementPoint.getX() + ", " + childElementPoint.getY());
//                System.out.println("endingStateElement coordinates: "+ endingStateElementPoint.getX() + ", " + endingStateElementPoint.getY());
//                System.out.println("manhattan distance "+manhattanDistance);
//                System.out.println();
            }
        }

        return manhattanDistance;
    }


    private void runBFSNonRecursive() {
        root = new TreeNode(startingState);
        Queue<TreeNode> queue  = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            List<TreeNode> children = agent.getNextStates(node);

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
            List<TreeNode> children = agent.getNextStates(node);

            for (TreeNode child : children){
                if(Arrays.equals(child.getData().getStateValues(),endingValues)){
                    System.out.println("Found!");
                    break;
                }
                stack.push(child);
            }

        }
    }

    private boolean isNodeEndGoal(TreeNode node) {
        return Arrays.equals(node.getData().getStateValues(),endingValues);
    }

    //this makes the problem a graph based problem
//    private boolean stateVisited(int[] newStateValue) {
//        for (int[] stateValue : visitedStates){
//
//            if(Arrays.equals(stateValue,newStateValue)){
//                return true;
//            }
//        }
//        return false;
//    }

    //not use for now
//public void generateStateSpaceRecursive(TreeNode node){
//
//    if(node == null){
//        root = new TreeNode(startingState);
//        visitedStates.add(root.getData().getStateValues());
//        generateStateSpaceRecursive(root);
//    }else {
//        if (!isNodeEndGoal(node)) {
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
