package Classes;

import Models.Cove;

public class Node {
    private Node left;
    private Cove cove;
    private Node right;
    private int level;
    private int fe;
    
    public Node() {
    }
    
    Node(Node left, Cove cove, Node right, int level) {
        this.left = left;
        this.cove = cove;
        this.right = right;
        this.level = level;
        this.fe = 0;
    }
    
    
    public Node getLeft() {
        return left;
    }
    
    public void setLeft(Node left) {
        this.left = left;
    }
    
    public Cove getCove() {
        return cove;
    }
    
    public void setCove(Cove cove) {
        this.cove = cove;
    }
    
    public Node getRight() {
        return right;
    }
    
    public void setRight(Node right) {
        this.right = right;
    }
    
    public int getLevel() {
        return level;
    }
    
    public void setLevel(int level) {
        this.level = level;
    }
    
    public int getFe() {
        return fe;
    }
    
    public void setFe(int fe) {
        this.fe = fe;
    }
}

