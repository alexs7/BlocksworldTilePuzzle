package com.alexs7;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 11/06/2016.
 */
public class TreeNode {
    private TreeNode parent;
    private List<TreeNode> children;
    private State data;

    public TreeNode(State data) {
        this.data = new State(data.getStateValues());
        this.children = new ArrayList<>();
    }

    //    new incoming node
    public void addChild(TreeNode child) {
        child.setParent(this);
        this.children.add(child);
    }

    public State getData() {
        return data;
    }

    public List<TreeNode> getChildren(){
        return this.children;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }
}
