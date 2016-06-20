package com.alexs7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ar1v13 on 13/06/16.
 */
public class Agent {

    private int problemSize;

    public Agent(int problemSize) {
        this.problemSize = problemSize;
    }

    public boolean canMoveLeft(TreeNode node) {
        int agentPosition = node.getData().getAgentPosition();
        return agentPosition % problemSize != 0;
    }

    public boolean canMoveRight(TreeNode node) {
        int agentPosition = node.getData().getAgentPosition();
        return (agentPosition + 1) % problemSize != 0;
    }

    public boolean canMoveUp(TreeNode node) {
        int agentPosition = node.getData().getAgentPosition();
        return agentPosition > problemSize;
    }

    public boolean canMoveDown(TreeNode node) {
        int agentPosition = node.getData().getAgentPosition();
        return agentPosition < ((problemSize * problemSize) - problemSize);
    }

    public TreeNode moveLeft(TreeNode node) {
        TreeNode newNode = new TreeNode(node.getData());
        int agentPosition = newNode.getData().getAgentPosition();
        int agentCell  = newNode.getData().getStateValues()[agentPosition];
        int leftCell  = newNode.getData().getStateValues()[agentPosition-1];

        newNode.getData().getStateValues()[agentPosition] = leftCell;
        newNode.getData().getStateValues()[agentPosition-1] = agentCell;

        return newNode;
    }

    public TreeNode moveRight(TreeNode node) {
        TreeNode newNode = new TreeNode(node.getData());
        int agentPosition = newNode.getData().getAgentPosition();
        int agentCell  = newNode.getData().getStateValues()[agentPosition];
        int rightCell  = newNode.getData().getStateValues()[agentPosition+1];

        newNode.getData().getStateValues()[agentPosition] = rightCell;
        newNode.getData().getStateValues()[agentPosition+1] = agentCell;

        return newNode;
    }

    public TreeNode moveUp(TreeNode node) {
        TreeNode newNode = new TreeNode(node.getData());
        int agentPosition = newNode.getData().getAgentPosition();
        int agentCell  = newNode.getData().getStateValues()[agentPosition];
        int upperCell  = newNode.getData().getStateValues()[agentPosition-problemSize];

        newNode.getData().getStateValues()[agentPosition] = upperCell;
        newNode.getData().getStateValues()[agentPosition-problemSize] = agentCell;

        return newNode;
    }

    public TreeNode moveDown(TreeNode node) {
        TreeNode newNode = new TreeNode(node.getData());
        int agentPosition = newNode.getData().getAgentPosition();
        int agentCell  = newNode.getData().getStateValues()[agentPosition];
        int upperCell  = newNode.getData().getStateValues()[agentPosition+problemSize];

        newNode.getData().getStateValues()[agentPosition] = upperCell;
        newNode.getData().getStateValues()[agentPosition+problemSize] = agentCell;

        return newNode;
    }

    public List<TreeNode> getNextStates(TreeNode node) {

        List<TreeNode> nextStates = new ArrayList<>();

        if(canMoveLeft(node)){
            nextStates.add(moveLeft(node));
        }
        if(canMoveRight(node)){
            nextStates.add(moveRight(node));
        }
        if(canMoveUp(node)){
            nextStates.add(moveUp(node));
        }
        if(canMoveDown(node)){
            nextStates.add(moveDown(node));
        }

        return nextStates;
    }
}
