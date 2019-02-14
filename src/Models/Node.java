package Models;

import java.awt.*;

public class Node {
    private Node left;
    private Cave cave;
    private Node right;
    private boolean visited;
    private int fe;

    Node(Node left, Cave cave, Node right) {
        this.left = left;
        this.cave = cave;
        this.right = right;
        this.fe = 0;
        this.visited = false;
    }
    
    
    public Node getLeft() {
        return left;
    }

    void setLeft(Node left) {
        this.left = left;
    }
    
    public Cave getCave() {
        return cave;
    }
    
    public Node getRight() {
        return right;
    }

    void setRight(Node right) {
        this.right = right;
    }

    int getFe() {
        return fe;
    }

    void setFe(int fe) {
        this.fe = fe;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public Rectangle getRect() {
        return new Rectangle(this.getCave().getX(), this.getCave().getY(), this.getCave().getWidth(), this.getCave().getHeight());
    }
}

