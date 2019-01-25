package Models;

import javax.swing.*;

public class Truck extends Base implements Runnable {
    private Thread thread;
    int image;
    
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
            try {
                Thread.sleep(166);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
