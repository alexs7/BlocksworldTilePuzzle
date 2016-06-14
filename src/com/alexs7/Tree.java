package com.alexs7;

import java.util.ArrayList;
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

    public Tree() {
        Cell[] startingValues = new Cell[]{ new Cell(0),    new Cell(1),    new Cell(2),    new Cell(3),
                                            new Cell(4),    new Cell(5),    new Cell(6),    new Cell(7),
                                            new Cell(8),    new Cell(9),    new Cell(10),   new Cell(11),
                                            new Cell('A'),  new Cell('B'),  new Cell('C'),  new Cell('o')};

        Cell[] endingValues = new Cell[]{   new Cell(0),    new Cell(1),    new Cell(2),    new Cell(3),
                                            new Cell(4),    new Cell('A'),  new Cell(6),    new Cell(7),
                                            new Cell(8),    new Cell('B'),  new Cell(10),   new Cell(11),
                                            new Cell(12),   new Cell('C'),  new Cell(14),   new Cell('o')};

        problemSize = (int) Math.sqrt(startingValues.length);
        agent = new Agent(problemSize);
        startingState = new State(startingValues);
        endingState = new State(endingValues);


        root = new TreeNode(startingState);

        List<TreeNode> nextStates = getNextStates(root);
        generateStateSpaceRecursive(root, nextStates);
    }

    public TreeNode generateStateSpaceRecursive(TreeNode node, List<TreeNode> childrenToAdd){

        for (TreeNode child : childrenToAdd){
            node.addChild(child);
        }

        for (TreeNode child : node.getChildren()){
            generateStateSpaceRecursive(child, getNextStates(child));
        }

        return root;
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
}
