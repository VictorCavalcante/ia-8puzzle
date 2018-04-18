package br.ic.ufal.ia.puzzle;

import java.util.ArrayList;

public class Node {
    private boolean visited;

    private String state;
    private ArrayList<Node> children;
    private Node parent;
    private int depth;

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node(String state) {
        this.state = state;
        children = new ArrayList<>();
    }

    public String getState() {
        return state;
    }

    public void addChild(Node child) {
        children.add(child);
    }
}