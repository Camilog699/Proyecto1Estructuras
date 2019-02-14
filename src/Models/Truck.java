package Models;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class Truck extends Base implements Runnable {
    private Thread thread;
    int image;
    Node nextNode;
    int desviacionX = 36;
    int desviacionY = 21;
    int step;
    String direction;
    int nextPos;
    private int capacity;
    private LinkedList<Node> nodes;
    private boolean optim;
    private int holding;

    public Truck(int x, int y, LinkedList<Node> nodes) {
        super(x, y, 73, 43, "../img/truck/right/truck1.png");
        this.image = 1;
        this.nodes = nodes;
        this.step = 10;
        this.optim = false;
        this.nextPos = 0;
        this.direction = "right";
        this.nextNode = nodes.get(0);
        this.capacity = 400;
        this.holding = 0;
        new Thread(this).start();
    }

    @Override
    public void run() {

        while (true) {
            this.image++;
            if (this.image > 4) this.image = 1;
            if (this.capacity <= this.holding) {
                goToStorage();
            }
            this.setSprite(new ImageIcon(getClass().getResource("../img/truck/" + direction + "/truck" + image + ".png")));
            moveInPath();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    void moveRight() {
        this.direction = "right";
        this.setX(this.getX() + step);
    }

    void moveUp() {
        this.setY(this.getY() - step);
    }

    void moveDown() {
        this.setY(this.getY() + step);
    }

    void moveLeft() {
        this.direction = "left";
        this.setX(this.getX() - step);
    }

    Rectangle getRect() {
        return new Rectangle(this.getX() + desviacionX, this.getY() + desviacionY, this.getWidth(), this.getHeight());
    }

    void flipNodes() {
        LinkedList<Node> listaAux = new LinkedList<>();
        for (int i = nodes.size() - 1; i >= 0; i--) {
            listaAux.add(this.nodes.get(i));
        }
        this.nodes = listaAux;
        this.nextNode = this.nodes.get(0);
        nextPos = 0;
    }

    public void goToStorage() {
        this.setX(20);
        this.setY(20);
        this.holding = 0;
    }

    public void moveInPath() {
        if (this.getRect().intersects(nextNode.getRect())) {
            this.holding = nextNode.getCave().collectMaterial(this);
            if (nextPos + 1 < nodes.size()) {
                nextPos++;
                nextNode = nodes.get(nextPos);
            } else {
                flipNodes();
            }
        }
        if (nextNode.getCave().getX() > this.getX()) {
            desviacionY = 0;
            desviacionX = -36;
            moveRight();
            return;
        }
        if (nextNode.getCave().getX() < this.getX()) {
            desviacionX = 36;
            desviacionY = 0;
            moveLeft();
            return;
        }
        if (nextNode.getCave().getY() < this.getY()) {
            desviacionY = 30;
            desviacionX = 0;
            moveUp();
            return;
        }
        if (nextNode.getCave().getY() > this.getY()) {
            desviacionY = -30;
            desviacionX = 0;
            moveDown();
            return;
        }
    }

    public LinkedList<Node> getNodes() {
        return nodes;
    }

    public void setNodes(LinkedList<Node> nodes) {
        this.nodes = nodes;
    }

    public boolean isOptim() {
        return optim;
    }

    public void setOptim(boolean optim) {
        this.optim = optim;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getHolding() {
        return holding;
    }

    public void setHolding(int holding) {
        this.holding = holding;
    }
}


    


