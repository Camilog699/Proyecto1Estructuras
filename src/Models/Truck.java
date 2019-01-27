package Models;

import javax.swing.*;
import java.util.LinkedList;

public class Truck extends Base implements Runnable {
    private Thread thread;
    int image;
    int capacity;
    Cave nextCove;
    LinkedList<Cave> path;
    
    public Truck(int x, int y) {
        super(x, y, 73, 43, "../img/truck/truck1.png");
        this.image = 1;
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
        this.setX(getX() + 10);
    }

    private void moveY() {
        this.setX(getY() + 2);
    }

}

