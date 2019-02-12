package Models;

import javax.swing.*;
import java.util.LinkedList;

public class Truck extends Base implements Runnable {
    private Thread thread;
    int image;
    int capacity;
    Cave nextCave;
    LinkedList<Node> caves;
    boolean move = true;
    boolean moveRightX = true;
    boolean moveRightY = true;
    boolean moveleftX = true;
    boolean moveleftY = true;

    public Truck(int x, int y, LinkedList<Node> caves) {
        super(x, y, 73, 43, "../img/truck/truck1.png");
        this.image = 1;
        this.caves = caves;
        new Thread(this).start();
    }

    @Override
    public void run() {

        while (true) {
            this.image++;
            if (this.image > 4) this.image = 1;
            this.setSprite(new ImageIcon(getClass().getResource("../img/truck/truck" + image + ".png")));
            moveX();
            try {
                Thread.sleep(166);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void moveX() {
        if (move) {
            this.setX(getX() + 5);
            if (this.getX() == caves.get(0).getCave().getX()) {
                move = false;
            }
        }
    }

    private void moveOptimR(LinkedList<Cave> cave) {
        if (caves.get(0).getLeft().getCave().getValue() < caves.get(0).getRight().getCave().getValue()) {
            if (moveRightY) {
                if (this.getY() == caves.get(0).getRight().getCave().getY()) {
                    moveRightY = false;
                } else if (this.getY() != caves.get(0).getRight().getCave().getY()) {
                    this.setY(getY() + 5);
                }
            }
            if (moveRightX) {
                if (this.getX() == caves.get(0).getRight().getCave().getX()) {
                    moveRightX = false;
                } else if (this.getX() != caves.get(0).getRight().getCave().getX()) {
                    this.setX(getX() + 5);
                }
            }
        }
    }


    private void moveOptimL(LinkedList<Cave> cave) {

        if (caves.get(0).getLeft().getCave().getValue() > caves.get(0).getRight().getCave().getValue()) {
            if (moveleftY) {
                if (this.getY() == caves.get(0).getLeft().getCave().getY()) {
                    moveleftY = false;
                } else if (this.getY() != caves.get(0).getLeft().getCave().getY()) {
                    this.setY(getY() - 5);
                }
            }
            if (moveleftX) {
                if (this.getX() == caves.get(0).getLeft().getCave().getX()) {
                    moveleftY = false;
                } else if (this.getX() != caves.get(0).getLeft().getCave().getX()) {
                    this.setX(getX() - 5);
                }
            }
        }
    }
}


    


