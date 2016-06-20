package com.alexs7;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by alex on 20/06/2016.
 */
public class BlindSearch {

    public static void runIterativeDeepeningDFS(TreeNode root, TreeNode goal, Agent agent, int depthLimit){
        int[] endingValues = goal.getData().getStateValues();
        Queue<TreeNode> queue  = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            List<TreeNode> children = agent.getNextStates(node);

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
}
