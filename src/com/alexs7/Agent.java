package com.alexs7;

import java.util.Arrays;

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
        int foo = ((problemSize * problemSize) - problemSize);
        return agentPosition < ((problemSize * problemSize) - problemSize);
    }

    public TreeNode moveLeft(TreeNode node) {
        TreeNode newNode = new TreeNode(node.getData());
        int agentPosition = newNode.getData().getAgentPosition();
        Cell agentCell  = newNode.getData().getStateValues()[agentPosition];
        Cell leftCell  = newNode.getData().getStateValues()[agentPosition-1];

        newNode.getData().getStateValues()[agentPosition] = leftCell;
        newNode.getData().getStateValues()[agentPosition-1] = agentCell;

        return newNode;
    }

    public TreeNode moveRight(TreeNode node) {
        TreeNode newNode = new TreeNode(node.getData());
        int agentPosition = newNode.getData().getAgentPosition();
        Cell agentCell  = newNode.getData().getStateValues()[agentPosition];
        Cell rightCell  = newNode.getData().getStateValues()[agentPosition+1];

        newNode.getData().getStateValues()[agentPosition] = rightCell;
        newNode.getData().getStateValues()[agentPosition+1] = agentCell;

        return newNode;
    }

    public TreeNode moveUp(TreeNode node) {
        TreeNode newNode = new TreeNode(node.getData());
        int agentPosition = newNode.getData().getAgentPosition();
        Cell agentCell  = newNode.getData().getStateValues()[agentPosition];
        Cell upperCell  = newNode.getData().getStateValues()[agentPosition-problemSize];

        newNode.getData().getStateValues()[agentPosition] = upperCell;
        newNode.getData().getStateValues()[agentPosition-problemSize] = agentCell;

        return newNode;
    }

    public TreeNode moveDown(TreeNode node) {
        TreeNode newNode = new TreeNode(node.getData());
        int agentPosition = newNode.getData().getAgentPosition();
        Cell agentCell  = newNode.getData().getStateValues()[agentPosition];
        Cell upperCell  = newNode.getData().getStateValues()[agentPosition+problemSize];

        newNode.getData().getStateValues()[agentPosition] = upperCell;
        newNode.getData().getStateValues()[agentPosition+problemSize] = agentCell;

        return newNode;
    }
}
