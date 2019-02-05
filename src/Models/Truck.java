package Models;

import Classes.Node;

import javax.swing.*;
import java.util.LinkedList;

public class Truck extends Base implements Runnable {
    private Thread thread;
    int image;
    int capacity;
    Cave nextCave;
    LinkedList<Node> path;
    boolean move = true;
    Truck t;

    public Truck(int x, int y) {
        super(x, y, 73, 43, "../img/truck/truck1.png");
        this.image = 1;
        new Thread(this).start();
    }

    @Override
    public void run() {

        while (move) {
            this.image++;
            if (this.image > 4) this.image = 1;
            this.setSprite(new ImageIcon(getClass().getResource("../img/truck/truck" + image + ".png")));
            moveX();
            //move(t, path);
            try {
                Thread.sleep(166);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void moveX() {
        this.setX(getX() + 5);
        if (this.getX() == 720) {
            this.setX(720);
        }
    }

    private void moveOptim(Truck truck, LinkedList<Node> cave) {
        if (path.get(0).getLeft().getCave().getAmount() < path.get(0).getRight().getCave().getAmount()) {
            truck.setY(path.get(0).getRight().getCave().getY());
            truck.setX(path.get(0).getRight().getCave().getX());
        } else if (path.get(0).getLeft().getCave().getAmount() > path.get(0).getRight().getCave().getAmount()) {
            truck.setY(path.get(0).getLeft().getCave().getY());
            truck.setX(path.get(0).getLeft().getCave().getX());
        } else {
            truck.setY(path.get(0).getLeft().getCave().getY());
            truck.setX(path.get(0).getLeft().getCave().getX());
        }
    }
}

