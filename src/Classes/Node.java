package Classes;

import Models.Cave;

public class Node {
    private Node left;
    private Cave cave;
    private Node right;
    private int level;
    private int fe;
    
    public Node() {
    }
    
    Node(Node left, Cave cave, Node right, int level) {
        this.left = left;
        this.cave = cave;
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
    
    public Cave getCave() {
        return cave;
    }
    
    public void setCave(Cave cave) {
        this.cave = cave;
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

